/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.floorball.controller.converter;

import com.so.core.exception.AppException;
import com.so.dal.core.model.game.CompetitorTeam;
import com.so.dal.core.model.game.Game;
import com.so.dal.core.model.season.SeasonTournamentPeriod;
import com.so.dal.core.repository.game.CompetitorTeamRepository;
import com.so.dal.core.repository.game.GameRepository;
import com.so.dal.core.repository.season.SeasonTournamentPeriodRepository;
import com.so.dal.floorball.model.game.SportFlorbalGameShots;
import com.so.dal.floorball.model.game.SportFlorbalGoalType;
import com.so.dal.floorball.repository.game.SportFlorbalGameShotsRepository;
import com.so.floorball.controller.floorballGame.dto.SportFloorballGameShotsDto;
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
public class SportFloorballGameShotsConverter {
    private final static Logger LOG = LoggerFactory.getLogger(SportFloorballGameShotsConverter.class);
    
    @Autowired
    SportFlorbalGameShotsRepository sfgsRepo;
    
    @Autowired
    GameRepository gameRepo;
    
    @Autowired
    CompetitorTeamRepository competitorTeamRepo;
    
    @Autowired
    SeasonTournamentPeriodRepository periodRepo;
    
    
    public SportFloorballGameShotsDto shotsEntityToDto(SportFlorbalGameShots entity){
        SportFloorballGameShotsDto dto = new SportFloorballGameShotsDto();
        
        dto.setId(entity.getId());
        dto.setIdGame(entity.getGame().getId());
        dto.setIdTeam(entity.getCompetitorTeam().getId());
        dto.setIdPeriod(entity.getSeasonTournamentPeriod().getId());
        dto.setCount(entity.getCount());
        
        return dto;
    }
    
    public SportFlorbalGameShots shotsDtoToEntity(SportFloorballGameShotsDto dto) throws AppException{
        SportFlorbalGameShots entity;
        if (dto.getId() != null) {
            entity = sfgsRepo.findOne(dto.getId());
            if (entity == null) {
                LOG.error("nenajdeny SportFlorbalGameShots podla id: {}", dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje SportFlorbalGameShots s id: " + dto.getId());
            }
        } else {
            entity = new SportFlorbalGameShots();
        }
        
        if (dto.getIdGame() != null) {           
            Game t = gameRepo.findOne(dto.getIdGame());
            if (t == null) {
                LOG.error("neexistuje Game s id {}", dto.getIdGame());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje Game s id: " + dto.getIdGame());
            } else {
                entity.setGame(t);
            }          
        }
        
        if (dto.getIdTeam() != null) {           
            CompetitorTeam t = competitorTeamRepo.findOne(dto.getIdTeam());
            if (t == null) {
                LOG.error("neexistuje CompetitorTeam s id {}", dto.getIdTeam());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje CompetitorTeam s id: " + dto.getIdTeam());
            } else {
                entity.setCompetitorTeam(t);
            }          
        }
        
        if (dto.getIdPeriod() != null) {           
            SeasonTournamentPeriod t = periodRepo.findOne(dto.getIdPeriod());
            if (t == null) {
                LOG.error("neexistuje SeasonTournamentPeriod s id {}", dto.getIdPeriod());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje SeasonTournamentPeriod s id: " + dto.getIdPeriod());
            } else {
                entity.setSeasonTournamentPeriod(t);
            }          
        }
        
        entity.setCount(dto.getCount());
        
        return entity;
  
    }
    
}
