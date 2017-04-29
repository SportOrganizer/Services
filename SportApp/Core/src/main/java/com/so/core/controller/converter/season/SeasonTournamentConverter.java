/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.converter.season;

import com.so.core.controller.converter.DateConverter;
import com.so.core.controller.converter.game.CompetitorEntityConverter;
import com.so.core.controller.converter.game.GameConverter;
import com.so.core.controller.dto.ResourceDto;
import com.so.core.controller.dto.game.CompetitorTeamDto;
import com.so.core.controller.dto.season.SeasonTournamentDTO;
import com.so.core.controller.dto.season.SeasonTournamentGroupDTO;
import com.so.core.controller.dto.season.SeasonTournamentLocationDTO;
import com.so.core.controller.dto.season.SeasonTournamentPeriodDTO;
import com.so.core.controller.dto.season.SeasonTournamentRoundDTO;
import com.so.core.controller.dto.season.SeasonTournamentSettingsDTO;
import com.so.core.controller.dto.season.SeasonTournamentSettingsTypeDto;
import com.so.core.exception.AppException;
import com.so.dal.core.model.Resource;
import com.so.dal.core.model.Tournament;
import com.so.dal.core.model.game.CompetitorTeam;
import com.so.dal.core.model.season.Season;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.model.season.SeasonTournamentGroup;
import com.so.dal.core.model.season.SeasonTournamentLocation;
import com.so.dal.core.model.season.SeasonTournamentPeriod;
import com.so.dal.core.model.season.SeasonTournamentRound;
import com.so.dal.core.model.season.SeasonTournamentSettings;
import com.so.dal.core.model.season.SeasonTournamentSettingsType;
import com.so.dal.core.repository.ResourceRepository;
import com.so.dal.core.repository.TournamentRepository;
import com.so.dal.core.repository.season.SeasonRepository;
import com.so.dal.core.repository.season.SeasonTournamentGroupRepository;
import com.so.dal.core.repository.season.SeasonTournamentLocationRepository;
import com.so.dal.core.repository.season.SeasonTournamentPeriodRepository;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
import com.so.dal.core.repository.season.SeasonTournamentRoundRepository;
import com.so.dal.core.repository.season.SeasonTournamentSettingsRepository;
import com.so.dal.core.repository.season.SeasonTournamentSettingsTypeRepository;
import java.util.ArrayList;
import java.util.List;
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
public class SeasonTournamentConverter {
    
    private final static Logger LOG = LoggerFactory.getLogger(SeasonTournamentConverter.class);
    
    @Autowired
    SeasonTournamentRepository seasonTournamentRepo;
    @Autowired
    SeasonRepository seasonRepo;
    @Autowired
    TournamentRepository tournamentRepo;
    @Autowired
    ResourceRepository resourceRepo;
    @Autowired
    private SeasonTournamentRoundRepository stRoundRepo;
    @Autowired
    private SeasonTournamentLocationRepository stLocationRepo;
    @Autowired
    private SeasonTournamentGroupRepository stGroupRepo;
    @Autowired
    private DateConverter dateConverter;
    @Autowired
    private SeasonTournamentPeriodRepository stPeriodRepo;
    @Autowired
    private SeasonTournamentSettingsTypeRepository stSettingsTypeRepo;
    @Autowired
    private SeasonTournamentSettingsRepository stSettingsRepo;
    @Autowired
    private GameConverter gameConverter;
    @Autowired
    private CompetitorEntityConverter competitorConverter;
    
    public SeasonTournamentDTO entityToDto(SeasonTournament entity) throws AppException {
        
        if (entity == null) {
            LOG.error("entity je null");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "entity v konvertore SeasonTournament je null");
        }
        
        SeasonTournamentDTO dto = new SeasonTournamentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSeasonId(entity.getSeason().getId());
        dto.setTournamentId(entity.getTournament().getId());
        if (entity.getResource() != null) {
            dto.setLogo(new ResourceDto(entity.getResource().getId(), entity.getResource().getPath()));
        }
        
        if (!entity.getSeasonTournamentLocations().isEmpty()) {
            List<SeasonTournamentLocationDTO> stg = new ArrayList<>();
            for (SeasonTournamentLocation l : entity.getSeasonTournamentLocations()) {
                stg.add(locationEntityToDto(l));
            }
            dto.setSeasonTournamentLocation(stg);
        }
        
        if (!entity.getSeasonTournamentGroups().isEmpty()) {
            List<SeasonTournamentGroupDTO> stg = new ArrayList<>();
            for (SeasonTournamentGroup g : entity.getSeasonTournamentGroups()) {
                stg.add(groupEntityToDto(g));
            }
            dto.setSeasonTournamentGroups(stg);
        }
        
        if (!entity.getSeasonTournamentRounds().isEmpty()) {
            List<SeasonTournamentRoundDTO> str = new ArrayList<>();
            for (SeasonTournamentRound r : entity.getSeasonTournamentRounds()) {
                str.add(roundEntityToDto(r));
            }
            dto.setSeasonTournamentRounds(str);
        }
        
        if (!entity.getSeasonTournamentPeriods().isEmpty()) {
            List<SeasonTournamentPeriodDTO> stp = new ArrayList<>();
            for (SeasonTournamentPeriod p : entity.getSeasonTournamentPeriods()) {
                stp.add(stPeriodEntityToDto(p));
            }
            dto.setPeriods(stp);
        }
        
        return dto;
    }
    
    public SeasonTournament dtoToEntity(SeasonTournamentDTO dto) throws AppException {
        if (dto == null) {
            LOG.error("entity je null");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "dto v konvertore SeasonTournament je null");
        }
        SeasonTournament entity;
        
        if (dto.getId() != null) {
            entity = seasonTournamentRepo.findOne(dto.getId());
            
            if (entity == null) {
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje SeasonTournament s id=" + dto.getId());
            }
        } else {
            entity = new SeasonTournament();
        }
        
        if (dto.getSeasonId() != null) {
            Season season = seasonRepo.findOne(dto.getSeasonId());
            
            if (season != null) {
                entity.setSeason(season);
            } else {
                LOG.error("neexistuje Season s id: {}", dto.getSeasonId());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje Season s id:" + dto.getSeasonId());
            }
        }
        
        if (dto.getTournamentId() != null) {
            Tournament tournament = tournamentRepo.findOne(dto.getTournamentId());
            if (tournament == null) {
                LOG.error("neexistuje Tournament s id: {}", dto.getSeasonId());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje Tournament s id:" + dto.getTournamentId());
            } else {
                entity.setTournament(tournament);
            }
            
        }
        
        if (dto.getLogo() != null) {
            if (dto.getLogo().getId() != null) {
                Resource resource = resourceRepo.getOne(dto.getLogo().getId());
                
                if (resource == null) {
                    LOG.error("neexistuje Resource s id: {}", dto.getLogo().getId());
                    throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje Resource s id:" + dto.getLogo().getId());
                } else {
                    entity.setResource(resource);
                }
            }
        }
        entity.setName(dto.getName());
        return entity;
    }
    
    public SeasonTournamentLocationDTO locationEntityToDto(SeasonTournamentLocation entity) {
        
        SeasonTournamentLocationDTO dto = new SeasonTournamentLocationDTO();
        
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        if (entity.getSeasonTournament() != null) {
            dto.setSeasonTournamentId(entity.getSeasonTournament().getId());
        }
        return dto;
    }
    
    public SeasonTournamentLocation locationDtoToEntity(SeasonTournamentLocationDTO dto) throws AppException {
        
        SeasonTournamentLocation entity;
        
        if (dto.getId() != null) {
            entity = stLocationRepo.findOne(dto.getId());
            if (entity == null) {
                LOG.error("nenajdena stLocation s id {}", dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "nenajdena stLocation s id:" + dto.getId());
            }
        } else {
            entity = new SeasonTournamentLocation();
        }
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        if (dto.getSeasonTournamentId() != null) {
            SeasonTournament st = seasonTournamentRepo.findOne(dto.getSeasonTournamentId());
            if (st == null) {
                LOG.info("nenajdeny seasonTournament podla ID:" + dto.getSeasonTournamentId());
                throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "nenajdeny season tournament podla id: " + dto.getSeasonTournamentId());
            }
            entity.setSeasonTournament(st);
        }
        return entity;
    }
    
    public SeasonTournamentRoundDTO roundEntityToDto(SeasonTournamentRound entity) throws AppException {
        
        SeasonTournamentRoundDTO dto = new SeasonTournamentRoundDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        if (entity.getSeasonTournament() != null) {
            dto.setSeasonTournamentId(entity.getSeasonTournament().getId());
        }
//        if(ifShortGame){
//         if (entity.getGames() != null && !entity.getGames().isEmpty()) {
//            List<GameDto> lg = new ArrayList<>();
//            for (Game g : entity.getGames()) {
//                lg.add(gameConverter.gameEntityToDto2(g));
//            }
//            dto.setGames(lg);
//        }   
//        }else{
//        if (entity.getGames() != null && !entity.getGames().isEmpty()) {
//            List<GameDto> lg = new ArrayList<>();
//            for (Game g : entity.getGames()) {
//                lg.add(gameConverter.gameEntityToDto(g));
//            }
//            dto.setGames(lg);
//        }
//        }
        return dto;
    }
    
    public SeasonTournamentRound roundDtoToEntity(SeasonTournamentRoundDTO dto) throws AppException {
        
        SeasonTournamentRound entity;
        
        if (dto.getId() != null) {
            entity = stRoundRepo.findOne(dto.getId());
            if (entity == null) {
                throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny stRound s id: " + dto.getId());
            }
        } else {
            entity = new SeasonTournamentRound();
        }
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        if (dto.getSeasonTournamentId() != null) {
            SeasonTournament st = seasonTournamentRepo.findOne(dto.getSeasonTournamentId());
            if (st == null) {
                LOG.info("nenajdeny seasonTournament podla ID:" + dto.getSeasonTournamentId());
                throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "nenajdeny season tournament podla id: " + dto.getSeasonTournamentId());
            }
            entity.setSeasonTournament(st);
        }
        return entity;
    }
    
    public SeasonTournamentGroupDTO groupEntityToDto(SeasonTournamentGroup entity) {
        
        SeasonTournamentGroupDTO dto = new SeasonTournamentGroupDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        if (entity.getSeasonTournament() != null) {
            dto.setSeasonTournamentId(entity.getSeasonTournament().getId());
        }
        if (!entity.getCompetitorTeams().isEmpty()) {
            List<CompetitorTeamDto> ct = new ArrayList<>();
            for (CompetitorTeam t : entity.getCompetitorTeams()) {
                ct.add(competitorConverter.competitorTeamEntityToDto(t, true));
            }
            dto.setCompetitorTeams(ct);
        }
        return dto;
    }
    
    public SeasonTournamentGroup groupDtoToEntity(SeasonTournamentGroupDTO dto) throws AppException {
        
        SeasonTournamentGroup entity;
        if (dto.getId() != null) {
            entity = stGroupRepo.findOne(dto.getId());
            if (entity == null) {
                LOG.error("nenajdena stGroup s id {}", dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "nenajdena stGroup s id:" + dto.getId());
                
            }
        } else {
            entity = new SeasonTournamentGroup();
        }
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        if (dto.getSeasonTournamentId() != null) {
            SeasonTournament st = seasonTournamentRepo.findOne(dto.getSeasonTournamentId());
            if (st == null) {
                LOG.info("nenajdeny seasonTournament podla ID:" + dto.getSeasonTournamentId());
                throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "nenajdeny season tournament podla id: " + dto.getSeasonTournamentId());
            }
            entity.setSeasonTournament(st);
        }
        return entity;
    }
    
    public SeasonTournamentSettingsTypeDto stSettingsTypeEntityToDto(SeasonTournamentSettingsType entity) {
        SeasonTournamentSettingsTypeDto dto = new SeasonTournamentSettingsTypeDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
    
    public SeasonTournamentSettingsType stSettingsTypeDtoToEntity(SeasonTournamentSettingsTypeDto dto) throws AppException {
        SeasonTournamentSettingsType entity;
        
        if (dto.getId() != null) {
            entity = stSettingsTypeRepo.findOne(dto.getId());
            if (entity == null) {
                LOG.info("nenajdeny seasonTournamentSettingsType podla ID:" + dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny seasonTournamntSettingsType podla id: " + dto.getId());
            }
        } else {
            entity = new SeasonTournamentSettingsType();
        }
        entity.setName(dto.getName());
        
        return entity;
    }
    
    public SeasonTournamentSettingsDTO stSettingsEntityToDto(SeasonTournamentSettings entity) {
        SeasonTournamentSettingsDTO dto = new SeasonTournamentSettingsDTO();
        
        dto.setId(entity.getId());
        if (entity.getSeasonTournament() != null) {
            dto.setIdSeasonTournament(entity.getSeasonTournament().getId());
        }
        if (entity.getSeasonTournamentSettingsType() != null) {
            dto.setType(stSettingsTypeEntityToDto(entity.getSeasonTournamentSettingsType()));
        }
        dto.setValue(entity.getValue());
        
        return dto;
    }
    
    public SeasonTournamentSettings stSettingsDtoToEntity(SeasonTournamentSettingsDTO dto) throws AppException {
        SeasonTournamentSettings entity;
        
        if (dto.getId() != null) {
            entity = stSettingsRepo.findOne(dto.getId());
            if (entity == null) {
                LOG.info("nenajdeny seasonTournamentSettings podla ID:" + dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny seasonTournamntSettings podla id: " + dto.getId());
            }
        } else {
            entity = new SeasonTournamentSettings();
        }
        
        if (dto.getIdSeasonTournament() != null) {
            SeasonTournament st = seasonTournamentRepo.findOne(dto.getIdSeasonTournament());
            if (st == null) {
                LOG.info("nenajdeny seasonTournament podla ID:" + dto.getIdSeasonTournament());
                throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "nenajdeny season tournament podla id: " + dto.getIdSeasonTournament());
            }
            entity.setSeasonTournament(st);
        }
        
        if (dto.getType() != null) {
            if (dto.getType().getId() != null) {
                SeasonTournamentSettingsType st = stSettingsTypeRepo.findOne(dto.getType().getId());
                if (st == null) {
                    LOG.info("nenajdeny seasonTournamentYpe podla ID:" + dto.getType().getId());
                    throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "nenajdeny seasonTournamentSettingsType podla id: " + dto.getType().getId());
                }
                entity.setSeasonTournamentSettingsType(st);
            }
        }
        entity.setName(dto.getName());
        entity.setValue(dto.getValue());
        
        return entity;
    }
    
    public SeasonTournamentPeriodDTO stPeriodEntityToDto(SeasonTournamentPeriod entity) {
        SeasonTournamentPeriodDTO dto = new SeasonTournamentPeriodDTO();
        
        if (entity.getSeasonTournament() != null) {
            dto.setSeasonTournamentId(entity.getSeasonTournament().getId());
        }
        dto.setId(entity.getId());
        dto.setIsGoldPart(entity.getIsGoldPart());
        dto.setLength(dateConverter.timeToString(entity.getLength()));
        dto.setName(entity.getName());
        
        return dto;
    }
    
    public SeasonTournamentPeriod stPeriodDtoToEntity(SeasonTournamentPeriodDTO dto) throws AppException {
        SeasonTournamentPeriod entity;
        if (dto.getId() != null) {
            entity = stPeriodRepo.findOne(dto.getId());
            if (entity == null) {
                LOG.error("nenajdena stPeriod s id {}", dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "nenajdena stPeriod s id:" + dto.getId());
            }
        } else {
            entity = new SeasonTournamentPeriod();
        }
        entity.setIsGoldPart(dto.getIsGoldPart());
        entity.setLength(dateConverter.stringToTime(dto.getLength()));
        entity.setName(dto.getName());
        if (dto.getSeasonTournamentId() != null) {
            SeasonTournament st = seasonTournamentRepo.findOne(dto.getSeasonTournamentId());
            if (st == null) {
                LOG.info("nenajdeny seasonTournament podla ID:" + dto.getSeasonTournamentId());
                throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny season tournament podla id: " + dto.getSeasonTournamentId());
            }
            entity.setSeasonTournament(st);
        }
        return entity;
    }
}
