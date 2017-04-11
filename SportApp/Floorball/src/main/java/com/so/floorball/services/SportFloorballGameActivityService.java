/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.floorball.services;

import com.so.core.controller.dto.game.GameDto;
import com.so.core.exception.AppException;
import com.so.core.services.PersonService;
import com.so.dal.core.repository.game.GameRepository;
import com.so.dal.floorball.model.game.SportFlorbalGameActivity;
import com.so.dal.floorball.model.game.SportFlorbalGameShots;
import com.so.dal.floorball.repository.game.SportFlorbalGameActivityRepository;
import com.so.dal.floorball.repository.game.SportFlorbalGameShotsRepository;
import com.so.floorball.controller.converter.SportFloorballGameActivityConverter;
import com.so.floorball.controller.converter.SportFloorballGameShotsConverter;
import com.so.floorball.controller.floorballGame.dto.RequestSportFloorballGameActivityDto;
import com.so.floorball.controller.floorballGame.dto.ResponseSportFloorballGameActivityDto;
import com.so.floorball.controller.floorballGame.dto.SportFloorballGameShotsDto;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kristián Kačinetz
 */
@Service
public class SportFloorballGameActivityService {
    
    private final static Logger LOG = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    SportFloorballGameActivityConverter converter;
    
    @Autowired
    SportFlorbalGameActivityRepository sfgaRepo;
    
    @Autowired
    SportFloorballGameShotsConverter shotsConverter;
    
    @Autowired
    SportFlorbalGameShotsRepository shotsRepo;
    

    @Autowired
    GameRepository gameRepo;
    
    @Transactional
    public ResponseSportFloorballGameActivityDto addSportFloorballGameActivity(RequestSportFloorballGameActivityDto dto) throws AppException {
        LOG.info("addSportFloorballGameActivity()");
        
        if(dto.getIdGame() == null || dto.getIdCompetitorTeam() == null || dto.getIdGamePeriod() == null || dto.getGameTime() == null || dto.getRealTime() == null){
            LOG.error("parameter idGame, idCompetitorTeam, idGamePeriod, gameTime, realTime nemoze byt null: {}, {}, {}, {}, {}", dto.getIdGame(), dto.getIdCompetitorTeam(), dto.getIdGamePeriod(), dto.getGameTime(), dto.getRealTime());
            throw new AppException(HttpStatus.BAD_REQUEST, "nie je zadany povinny parameter");
        }
        
        SportFlorbalGameActivity a = converter.sportFloorballGameActivityDtoToEntity(dto);
        a = sfgaRepo.saveAndFlush(a);

        if (a == null) {
            LOG.error("SportFloorballGameActivity sa neulozila do databazy: {}", dto.getId());
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "SportFloorballGameActivity s ID:" + dto.getId() + " sa neulozil do databazy");
        }

        return converter.sportFloorballGameActivityEntityToDto(a);

    }
    
    @Transactional
    public List<ResponseSportFloorballGameActivityDto> findAllActivities() {
        LOG.info("findAllActivities()");

        List<SportFlorbalGameActivity> lg = sfgaRepo.findAll();
        List<ResponseSportFloorballGameActivityDto> l = new ArrayList<>();

        for (SportFlorbalGameActivity g : lg) {
            l.add(converter.sportFloorballGameActivityEntityToDto(g));
        }
        return l;
    }
    
    @Transactional
    public List<ResponseSportFloorballGameActivityDto> findAllActivitiesByGame(Integer idGame) {
        LOG.info("findAllActivitiesByGame()");

        List<SportFlorbalGameActivity> lg = sfgaRepo.findByGame(gameRepo.findOne(idGame));
        List<ResponseSportFloorballGameActivityDto> l = new ArrayList<>();

        for (SportFlorbalGameActivity g : lg) {
            l.add(converter.sportFloorballGameActivityEntityToDto(g));
        }
        return l;
    }

    @Transactional
    public ResponseSportFloorballGameActivityDto findOneActivity(Integer id) throws AppException {
        LOG.info("findGame({})", id);

        SportFlorbalGameActivity g = sfgaRepo.findOne(id);

        if (g == null) {
            LOG.error("nenajdena activita s id:{}", id);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdena activita s id:" + id);
        }
        return converter.sportFloorballGameActivityEntityToDto(g);
    }
    
    @Transactional
    public void deleteSportFlorbalGameActivity(Integer id) throws AppException {
        LOG.info("deleteSportFlorbalGameActivity({})", id);
        SportFlorbalGameActivity p = sfgaRepo.findOne(id);

        if (p == null) {
            LOG.error("nenajdena SportFlorbalGameActivity na vymazanie id={}", id);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdena SportFlorbalGameActivity id=" + id);
        }
        sfgaRepo.delete(p);
    }
    
    @Transactional
    public ResponseSportFloorballGameActivityDto updateActivity(RequestSportFloorballGameActivityDto updated) throws AppException {
        LOG.info("update()");

        if (updated == null) {
            LOG.error("aktualizovany objekt je null");
            throw new AppException(HttpStatus.BAD_REQUEST, "zadany objekt je nespravne vyplneny");
        }
        SportFlorbalGameActivity a = converter.sportFloorballGameActivityDtoToEntity(updated);
        a = sfgaRepo.saveAndFlush(a);

        if (a == null) {
            LOG.error("nepodarilo sa ulozit SportFlorbalGameActivity do db");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "nepodarilo sa ulozit SportFlorbalGameActivity do db");
        }
        return converter.sportFloorballGameActivityEntityToDto(a);
    }
    
    @Transactional
    public SportFloorballGameShotsDto addSportFloorballGameActivity(SportFloorballGameShotsDto dto) throws AppException {
        LOG.info("addSportFloorballGameActivity()");
        
        if(dto.getIdGame() == null || dto.getIdTeam() == null || dto.getIdPeriod() == null || dto.getCount() == null){
            LOG.error("parameter idGame, idCompetitorTeam, idGamePeriod, count nemoze byt null: {}, {}, {}, {}", dto.getIdGame(), dto.getIdTeam(), dto.getIdPeriod(), dto.getCount());
            throw new AppException(HttpStatus.BAD_REQUEST, "nie je zadany povinny parameter");
        }
        
        SportFlorbalGameShots a = shotsConverter.shotsDtoToEntity(dto);
        a = shotsRepo.saveAndFlush(a);

        if (a == null) {
            LOG.error("SportFlorbalGameShots sa neulozila do databazy: {}", dto.getId());
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "SportFlorbalGameShots s ID:" + dto.getId() + " sa neulozil do databazy");
        }

        return shotsConverter.shotsEntityToDto(a);

    }
    
    @Transactional
    public List<SportFloorballGameShotsDto> findAllShots() {
        LOG.info("findAllShots()");

        List<SportFlorbalGameShots> lg = shotsRepo.findAll();
        List<SportFloorballGameShotsDto> l = new ArrayList<>();

        for (SportFlorbalGameShots g : lg) {
            l.add(shotsConverter.shotsEntityToDto(g));
        }
        return l;
    }

    @Transactional
    public SportFloorballGameShotsDto findOneShot(Integer id) throws AppException {
        LOG.info("findOneShot({})", id);

        SportFlorbalGameShots g = shotsRepo.findOne(id);

        if (g == null) {
            LOG.error("nenajdena shot s id:{}", id);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdena shot s id:" + id);
        }
        return shotsConverter.shotsEntityToDto(g);
    }
    
    @Transactional
    public void deleteSportFlorbalGameShots(Integer id) throws AppException {
        LOG.info("deleteSportFlorbalGameShots({})", id);
        SportFlorbalGameShots p = shotsRepo.findOne(id);

        if (p == null) {
            LOG.error("nenajdena SportFlorbalGameShots na vymazanie id={}", id);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdena SportFlorbalGameShots id=" + id);
        }
        shotsRepo.delete(p);
    }
    
    @Transactional
    public SportFloorballGameShotsDto updateActivity(SportFloorballGameShotsDto updated) throws AppException {
        LOG.info("update()");

        if (updated == null) {
            LOG.error("aktualizovany objekt je null");
            throw new AppException(HttpStatus.BAD_REQUEST, "zadany objekt je nespravne vyplneny");
        }
        SportFlorbalGameShots a = shotsConverter.shotsDtoToEntity(updated);
        a = shotsRepo.saveAndFlush(a);

        if (a == null) {
            LOG.error("nepodarilo sa ulozit SportFlorbalGameShots do db");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "nepodarilo sa ulozit SportFlorbalGameShots do db");
        }
        return shotsConverter.shotsEntityToDto(a);
    }
    
}
