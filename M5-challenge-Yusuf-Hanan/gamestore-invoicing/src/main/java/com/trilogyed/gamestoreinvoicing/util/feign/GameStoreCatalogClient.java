package com.trilogyed.gamestoreinvoicing.util.feign;

import com.trilogyed.gamestoreinvoicing.viewModel.ConsoleViewModel;
import com.trilogyed.gamestoreinvoicing.viewModel.GameViewModel;
import com.trilogyed.gamestoreinvoicing.viewModel.TShirtViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@FeignClient(name="gamestore-catalog")
public interface GameStoreCatalogClient {

//Game
    @GetMapping("/game")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getAllGames();

    @GetMapping("/game/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<GameViewModel> getGameInfo(@PathVariable("id") long gameId);

    @PutMapping("/game")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody @Valid GameViewModel gameViewModel);

    @GetMapping("game/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByTitle(@PathVariable("title") String title);

    @GetMapping("game/esrbrating/{esrb}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByEsrbRating(@PathVariable("esrb") String esrb);

    @GetMapping("game/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByStudio(@PathVariable("studio") String studio);


//Tshirt
    @GetMapping("/tshirt")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getAllTShirts();

    @GetMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TShirtViewModel> getTShirt(@PathVariable("id") long tShirtId);

    @PostMapping("/tshirt")
    @ResponseStatus(HttpStatus.CREATED)
    TShirtViewModel createTShirt(@RequestBody @Valid TShirtViewModel tShirtViewModel);
    @GetMapping("/tshirt/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getTShirtsBySize(@PathVariable("size") String size);

    @GetMapping("/tshirt/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getTShirtsByColor(@PathVariable("color") String color);

//Console
    @GetMapping("/console")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getAllConsoles();

    @GetMapping("/console/{id}")
    @ResponseStatus(HttpStatus.OK)
    //returning optional consoleViewModel
    public Optional<ConsoleViewModel> getConsole(@PathVariable("id") long consoleId);
    @GetMapping("/console/{id}")
    public ConsoleViewModel getConsoleById(@PathVariable("id") long consoleId);
    @GetMapping("/console/manufacturer/{manufacturer}")
    public List<ConsoleViewModel> getConsoleByManufacturer(@PathVariable("manufacturer") String manu);

}
