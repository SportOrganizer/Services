/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.converter.game;

import com.so.core.controller.converter.DateConverter;
import com.so.core.controller.converter.season.SeasonTournamentConverter;
import com.so.core.controller.dto.game.GameDto;
import com.so.core.controller.dto.game.GamePlayerDto;
import com.so.core.exception.AppException;
import com.so.dal.core.model.game.CompetitorTeam;
import com.so.dal.core.model.game.CompetitorTeamPlayer;
import com.so.dal.core.model.game.Game;
import com.so.dal.core.model.game.GamePlayer;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.model.season.SeasonTournamentGroup;
import com.so.dal.core.model.season.SeasonTournamentLocation;
import com.so.dal.core.model.season.SeasonTournamentRound;
import com.so.dal.core.repository.game.CompetitorTeamPlayerRepository;
import com.so.dal.core.repository.game.CompetitorTeamRepository;
import com.so.dal.core.repository.game.GamePlayerRepository;
import com.so.dal.core.repository.game.GameRepository;
import com.so.dal.core.repository.season.SeasonTournamentGroupRepository;
import com.so.dal.core.repository.season.SeasonTournamentLocationRepository;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
import com.so.dal.core.repository.season.SeasonTournamentRoundRepository;
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
public class GameConverter {

    private final static Logger LOG = LoggerFactory.getLogger(GameConverter.class);

    @Autowired
    private CompetitorEntityConverter competitorConverter;

    @Autowired
    private SeasonTournamentConverter seasonTournamentConverter;

    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private CompetitorTeamRepository competitorTeamRepo;

    @Autowired
    private CompetitorTeamPlayerRepository competitorTeamPlayerRepo;

    @Autowired
    private SeasonTournamentGroupRepository groupRepo;

    @Autowired
    private SeasonTournamentLocationRepository locationRepo;

    @Autowired
    private SeasonTournamentRoundRepository roundRepo;

    @Autowired
    private GamePlayerRepository gamePlayerRepo;

    @Autowired
    private DateConverter dateConverter;

    @Autowired
    private SeasonTournamentRepository stRepo;

    public GameDto gameEntityToDto(Game entity) throws AppException {
        GameDto dto = new GameDto();
        dto.setId(entity.getId());
        dto.setFinished(entity.isFinished());
        dto.setContumated(entity.isContumated());
        dto.setOvertime(entity.isOvertime());
        dto.setName(entity.getName());
        dto.setRealStart(dateConverter.dateTimeToString(entity.getRealStart()));
        dto.setStartTime(dateConverter.dateTimeToString(entity.getStartTime()));

        if (entity.getCompetitorTeamByIdAwayTeam() != null) {
            dto.setAwayTeam(competitorConverter.competitorTeamEntityToDto(entity.getCompetitorTeamByIdAwayTeam(), false));
        }
        if (entity.getCompetitorTeamByIdHomeTeam() != null) {
            dto.setHomeTeam(competitorConverter.competitorTeamEntityToDto(entity.getCompetitorTeamByIdHomeTeam(), false));
        }

        if (entity.getSeasonTournamentGroup() != null) {
            dto.setGroup(seasonTournamentConverter.groupEntityToDto(entity.getSeasonTournamentGroup()));
        }

        if (entity.getSeasonTournamentLocation() != null) {
            dto.setLocation(seasonTournamentConverter.locationEntityToDto(entity.getSeasonTournamentLocation()));
        }

        if (entity.getSeasonTournamentRound() != null) {
            dto.setRound(seasonTournamentConverter.roundEntityToDto(entity.getSeasonTournamentRound()));
        }

        if (entity.getSeasonTournament() != null) {
            dto.setSeasonTournament(seasonTournamentConverter.entityToDto(entity.getSeasonTournament()));
        }

        return dto;
    }
    
        public GameDto gameEntityToDto2(Game entity) throws AppException {
        GameDto dto = new GameDto();
        dto.setId(entity.getId());
        dto.setFinished(entity.isFinished());
        dto.setContumated(entity.isContumated());
        dto.setOvertime(entity.isOvertime());
        dto.setName(entity.getName());
        dto.setRealStart(dateConverter.dateTimeToString(entity.getRealStart()));
        dto.setStartTime(dateConverter.dateTimeToString(entity.getStartTime()));

        if (entity.getCompetitorTeamByIdAwayTeam() != null) {
            dto.setAwayTeam(competitorConverter.competitorTeamEntityToDto(entity.getCompetitorTeamByIdAwayTeam(), false));
        }
        if (entity.getCompetitorTeamByIdHomeTeam() != null) {
            dto.setHomeTeam(competitorConverter.competitorTeamEntityToDto(entity.getCompetitorTeamByIdHomeTeam(), false));
        }
//
//        if (entity.getSeasonTournamentGroup() != null) {
//            dto.setGroup(seasonTournamentConverter.groupEntityToDto(entity.getSeasonTournamentGroup()));
//        }
//
//        if (entity.getSeasonTournamentLocation() != null) {
//            dto.setLocation(seasonTournamentConverter.locationEntityToDto(entity.getSeasonTournamentLocation()));
//        }
//
//        if (entity.getSeasonTournamentRound() != null) {
//            dto.setRound(seasonTournamentConverter.roundEntityToDto(entity.getSeasonTournamentRound()));
//        }
//
//        if (entity.getSeasonTournament() != null) {
//            dto.setSeasonTournament(seasonTournamentConverter.entityToDto(entity.getSeasonTournament()));
//        }

        return dto;
    }

    public Game gameDtoToEntity(GameDto dto) throws AppException {
        Game entity;
        if (dto.getId() != null) {
            entity = gameRepo.findOne(dto.getId());
            if (entity == null) {
                LOG.error("nenajdena game podla id: {}", dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje game s id: " + dto.getId());
            }
        } else {
            entity = new Game();
        }
        entity.setContumated(dto.getContumated());
        entity.setFinished(dto.getFinished());
        entity.setOvertime(dto.getOvertime());
        entity.setStartTime(dateConverter.stringToDateTime(dto.getStartTime()));
        entity.setRealStart(dateConverter.stringToDateTime(dto.getRealStart()));

        if (dto.getAwayTeam() != null) {
            if (dto.getAwayTeam().getId() != null) {
                CompetitorTeam t = competitorTeamRepo.findOne(dto.getAwayTeam().getId());
                if (t == null) {
                    LOG.error("neexistuje competitorTeamPlayer s id {}", dto.getAwayTeam().getId());
                    throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje competitorTeamPlayer s id: " + dto.getAwayTeam().getId());
                } else {
                    entity.setCompetitorTeamByIdAwayTeam(t);
                }
            }
        }

        if (dto.getHomeTeam() != null) {
            if (dto.getHomeTeam().getId() != null) {
                CompetitorTeam t = competitorTeamRepo.findOne(dto.getHomeTeam().getId());
                if (t == null) {
                    LOG.error("neexistuje competitorTeamPlayer s id {}", dto.getHomeTeam().getId());
                    throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje competitorTeamPlayer s id: " + dto.getHomeTeam().getId());
                } else {
                    entity.setCompetitorTeamByIdHomeTeam(t);
                }
            }
        }

        if (dto.getGroup() != null) {
            if (dto.getGroup().getId() != null) {
                SeasonTournamentGroup g = groupRepo.findOne(dto.getGroup().getId());
                if (g == null) {
                    LOG.error("neexistuje group s id {}", dto.getGroup().getId());
                    throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje group s id: " + dto.getGroup().getId());
                } else {
                    entity.setSeasonTournamentGroup(g);
                }
            }
        }

        if (dto.getLocation() != null) {
            if (dto.getLocation().getId() != null) {
                SeasonTournamentLocation l = locationRepo.findOne(dto.getLocation().getId());
                if (l == null) {
                    LOG.error("neexistuje location s id {}", dto.getLocation().getId());
                    throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje location s id: " + dto.getLocation().getId());
                } else {
                    entity.setSeasonTournamentLocation(l);
                }
            }
        }

        if (dto.getRound() != null) {
            if (dto.getRound().getId() != null) {
                SeasonTournamentRound r = roundRepo.findOne(dto.getRound().getId());
                if (r == null) {
                    LOG.error("neexistuje round s id {}", dto.getRound().getId());
                    throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje round s id: " + dto.getRound().getId());
                } else {
                    entity.setSeasonTournamentRound(r);
                }
            }
        }

        if (dto.getSeasonTournament() != null) {
            if (dto.getSeasonTournament().getId() != null) {
                SeasonTournament st = stRepo.findOne(dto.getSeasonTournament().getId());
                if (st == null) {
                    LOG.error("neexistuje seasonTournament s id {}", dto.getSeasonTournament());
                    throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje seasonTournament s id: " + dto.getSeasonTournament());
                } else {
                    entity.setSeasonTournament(st);
                }
            }
        }
        return entity;
    }

    public GamePlayerDto gamePlayerEntityToDto(GamePlayer entity) {
        GamePlayerDto dto = new GamePlayerDto();
        dto.setId(entity.getId());
        if (entity.getCompetitorTeamPlayer() != null) {
            dto.setCompetitorTeamPlayerId(entity.getCompetitorTeamPlayer().getId());
        }
        if (entity.getGame() != null) {
            dto.setGameId(entity.getGame().getId());
        }

        return dto;
    }

    public GamePlayer gamePlayerDtoToEntity(GamePlayerDto dto) throws AppException {
        GamePlayer entity;
        if (dto == null) {
            LOG.error("dto je null");
            throw new AppException(HttpStatus.BAD_REQUEST, "game player je null");
        }

        if (dto.getId() != null) {
            entity = gamePlayerRepo.findOne(dto.getId());
            if (entity == null) {
                LOG.error("neexistuje gamePlayer s id {}", dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje gamePlayer s id " + dto.getId());
            }
        } else {
            entity = new GamePlayer();
        }

        if (dto.getCompetitorTeamPlayerId() != null) {
            CompetitorTeamPlayer ctp = competitorTeamPlayerRepo.findOne(dto.getCompetitorTeamPlayerId());
            if (ctp == null) {
                LOG.error("neexistuje competitorTeamPlayer s id {}", dto.getCompetitorTeamPlayerId());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje competitorTeamPlayer s id " + dto.getCompetitorTeamPlayerId());
            }
            entity.setCompetitorTeamPlayer(ctp);
        }

        if (dto.getGameId() != null) {
            Game g = gameRepo.findOne(dto.getGameId());
            if (g == null) {
                LOG.error("neexistuje competitorTeamPlayer s id {}", dto.getGameId());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje competitorTeamPlayer s id " + dto.getGameId());
            }
            entity.setGame(g);
        }

        return entity;
    }
}
