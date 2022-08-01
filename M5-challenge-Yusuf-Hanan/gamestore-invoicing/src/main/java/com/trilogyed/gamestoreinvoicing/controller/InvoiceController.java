package com.trilogyed.gamestoreinvoicing.controller;

import com.trilogyed.gamestoreinvoicing.service.GameStoreInvoicingServiceLayer;
import com.trilogyed.gamestoreinvoicing.viewModel.ConsoleViewModel;
import com.trilogyed.gamestoreinvoicing.viewModel.GameViewModel;
import com.trilogyed.gamestoreinvoicing.viewModel.InvoiceViewModel;
import com.trilogyed.gamestoreinvoicing.viewModel.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/invoice")
@CrossOrigin(origins = {"http://localhost:3000"})
public class InvoiceController {

    @Autowired
    GameStoreInvoicingServiceLayer service;

    // Assumption: All orders are final and data privacy is not top priority. Therefore, the Update & Delete EndPoints
    // are left out by design due to its potential danger. The getAllInvoices is a questionable one since it could
    // overwhelm the system and infringes on data privacy; however, it does not damage data as with the Update and Delete
//Game
    @GetMapping("/game")
    public List<GameViewModel> getAllGames() {
        return service.getGames();
    }

    @GetMapping("/game/{id}")
    public Optional<GameViewModel> getGameInfo(@PathVariable("id") long gameId) {
        return service.getGameById(gameId);
    }

    @GetMapping("game/title/{title}")
    public List<GameViewModel> getGamesByTitle(@PathVariable("title") String title){
        return service.getGameTitle(title);
    }

    @GetMapping("game/esrbrating/{esrb}")
    public List<GameViewModel> getGamesByEsrbRating(@PathVariable("esrb") String esrb){
        return service.getGameRating(esrb);
    }

    @GetMapping("game/studio/{studio}")
    public List<GameViewModel> getGamesByStudio(@PathVariable("studio") String studio){
        return service.getGameStudio(studio);
    }

//Tshirt

    @GetMapping("/tshirt")
    public List<TShirtViewModel> getAllTShirts() {
        return service.getAllTShirts();
    }

    @GetMapping("/tshirt/{id}")
    public Optional<TShirtViewModel> getTShirt(@PathVariable("id") int tShirtId) {
        return service.getTShirtById(tShirtId);
    }
    @PostMapping("/tshirt")
    @ResponseStatus(HttpStatus.CREATED)
    TShirtViewModel createTShirt(@RequestBody @Valid TShirtViewModel tShirtViewModel){
        return service.createTShirt(tShirtViewModel);
    }
    @GetMapping("/tshirt/size/{size}")
    public List<TShirtViewModel> getTShirtsBySize(@PathVariable("size") String size) {
        return service.getTShirtsBySize(size);
    }

    @GetMapping("/tshirt/color/{color}")
    public List<TShirtViewModel> getTShirtsByColor(@PathVariable("color") String color) {
        return service.getTShirtsByColor(color);
    }

//Console
    @GetMapping("/console")
    public List<ConsoleViewModel> getAllConsoles() {
        return service.getAllConsoles();
    }

    @GetMapping("/console/{id}")
    public ConsoleViewModel getConsole(@PathVariable("id") long consoleId) {
        return service.getConsoleById(consoleId);
    }

    @GetMapping("/console/manufacturer/{manufacturer}")
    public List<ConsoleViewModel> getConsoleByManufacturer(@PathVariable("manufacturer") String manu) {
        return service.getConsoleByManufacturer(manu);
    }

//Invoice
    @PostMapping("/createInvoice")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel purchaseItem(@RequestBody @Valid InvoiceViewModel invoiceViewModel) {
        invoiceViewModel = service.createInvoice(invoiceViewModel);
        return invoiceViewModel;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel findInvoice(@PathVariable("id") long invoiceId) {
        InvoiceViewModel invoiceViewModel = service.getInvoice(invoiceId);
        if (invoiceViewModel == null) {
            throw new IllegalArgumentException("Invoice could not be retrieved for id " + invoiceId);
        } else {
            return invoiceViewModel;
        }
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> findAllInvoices() {
        List<InvoiceViewModel> invoiceViewModelList = service.getAllInvoices();

        if (invoiceViewModelList == null || invoiceViewModelList.isEmpty()) {
            throw new IllegalArgumentException("No invoices were found.");
        } else {
            return invoiceViewModelList;
        }
    }

    @GetMapping("/cname/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> findInvoicesByCustomerName(@PathVariable String name) {
        List<InvoiceViewModel> invoiceViewModelList = service.getInvoicesByCustomerName(name);

        if (invoiceViewModelList == null || invoiceViewModelList.isEmpty()) {
            throw new IllegalArgumentException("No invoices were found for: "+name);
        } else {
            return invoiceViewModelList;
        }
    }
}
