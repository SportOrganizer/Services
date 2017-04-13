/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.webservices.game;

import com.google.gson.Gson;
import com.so.core.controller.dto.game.GameDto;
import com.so.core.controller.dto.game.GamePlayerDto;
import com.so.core.exception.AppException;
import com.so.core.services.game.GameService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kristián Kačinetz
 */
@RestController
@CrossOrigin
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService gameService;

    ////////////GAME///////////////////
    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String createGame(@RequestBody GameDto request) throws AppException {
        Gson gson = new Gson();
        GameDto response = gameService.createGame(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateGame(@PathVariable(value = "id") Integer id, @RequestBody GameDto request) throws AppException {

        if (!Objects.equals(id, request.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id v url sa nerovna id v dto");
        }
        Gson gson = new Gson();
        GameDto response = gameService.editGame(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllGames() throws AppException {
        Gson gson = new Gson();
        List<GameDto> response = gameService.findAllGames();
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getOneGame(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        GameDto response = gameService.findGame(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteGame(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        gameService.deleteGame(id);
        return gson.toJson(gameService.findAllGames());
    }

    ////////////GAME///////////////////
    /////////////GAMEPLAYER//////////////
    @RequestMapping(path = "/gameplayer/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addGamePlayer(@RequestBody GamePlayerDto request) throws AppException {
        Gson gson = new Gson();
        GamePlayerDto response = gameService.addGamePlayer(request);
        return gson.toJson(response);
    }


    @RequestMapping(path = "/gameplayer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getOneGamePlayer(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        GamePlayerDto response = gameService.findGamePlayer(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "{idGame}/gameplayer/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllGamePlayersByGame(@PathVariable(value = "idGame") Integer id) throws AppException {

        Gson gson = new Gson();
        List<GamePlayerDto> response = gameService.getAllGamePlayersByGame(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/gameplayer/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateGamePlayer(@PathVariable(value = "id") Integer id, @RequestBody GamePlayerDto request) throws AppException {

        if (!Objects.equals(id, request.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id v url sa nerovna id v dto");
        }
        Gson gson = new Gson();
        GamePlayerDto response = gameService.editGamePlayer(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/gameplayer/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteGamePlayer(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        gameService.deleteGamePlayer(id);
        return gson.toJson(gameService.findAllGamePlayers());
    }

}
