/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.game;

import com.so.core.controller.converter.game.GameConverter;
import com.so.core.controller.dto.game.GameDto;
import com.so.core.controller.dto.game.GamePlayerDto;
import com.so.core.exception.AppException;
import com.so.dal.core.model.game.Game;
import com.so.dal.core.model.game.GamePlayer;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.repository.game.GamePlayerRepository;
import com.so.dal.core.repository.game.GameRepository;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author peter
 */
@Service
public class GameService {

    private final static Logger LOG = LoggerFactory.getLogger(GameService.class);
    @Autowired
    private GameConverter gameConverter;

    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private GamePlayerRepository gamePlayerRepo;

    @Autowired
    private SeasonTournamentRepository stRepo;

    @Transactional
    public GameDto createGame(GameDto game) throws AppException {
        Game g = gameConverter.gameDtoToEntity(game);
        g = gameRepo.saveAndFlush(g);

        if (g == null) {
            LOG.error("game sa nepodarilo ulozit do dbs");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "game sa nepodarilo ulozit do dbs");
        }
        return gameConverter.gameEntityToDto(g);
    }

    @Transactional
    public List<GameDto> findAllGames() throws AppException {
        LOG.info("findAllGames()");

        List<Game> lg = gameRepo.findAllByOrderByStartTimeAsc();
        List<GameDto> l = new ArrayList<>();

        for (Game g : lg) {
            l.add(gameConverter.gameEntityToDto(g));
        }
        return l;
    }

    @Transactional
    public GameDto findGame(Integer id) throws AppException {
        LOG.info("findGame({})", id);

        Game g = gameRepo.findOne(id);

        if (g == null) {
            LOG.error("nenajdena game s id:{}", id);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdena game s id:" + id);
        }
        return gameConverter.gameEntityToDto(g);
    }

    @Transactional
    public List<GameDto> findBySeasonTournament(Integer stId, Integer groupId, Integer roundId, Integer locationId) throws AppException {
        LOG.info("findBySeasonTournament({},{},{},{})", stId, roundId, groupId, locationId);
        SeasonTournament st = stRepo.findOne(stId);
        if (st == null) {
            LOG.error("nenajdeny seasonTournament s id:", stId);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdeny seasonTournament s id:" + stId);
        }
        //  List<Game> lg = gameRepo.findAllBySeasonTournamentOrderByStartTimeAsc(st);
        List<Game> lg = gameRepo.filterByGroupROundLocation(groupId, roundId, locationId, stId);
        List<GameDto> l = new ArrayList<>();

        for (Game g : lg) {
            l.add(gameConverter.gameEntityToDto(g));
        }
        return l;
    }

    @Transactional
    public GameDto editGame(GameDto updatedGame) throws AppException {
        LOG.info("editGame({})", updatedGame);
        if (updatedGame == null) {
            LOG.error("aktualizovany objekt je null");
            throw new AppException(HttpStatus.BAD_REQUEST, "zadany objekt je nespravne vyplneny");
        }

        Game game = gameConverter.gameDtoToEntity(updatedGame);
        Game savedGame = gameRepo.saveAndFlush(game);

        if (savedGame == null) {
            LOG.error("nepodarilo sa ulozit updateovanu Game do db");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "Game sa nepodarilo aktualizovat");
        }
        return gameConverter.gameEntityToDto(savedGame);

    }

    @Transactional
    public void deleteGame(Integer id) throws AppException {
        LOG.info("deleteGame({})", id);
        Game g = gameRepo.findOne(id);

        if (g == null) {
            LOG.error("nenajdeny game na vymazanie id={}", id);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdeny game id=" + id);
        }
        gameRepo.delete(g);
    }

    @Transactional
    public GamePlayerDto addGamePlayer(GamePlayerDto gp) throws AppException {
        LOG.info("addGamePlayer({})", gp);
        if (gp.getCompetitorTeamPlayerId() == null || gp.getGameId() == null) {
            LOG.error("nevyplnene povinne parametre");
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplnene povinne parametre");
        }
        GamePlayer g = gameConverter.gamePlayerDtoToEntity(gp);
        g = gamePlayerRepo.saveAndFlush(g);
        if (g == null) {
            LOG.error("gamePlayer sa neulozil do databazy");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "gamePlayer sa neulozil do databazy");
        }
        return gameConverter.gamePlayerEntityToDto(g);
    }

    @Transactional
    public GamePlayerDto findGamePlayer(Integer id) throws AppException {
        LOG.info("findGamePlayer({})", id);
        GamePlayer gp = gamePlayerRepo.findOne(id);
        if (gp == null) {
            LOG.error("nenajdeny GamePlayer podla id {}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny GamePlayer id: " + id);
        }
        return gameConverter.gamePlayerEntityToDto(gp);
    }

    @Transactional
    public List<GamePlayerDto> findAllGamePlayers() {
        LOG.info("findAllGamePlayers");
        List<GamePlayerDto> l = new ArrayList<>();
        for (GamePlayer p : gamePlayerRepo.findAll()) {
            l.add(gameConverter.gamePlayerEntityToDto(p));
        }
        return l;
    }

    @Transactional
    public List<GamePlayerDto> getAllGamePlayersByGame(Integer gId) throws AppException {
        LOG.info("getAllGamePlayersByGame({})", gId);

        Game g = gameRepo.findOne(gId);
        if (g == null) {
            LOG.error("nenajdeny game podla id");
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny game id: " + gId);
        }
        List<GamePlayer> l = gamePlayerRepo.findByGame(g);
        List<GamePlayerDto> lDto = new ArrayList<>();

        for (GamePlayer gp : l) {
            lDto.add(gameConverter.gamePlayerEntityToDto(gp));
        }
        return lDto;

    }

    @Transactional
    public void deleteGamePlayer(Integer id) throws AppException {
        LOG.info("deleteGamePlayer({})", id);
        GamePlayer gp = gamePlayerRepo.findOne(id);

        if (gp == null) {
            LOG.error("nenajdeny gamePlayer na vymazanie id={}", id);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdeny gamePlayer id=" + id);
        }
        gamePlayerRepo.delete(gp);
    }

    @Transactional
    public GamePlayerDto editGamePlayer(GamePlayerDto updatedGp) throws AppException {
        LOG.info("editGamePlayer({})", updatedGp);
        if (updatedGp == null) {
            LOG.error("aktualizovany objekt je null");
            throw new AppException(HttpStatus.BAD_REQUEST, "zadany objekt je nespravne vyplneny");
        }

        GamePlayer gamePlayer = gameConverter.gamePlayerDtoToEntity(updatedGp);
        gamePlayer = gamePlayerRepo.saveAndFlush(gamePlayer);

        if (gamePlayer == null) {
            LOG.error("nepodarilo sa ulozit updateovaneho GamePlayera do db");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "GamePlayera sa nepodarilo aktualizovat");
        }
        return gameConverter.gamePlayerEntityToDto(gamePlayer);
    }

}
