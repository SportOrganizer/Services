/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.converter.game;

import com.so.core.controller.dto.season.penalty.PenaltyCustomDto;
import com.so.core.controller.dto.season.penalty.PenaltyDto;
import com.so.core.controller.dto.season.penalty.PenaltyTypeDto;
import com.so.core.controller.dto.season.penalty.SeasonTournamentPenaltySettingsDto;
import com.so.core.controller.dto.season.penalty.SeasonTournamentPenaltyTypeDto;
import com.so.core.exception.AppException;
import com.so.dal.core.model.Sports;
import com.so.dal.core.model.game.Penalty;
import com.so.dal.core.model.game.PenaltyType;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.model.season.SeasonTournamentPenaltySettings;
import com.so.dal.core.model.season.SeasonTournamentPenaltyType;
import com.so.dal.core.repository.SportsRepository;
import com.so.dal.core.repository.game.PenaltyRepository;
import com.so.dal.core.repository.game.PenaltyTypeRepository;
import com.so.dal.core.repository.season.SeasonTournamentPenaltySettingsRepository;
import com.so.dal.core.repository.season.SeasonTournamentPenaltyTypeRepository;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
import com.so.dal.floorball.model.game.SportFlorbalGameActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author peter
 */
@Service
public class PenaltyConverter {

    private final static Logger LOG = LoggerFactory.getLogger(PenaltyConverter.class);

    @Autowired
    private SportsRepository sportRepo;

    @Autowired
    private PenaltyRepository penaltyRepo;

    @Autowired
    private PenaltyTypeRepository penaltyTypeRepo;

    @Autowired
    private SeasonTournamentPenaltyTypeRepository stPenaltyTypeRepo;

    @Autowired
    private SeasonTournamentRepository stRepo;

    @Autowired
    private SeasonTournamentPenaltySettingsRepository stPenaltySettingsRepo;
    
    public PenaltyCustomDto penaltyCustomEntityToDto(SeasonTournamentPenaltySettings entity){
        PenaltyCustomDto dto = new PenaltyCustomDto();
        dto.setId(entity.getId());
        dto.setIsPlayerDown(entity.getSeasonTournamentPenaltyType().getIsPlayerDown());
        dto.setNamePenalty(entity.getPenalty().getName());
        dto.setNamePenaltyType(entity.getSeasonTournamentPenaltyType().getPenaltyType().getName());
        dto.setPenaltyDuration(entity.getSeasonTournamentPenaltyType().getPenaltyDuration());
        dto.setPenaltyStatsDuration(entity.getSeasonTournamentPenaltyType().getPenaltyStatsDuration());
        dto.setShortNamePenalty(entity.getPenalty().getShortName());
        
        return dto;
        
    }
    

    public PenaltyDto penaltyEntityToDto(Penalty entity) {
        PenaltyDto dto = new PenaltyDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setShortName(entity.getShortName());

        if (entity.getSports() != null) {
            dto.setSportsId(entity.getSports().getId());
        }
        return dto;
    }

    public Penalty penaltyDtoToEntity(PenaltyDto dto) throws AppException {
        Penalty entity;
        if (dto.getId() != null) {
            entity = penaltyRepo.findOne(dto.getId());
            if (entity == null) {
                LOG.error("nenajdena penalty s id {}", dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "nenajdena penalty s id: " + dto.getId());
            }
        } else {
            entity = new Penalty();
        }

        entity.setName(dto.getName());
        entity.setShortName(dto.getShortName());

        if (dto.getSportsId() != null) {
            Sports s = sportRepo.findOne(dto.getSportsId());
            if (s == null) {
                LOG.error("nenajdeny sport s id {}", dto.getSportsId());
                throw new AppException(HttpStatus.BAD_REQUEST, "nenajdena penalty s id: " + dto.getSportsId());
            }
            entity.setSports(s);
        }

        return entity;
    }

    public PenaltyTypeDto penaltyTypeEntityToDto(PenaltyType entity) {
        PenaltyTypeDto dto = new PenaltyTypeDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public PenaltyType penaltyTypeDtoToEntity(PenaltyTypeDto dto) throws AppException {
        PenaltyType entity;

        if (dto.getId() != null) {
            entity = penaltyTypeRepo.findOne(dto.getId());
            if (entity == null) {
                LOG.error("nenajdena penaltyType s id {}", dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "nenajdena penaltyType s id: " + dto.getId());
            }
        } else {
            entity = new PenaltyType();
        }
        entity.setName(dto.getName());

        return entity;
    }

    public SeasonTournamentPenaltyTypeDto stPenaltyTypeEntityToDto(SeasonTournamentPenaltyType entity) {
        SeasonTournamentPenaltyTypeDto dto = new SeasonTournamentPenaltyTypeDto();

        dto.setId(entity.getId());
        dto.setIsPlayerDown(entity.getIsPlayerDown());
        dto.setPenaltyDuration(entity.getPenaltyDuration());
        dto.setPenaltyStatsDuration(entity.getPenaltyStatsDuration());
        if (entity.getPenaltyType() != null) {
            dto.setPenaltyType(penaltyTypeEntityToDto(entity.getPenaltyType()));
        }

        if (entity.getSeasonTournament() != null) {
            dto.setSeasonTournament(entity.getSeasonTournament().getId());
        }

        return dto;
    }

    public SeasonTournamentPenaltyType stPenaltyTypeDtoToEntity(SeasonTournamentPenaltyTypeDto dto) throws AppException {
        SeasonTournamentPenaltyType entity;

        if (dto.getId() != null) {
            entity = stPenaltyTypeRepo.findOne(dto.getId());
            if (entity == null) {
                LOG.error("nenajdena stPenaltyType s id {}", dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "nenajdena stPenaltyType s id: " + dto.getId());
            }
        } else {
            entity = new SeasonTournamentPenaltyType();
        }

        entity.setIsPlayerDown(dto.getIsPlayerDown());
        entity.setPenaltyDuration(dto.getPenaltyDuration());
        entity.setPenaltyStatsDuration(dto.getPenaltyStatsDuration());

        if (dto.getPenaltyType()!= null) {
            if(dto.getPenaltyType().getId()!=null){
            PenaltyType p = penaltyTypeRepo.findOne(dto.getPenaltyType().getId());
            if (p == null) {
                LOG.error("nenajdeny penaltyType s id {}", dto.getPenaltyType().getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny penaltyType s id: " + dto.getPenaltyType().getId());
            }
            entity.setPenaltyType(p);
            }
        }
        
        if (dto.getSeasonTournament() != null) {
            SeasonTournament st = stRepo.findOne(dto.getSeasonTournament());
            if (st == null) {
                LOG.error("nenajdeny seasonTournament s id {}", dto.getSeasonTournament());
                throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny seasonTournament s id: " + dto.getSeasonTournament());
            }
            entity.setSeasonTournament(st);
        }
        return entity;
    }

    public SeasonTournamentPenaltySettingsDto stPenaltySettingsEntityToDto(SeasonTournamentPenaltySettings entity) {
        SeasonTournamentPenaltySettingsDto dto = new SeasonTournamentPenaltySettingsDto();

        dto.setId(entity.getId());
        if (entity.getSeasonTournament() != null) {
            dto.setSeasonTournamentId(entity.getSeasonTournament().getId());
        }
        if (entity.getPenalty() != null) {
            dto.setPenalty(penaltyEntityToDto(entity.getPenalty()));
        }
        if (entity.getSeasonTournamentPenaltyType() != null) {
            dto.setSeasonTournamentPenaltyType(stPenaltyTypeEntityToDto(entity.getSeasonTournamentPenaltyType()));
        }

        return dto;
    }

    public SeasonTournamentPenaltySettings stPenaltySettingsDtoToEntity(SeasonTournamentPenaltySettingsDto dto) throws AppException {
        SeasonTournamentPenaltySettings entity;
        if (dto.getId() != null) {
            entity = stPenaltySettingsRepo.findOne(dto.getId());
            if (entity == null) {
                LOG.error("nenajdena stPenaltySettings s id {}", dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "nenajdena stPenaltySettings s id: " + dto.getId());
            }
        } else {
            entity = new SeasonTournamentPenaltySettings();
        }

        if (dto.getSeasonTournamentId() != null) {
            SeasonTournament st = stRepo.findOne(dto.getSeasonTournamentId());
            if (st == null) {
                LOG.error("nenajdeny seasonTournament s id {}", dto.getSeasonTournamentId());
                throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny seasonTournament s id: " + dto.getSeasonTournamentId());
            }
            entity.setSeasonTournament(st);
        }

        if (dto.getPenalty() != null) {
            if (dto.getPenalty().getId() != null) {
                Penalty p = penaltyRepo.findOne(dto.getPenalty().getId());
                if (p == null) {
                    LOG.error("nenajdena penalty s id {}", dto.getPenalty().getId());
                    throw new AppException(HttpStatus.BAD_REQUEST, "nenajdena penalty s id: " + dto.getPenalty().getId());
                }
                entity.setPenalty(p);
            }
        }

        if (dto.getSeasonTournamentPenaltyType() != null) {
            if (dto.getSeasonTournamentPenaltyType().getId() != null) {
                SeasonTournamentPenaltyType p = stPenaltyTypeRepo.findOne(dto.getSeasonTournamentPenaltyType().getId());
                if (p == null) {
                    LOG.error("nenajdena stPenaltyType s id {}", dto.getSeasonTournamentPenaltyType().getId());
                    throw new AppException(HttpStatus.BAD_REQUEST, "nenajdena stPenaltyType s id: " + dto.getSeasonTournamentPenaltyType().getId());
                }
                entity.setSeasonTournamentPenaltyType(p);
            }
        }

        return entity;
    }

}
