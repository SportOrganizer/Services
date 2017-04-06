/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.game;

import com.so.core.controller.converter.game.GameConverter;
import com.so.core.controller.dto.game.GameDto;
<<<<<<< HEAD
import com.so.core.controller.dto.game.GamePlayerDto;
=======
import com.so.core.controller.dto.game.GamePlayerRequestDto;
>>>>>>> create Person, oprava DB
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
    public List<GameDto> findAllGames() {
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
    public List<GameDto> findBySeasonTournament(Integer stId) throws AppException {
        LOG.info("findBySeasonTournament({})", stId);
        SeasonTournament st = stRepo.findOne(stId);
        if (st == null) {
            LOG.error("nenajdeny seasonTournament s id:", stId);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdeny swasonTournament s id:" + stId);
        }
        List<Game> lg = gameRepo.findAllBySeasonTournamentOrderByStartTimeAsc(st);
        List<GameDto> l = new ArrayList<>();
        
        for (Game g : lg) {
            l.add(gameConverter.gameEntityToDto(g));
        }
        return l;
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

    


    public GameDto editGame(GameDto updatedGame) throws AppException {

        Game game = gameConverter.gameDtoToEntity(updatedGame);

        Game savedGame = gameRepo.saveAndFlush(game);

        if (savedGame == null) {
            LOG.error("nepodarilo sa ulozit updateovanu Game do db");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "Game sa nepodarilo aktualizovat");
        }
        return gameConverter.gameEntityToDto(savedGame);

    }
    
    public GamePlayerResponseDto createGamePlayer(GamePlayerRequestDto gp){
        
    }

}
