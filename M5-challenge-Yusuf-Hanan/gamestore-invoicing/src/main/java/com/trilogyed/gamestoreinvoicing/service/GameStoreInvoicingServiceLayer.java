package com.trilogyed.gamestoreinvoicing.service;

import com.trilogyed.gamestoreinvoicing.model.Invoice;
import com.trilogyed.gamestoreinvoicing.model.ProcessingFee;
import com.trilogyed.gamestoreinvoicing.model.Tax;
import com.trilogyed.gamestoreinvoicing.repository.InvoiceRepository;
import com.trilogyed.gamestoreinvoicing.repository.ProcessingFeeRepository;
import com.trilogyed.gamestoreinvoicing.repository.TaxRepository;
import com.trilogyed.gamestoreinvoicing.util.feign.GameStoreCatalogClient;
import com.trilogyed.gamestoreinvoicing.viewModel.ConsoleViewModel;
import com.trilogyed.gamestoreinvoicing.viewModel.GameViewModel;
import com.trilogyed.gamestoreinvoicing.viewModel.InvoiceViewModel;
import com.trilogyed.gamestoreinvoicing.viewModel.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GameStoreInvoicingServiceLayer {


    private final BigDecimal PROCESSING_FEE = new BigDecimal("15.49");
    private final BigDecimal MAX_INVOICE_TOTAL = new BigDecimal("999.99");
    private final String GAME_ITEM_TYPE = "Game";
    private final String CONSOLE_ITEM_TYPE = "Console";
    private final String TSHIRT_ITEM_TYPE = "T-Shirt";

    InvoiceRepository invoiceRepo;
    TaxRepository taxRepo;
    ProcessingFeeRepository processingFeeRepo;
    GameStoreCatalogClient client;

    public GameStoreInvoicingServiceLayer(InvoiceRepository invoiceRepo, TaxRepository taxRepo, ProcessingFeeRepository processingFeeRepo, GameStoreCatalogClient client) {
        this.invoiceRepo = invoiceRepo;
        this.taxRepo = taxRepo;
        this.processingFeeRepo = processingFeeRepo;
        this.client = client;
    }

    //Game
    public List<GameViewModel> getGames() {
        List<GameViewModel> gameList = this.client.getAllGames();
        return gameList;
    }

    public GameViewModel getGameById(@PathVariable("id") long gameId){
        return client.getGameInfo(gameId);
    }

    public List<GameViewModel> getGameTitle(@PathVariable("title") String title){
        return client.getGamesByTitle(title);
    }
    public List<GameViewModel> getGameRating(@PathVariable("esrb") String esrb){
        return client.getGamesByEsrbRating(esrb);
    }

    public List<GameViewModel> getGameStudio(@PathVariable("studio") String studio){
        return client.getGamesByStudio(studio);
    }

//Tshirt
    public List<TShirtViewModel> getTShirts(){
        List<TShirtViewModel> tshirtList = this.client.getAllTShirts();
        return tshirtList;
    }

    public TShirtViewModel getTShirt(@PathVariable("id") int tShirtId){
        return client.getTShirt(tShirtId);
    }

    public Optional<ConsoleViewModel> getConsole(@PathVariable("id") long consoleId) {
        return client.getConsole(consoleId);
    }

    //Invoice
    public InvoiceViewModel createInvoice(InvoiceViewModel invoiceViewModel) {

        //validation...
        if (invoiceViewModel == null)
            throw new NullPointerException("Create invoice failed. no invoice data.");

        if (invoiceViewModel.getItemType() == null)
            throw new IllegalArgumentException("Unrecognized Item type. Valid ones: Console or Game");

        //Check Quantity is > 0...
        if (invoiceViewModel.getQuantity() <= 0) {
            throw new IllegalArgumentException(invoiceViewModel.getQuantity() +
                    ": Unrecognized Quantity. Must be > 0.");
        }

        //start building invoice...
        Invoice invoice = new Invoice();
        invoice.setName(invoiceViewModel.getName());
        invoice.setStreet(invoiceViewModel.getStreet());
        invoice.setCity(invoiceViewModel.getCity());
        invoice.setState(invoiceViewModel.getState());
        invoice.setZipcode(invoiceViewModel.getZipcode());
        invoice.setItemType(invoiceViewModel.getItemType());
        invoice.setItemId(invoiceViewModel.getItemId());

        //Checks the item type and get the correct unit price
        //Check if we have enough quantity
        if (invoiceViewModel.getItemType().equals(CONSOLE_ITEM_TYPE)) {

            Optional<ConsoleViewModel> console = client.getConsole(invoice.getItemId());

//            if (returnVal.isPresent()) {
//                tempCon = returnVal.get();
//            } else {
//                throw new IllegalArgumentException("Requested item is unavailable.");
//            }

//            if (invoiceViewModel.getQuantity() > console.get().getQuantity()) {
//                throw new IllegalArgumentException("Requested quantity is unavailable.");
//            }

            invoice.setUnitPrice(console.get().getPrice());

       }
//        else if (invoiceViewModel.getItemType().equals(GAME_ITEM_TYPE)) {
//            Game tempGame = null;
//            Optional<Game> returnVal = gameRepo.findById(invoiceViewModel.getItemId());
//
//            if (returnVal.isPresent()) {
//                tempGame = returnVal.get();
//            } else {
//                throw new IllegalArgumentException("Requested item is unavailable.");
//            }
//
//            if(invoiceViewModel.getQuantity() >  tempGame.getQuantity()){
//                throw new IllegalArgumentException("Requested quantity is unavailable.");
//            }
//            invoice.setUnitPrice(tempGame.getPrice());
//
//        } else if (invoiceViewModel.getItemType().equals(TSHIRT_ITEM_TYPE)) {
//            TShirt tempTShirt = null;
//            Optional<TShirt> returnVal = tShirtRepo.findById(invoiceViewModel.getItemId());
//
//            if (returnVal.isPresent()) {
//                tempTShirt = returnVal.get();
//            } else {
//                throw new IllegalArgumentException("Requested item is unavailable.");
//            }
//
//            if(invoiceViewModel.getQuantity() >  tempTShirt.getQuantity()){
//                throw new IllegalArgumentException("Requested quantity is unavailable.");
//            }
//            invoice.setUnitPrice(tempTShirt.getPrice());
//
//        } else {
//            throw new IllegalArgumentException(invoiceViewModel.getItemType()+
//                    ": Unrecognized Item type. Valid ones: T-Shirt, Console, or Game");
//        }

            invoice.setQuantity(invoiceViewModel.getQuantity());

            invoice.setSubtotal(
                    invoice.getUnitPrice().multiply(
                            new BigDecimal(invoiceViewModel.getQuantity())).setScale(2, RoundingMode.HALF_UP));

            //Throw Exception if subtotal is greater than 999.99
            if ((invoice.getSubtotal().compareTo(new BigDecimal(999.99)) > 0)) {
                throw new IllegalArgumentException("Subtotal exceeds maximum purchase price of $999.99");
            }

            //Validate State and Calc tax...
            BigDecimal tempTaxRate;
            Optional<Tax> returnVal = taxRepo.findById(invoice.getState());

            if (returnVal.isPresent()) {
                tempTaxRate = returnVal.get().getRate();
            } else {
                throw new IllegalArgumentException(invoice.getState() + ": Invalid State code.");
            }

            if (!tempTaxRate.equals(BigDecimal.ZERO))
                invoice.setTax(tempTaxRate.multiply(invoice.getSubtotal()));
            else
                throw new IllegalArgumentException(invoice.getState() + ": Invalid State code.");

            BigDecimal processingFee;
            Optional<ProcessingFee> returnVal2 = processingFeeRepo.findById(invoiceViewModel.getItemType());

            if (returnVal2.isPresent()) {
                processingFee = returnVal2.get().getFee();
            } else {
                throw new IllegalArgumentException("Requested item is unavailable.");
            }

            invoice.setProcessingFee(processingFee);

            //Checks if quantity of items if greater than 10 and adds additional processing fee
            if (invoiceViewModel.getQuantity() > 10) {
                invoice.setProcessingFee(invoice.getProcessingFee().add(PROCESSING_FEE));
            }

            invoice.setTotal(invoice.getSubtotal().add(invoice.getProcessingFee()).add(invoice.getTax()));

            //checks total for validation
            if ((invoice.getTotal().compareTo(MAX_INVOICE_TOTAL) > 0)) {
                throw new IllegalArgumentException("Subtotal exceeds maximum purchase price of $999.99");
            }

            invoice = invoiceRepo.save(invoice);

            return buildInvoiceViewModel(invoice);
        }

    public InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setId(invoice.getId());
        invoiceViewModel.setName(invoice.getName());
        invoiceViewModel.setStreet(invoice.getStreet());
        invoiceViewModel.setCity(invoice.getCity());
        invoiceViewModel.setState(invoice.getState());
        invoiceViewModel.setZipcode(invoice.getZipcode());
        invoiceViewModel.setItemType(invoice.getItemType());
        invoiceViewModel.setItemId(invoice.getItemId());
        invoiceViewModel.setUnitPrice(invoice.getUnitPrice());
        invoiceViewModel.setQuantity(invoice.getQuantity());
        invoiceViewModel.setSubtotal(invoice.getSubtotal());
        invoiceViewModel.setProcessingFee(invoice.getProcessingFee());
        invoiceViewModel.setTax(invoice.getTax());
        invoiceViewModel.setProcessingFee(invoice.getProcessingFee());
        invoiceViewModel.setTotal(invoice.getTotal());

        return invoiceViewModel;
    }
    }

