/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.floorball.controller.converter;

import com.so.core.controller.converter.DateConverter;
import com.so.core.controller.converter.game.GameConverter;
import com.so.core.controller.converter.game.PenaltyConverter;
import com.so.core.exception.AppException;
import com.so.dal.core.model.game.CompetitorTeam;
import com.so.dal.core.model.game.Game;
import com.so.dal.core.model.game.GamePlayer;
import com.so.dal.core.model.season.SeasonTournamentPenaltySettings;
import com.so.dal.core.model.season.SeasonTournamentPeriod;
import com.so.dal.core.repository.game.CompetitorTeamRepository;
import com.so.dal.core.repository.game.GamePlayerRepository;
import com.so.dal.core.repository.game.GameRepository;
import com.so.dal.core.repository.season.SeasonTournamentPenaltySettingsRepository;
import com.so.dal.core.repository.season.SeasonTournamentPeriodRepository;
import com.so.dal.floorball.model.game.SportFlorbalGameActivity;
import com.so.dal.floorball.model.game.SportFlorbalGameActivityType;
import com.so.dal.floorball.model.game.SportFlorbalGoalType;
import com.so.dal.floorball.repository.game.SportFlorbalGameActivityRepository;
import com.so.dal.floorball.repository.game.SportFlorbalGameActivityTypeRepository;
import com.so.dal.floorball.repository.game.SportFlorbalGoalTypeRepository;
import com.so.floorball.controller.floorballGame.dto.RequestSportFloorballGameActivityDto;
import com.so.floorball.controller.floorballGame.dto.ResponseSportFloorballGameActivityDto;
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
public class SportFloorballGameActivityConverter {

    private final static Logger LOG = LoggerFactory.getLogger(GameConverter.class);
    
    @Autowired
    DateConverter dateConverter;
    
    @Autowired
    GameConverter gameConverter;
    
    @Autowired
    PenaltyConverter penaltyConverter;
    
    @Autowired
    SportFlorbalGameActivityRepository sfgaRepo;
    
    @Autowired
    SportFloorballGoalTypeConverter sfgtConverter;
    
    @Autowired
    CompetitorTeamRepository competitorTeamRepo;
    
    @Autowired
    GameRepository gameRepo;
    
    @Autowired
    SportFlorbalGameActivityTypeRepository sfgatRepo;
    
    @Autowired
    SeasonTournamentPeriodRepository gamePeriodRepo;
    
    @Autowired
    GamePlayerRepository gamePlayerRepo;
    
    @Autowired
    SportFlorbalGoalTypeRepository sfgtRepo;
    
    @Autowired
    SeasonTournamentPenaltySettingsRepository stpsRepo;
    
    public ResponseSportFloorballGameActivityDto sportFloorballGameActivityEntityToDto(SportFlorbalGameActivity entity) {
        
        ResponseSportFloorballGameActivityDto dto = new ResponseSportFloorballGameActivityDto();
        
        dto.setId(entity.getId());
        dto.setIdGame(entity.getGame().getId());
        dto.setIdCompetitorTeam(entity.getCompetitorTeam().getId());
        dto.setIdGamePeriod(entity.getSeasonTournamentPeriod().getId());
        dto.setGameTime(dateConverter.dateTimeToString(entity.getGameTime()));
        dto.setRealTime(dateConverter.dateTimeToString(entity.getRealTime()));
        dto.setIdActivityType(entity.getSportFlorbalGameActivityType().getId());
        dto.setNewScoreAway(entity.getNewScoreAway());
        dto.setNewScoreHome(entity.getNewScoreHome());
        dto.setPenaltySeconds(dateConverter.dateTimeToString(entity.getPenaltySeconds()));
        
        if (entity.getGamePlayerByIdGoalPlayer() != null) {
            dto.setGoalPlayer(gameConverter.gamePlayerEntityToDto(entity.getGamePlayerByIdGoalPlayer()));
        }
        
        if (entity.getGamePlayerByIdAssistPlayer() != null) {
            dto.setAssistPlayer(gameConverter.gamePlayerEntityToDto(entity.getGamePlayerByIdAssistPlayer()));
        }
        
        if (entity.getGamePlayerByIdAssist2player() != null) {
            dto.setAssist2player(gameConverter.gamePlayerEntityToDto(entity.getGamePlayerByIdAssist2player()));
        }
        
        if (entity.getGamePlayerByIdPenaltyPlayer() != null) {
            dto.setPenaltyPlayer(gameConverter.gamePlayerEntityToDto(entity.getGamePlayerByIdPenaltyPlayer()));
        }
        
        if (entity.getSportFlorbalGoalType() != null) {
            dto.setGoalType(sfgtConverter.sportFloorbalGoalTypeEntityToDto(entity.getSportFlorbalGoalType()));
        }
        
        if (entity.getSeasonTournamentPenaltySettings() != null) {
            dto.setPenaltyType(penaltyConverter.penaltyCustomEntityToDto(entity.getSeasonTournamentPenaltySettings()));
        }
        
        return dto;
    }
    
    public SportFlorbalGameActivity sportFloorballGameActivityDtoToEntity(RequestSportFloorballGameActivityDto dto) throws AppException {
        SportFlorbalGameActivity entity;
        
        if (dto.getId() != null) {
            entity = sfgaRepo.findOne(dto.getId());
            if (entity == null) {
                LOG.error("nenajdena SportFlorbalGameActivity podla id: {}", dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje SportFlorbalGameActivity s id: " + dto.getId());
            }
        } else {
            entity = new SportFlorbalGameActivity();
        }
        
        if (dto.getIdGame() != null) {           
            Game t = gameRepo.findOne(dto.getIdGame());
            if (t == null) {
                LOG.error("neexistuje game s id {}", dto.getIdGame());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje game s id: " + dto.getIdGame());
            } else {
                entity.setGame(t);
            }          
        }
        
        if (dto.getIdCompetitorTeam() != null) {           
            CompetitorTeam t = competitorTeamRepo.findOne(dto.getIdCompetitorTeam());
            if (t == null) {
                LOG.error("neexistuje competitorTeam s id {}", dto.getIdCompetitorTeam());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje competitorTeam s id: " + dto.getIdCompetitorTeam());
            } else {
                entity.setCompetitorTeam(t);
            }          
        }
        
        if (dto.getIdGamePeriod() != null) {           
            SeasonTournamentPeriod t = gamePeriodRepo.findOne(dto.getIdGamePeriod());
            if (t == null) {
                LOG.error("neexistuje gamePeriod s id {}", dto.getIdGamePeriod());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje gamePeriod s id: " + dto.getIdGamePeriod());
            } else {
                entity.setSeasonTournamentPeriod(t);
            }          
        }
        
        entity.setGameTime(dateConverter.stringToTime(dto.getGameTime()));
        entity.setRealTime(dateConverter.stringToDateTime(dto.getRealTime()));
        
        if (dto.getIdActivityType() != null) {           
            SportFlorbalGameActivityType t = sfgatRepo.findOne(dto.getIdActivityType());
            if (t == null) {
                LOG.error("neexistuje SportFlorbalGameActivityType s id {}", dto.getIdActivityType());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje SportFlorbalGameActivityType s id: " + dto.getIdActivityType());
            } else {
                entity.setSportFlorbalGameActivityType(t);
            }          
        }
        
        if (dto.getIdGoalPlayer() != null) {           
            GamePlayer t = gamePlayerRepo.findOne(dto.getIdGoalPlayer());
            if (t == null) {
                LOG.error("neexistuje GamePlayer s id {}", dto.getIdGoalPlayer());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje GamePlayer (GoalPlayer) s id: " + dto.getIdGoalPlayer());
            } else {
                entity.setGamePlayerByIdGoalPlayer(t);
            }          
        }
        
        if (dto.getIdAssistPlayer() != null) {           
            GamePlayer t = gamePlayerRepo.findOne(dto.getIdAssistPlayer());
            if (t == null) {
                LOG.error("neexistuje GamePlayer s id {}", dto.getIdAssistPlayer());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje GamePlayer (AssistPlayer) s id: " + dto.getIdAssistPlayer());
            } else {
                entity.setGamePlayerByIdAssistPlayer(t);
            }          
        }
        
        if (dto.getIdAssist2player() != null) {           
            GamePlayer t = gamePlayerRepo.findOne(dto.getIdAssist2player());
            if (t == null) {
                LOG.error("neexistuje GamePlayer s id {}", dto.getIdAssist2player());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje GamePlayer (Assist2Player) s id: " + dto.getIdAssist2player());
            } else {
                entity.setGamePlayerByIdAssist2player(t);
            }          
        }
        
        entity.setNewScoreAway(dto.getNewScoreAway());
        entity.setNewScoreHome(dto.getNewScoreHome());
        
        if (dto.getIdGoalType() != null) {           
            SportFlorbalGoalType t = sfgtRepo.findOne(dto.getIdGoalType());
            if (t == null) {
                LOG.error("neexistuje GoalType s id {}", dto.getIdGoalType());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje GoalType s id: " + dto.getIdGoalType());
            } else {
                entity.setSportFlorbalGoalType(t);
            }          
        }
        
        if (dto.getIdPenaltyPlayer() != null) {           
            GamePlayer t = gamePlayerRepo.findOne(dto.getIdPenaltyPlayer());
            if (t == null) {
                LOG.error("neexistuje GamePlayer s id {}", dto.getIdPenaltyPlayer());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje GamePlayer (PenaltyPlayer) s id: " + dto.getIdPenaltyPlayer());
            } else {
                entity.setGamePlayerByIdPenaltyPlayer(t);
            }          
        }
        
        if (dto.getIdPenaltyType() != null) {           
            SeasonTournamentPenaltySettings t = stpsRepo.findOne(dto.getIdPenaltyType());
            if (t == null) {
                LOG.error("neexistuje PenaltyType s id {}", dto.getIdPenaltyType());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje PenaltyType s id: " + dto.getIdPenaltyType());
            } else {
                entity.setSeasonTournamentPenaltySettings(t);
            }          
        }
        
        entity.setPenaltySeconds(dateConverter.stringToDateTime(dto.getPenaltySeconds()));
        
        return entity;
    }
    
}
