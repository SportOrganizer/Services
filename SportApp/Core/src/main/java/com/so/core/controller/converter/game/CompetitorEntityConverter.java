/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.converter.game;

import com.so.core.controller.converter.PersonConverter;
import com.so.core.controller.converter.TeamConverter;
import com.so.core.controller.dto.ResourceDto;
import com.so.core.controller.dto.game.CompetitorTeamDto;
import com.so.core.controller.dto.game.CompetitorTeamPlayerDto;
import com.so.core.exception.AppException;
import com.so.dal.core.model.Person;
import com.so.dal.core.model.Resource;
import com.so.dal.core.model.Team;
import com.so.dal.core.model.game.CompetitorTeam;
import com.so.dal.core.model.game.CompetitorTeamPlayer;
import com.so.dal.core.model.season.SeasonTournamentGroup;
import com.so.dal.core.repository.PersonRepository;
import com.so.dal.core.repository.ResourceRepository;
import com.so.dal.core.repository.TeamRepository;
import com.so.dal.core.repository.game.CompetitorTeamPlayerRepository;
import com.so.dal.core.repository.game.CompetitorTeamRepository;
import com.so.dal.core.repository.season.SeasonTournamentGroupRepository;
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
public class CompetitorEntityConverter {

    private final static Logger LOG = LoggerFactory.getLogger(CompetitorEntityConverter.class);

    @Autowired
    private CompetitorTeamRepository competitorTeamRepo;

    @Autowired
    private CompetitorTeamPlayerRepository competitorTeamPlayerRepo;

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    private ResourceRepository resourceRepo;

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private TeamConverter teamConverter;

    @Autowired
    private SeasonTournamentGroupRepository stGroupRepo;

    @Autowired
    private TeamRepository teamRepo;

    public CompetitorTeamPlayerDto competitorTeamPlayerEntityToDto(CompetitorTeamPlayer entity) {

        CompetitorTeamPlayerDto dto = new CompetitorTeamPlayerDto();

        dto.setCompetitorTeamId(entity.getCompetitorTeam().getId());
        dto.setId(entity.getId());
        dto.setNumber(entity.getNumber());
        dto.setIsCapitan(entity.isIsCapitan());
        dto.setPersonInfo(personConverter.personEntityToDto(entity.getPerson()));
        dto.setPhoto(new ResourceDto(entity.getResource().getId(), entity.getResource().getPath()));

        return dto;
    }

    public CompetitorTeamPlayer competitorTeamPlayerDtoToEntity(CompetitorTeamPlayerDto dto) throws AppException {

        CompetitorTeamPlayer entity;
        if (dto.getId() != null) {
            entity = competitorTeamPlayerRepo.findOne(dto.getId());
            if (entity == null) {
                LOG.error("nenajdeny competitorTeamPlayer podla id: {}", dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "bolo zadane nespravne id: " + dto.getId());
            }
        } else {
            entity = new CompetitorTeamPlayer();
        }
        if (dto.getCompetitorTeamId() != null) {
            CompetitorTeam t = competitorTeamRepo.findOne(dto.getCompetitorTeamId());
            if (t == null) {
                LOG.error("neexistuje competitorTeam s id={}", dto.getCompetitorTeamId());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje competitorTea, s id=" + dto.getCompetitorTeamId());
            } else {
                entity.setCompetitorTeam(t);
            }
        }
        if (dto.getPhoto() != null) {
            if (dto.getPhoto().getId() != null) {
                Resource r = resourceRepo.findOne(dto.getPhoto().getId());
                if (r == null) {
                    LOG.error("neexistuje resource s id={}", dto.getPhoto().getId());
                    throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje resource s id=" + dto.getPhoto().getId());
                } else {
                    entity.setResource(r);
                }
            }
        }
        if (dto.getPersonInfo() != null) {
            if (dto.getPersonInfo().getId() != null) {
                Person p = personRepo.findOne(dto.getPersonInfo().getId());
                if (p == null) {
                    LOG.error("neexistuje Person s id={}", dto.getPersonInfo().getId());
                    throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje competitorTea, s id=" + dto.getPersonInfo().getId());
                } else {
                    entity.setPerson(p);
                }
            }
        }
        entity.setIsCapitan(dto.isIsCapitan());
        entity.setNumber(dto.getNumber());

        return entity;
    }

    public CompetitorTeamDto competitorTeamEntityToDto(CompetitorTeam entity, boolean ifCopyPlayers) {
        CompetitorTeamDto dto = new CompetitorTeamDto();
        List<CompetitorTeamPlayerDto> players = new ArrayList<>();

        dto.setId(entity.getId());
        dto.setLogo(new ResourceDto(entity.getResource().getId(), entity.getResource().getPath()));
        dto.setSeasonTournamentGroupId(entity.getSeasonTournamentGroup().getId());
        dto.setTeam(teamConverter.teamEntityToDto(entity.getTeam(), false));

        if (ifCopyPlayers) {
            for (CompetitorTeamPlayer p : entity.getCompetitorTeamPlayers()) {
                players.add(competitorTeamPlayerEntityToDto(p));
            }
            dto.setCompetitorTeamPlayers(players);
        }

        return dto;
    }

    public CompetitorTeam competitorTeamDtoToEntity(CompetitorTeamDto dto) throws AppException {
        CompetitorTeam entity;

        if (dto.getId() != null) {
            entity = competitorTeamRepo.findOne(dto.getId());
            if (entity == null) {
                LOG.error("nenajdeny competitorTeamPlayer podla id: {}", dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "bolo zadane nespravne id: " + dto.getId());
            }
        } else {
            entity = new CompetitorTeam();
        }

        if (dto.getLogo() != null) {
            if (dto.getLogo().getId() != null) {
                Resource r = resourceRepo.findOne(dto.getLogo().getId());
                if (r == null) {
                    LOG.error("neexistuje resource s id={}", dto.getLogo().getId());
                    throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje resource s id=" + dto.getLogo().getId());
                } else {
                    entity.setResource(r);
                }
            }
        }

        if (dto.getSeasonTournamentGroupId() != null) {
            SeasonTournamentGroup g = stGroupRepo.findOne(dto.getSeasonTournamentGroupId());
            if (g == null) {
                LOG.error("neexistuje seasonTournamentGroup s id={}", dto.getSeasonTournamentGroupId());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje seasonTournamentGroup s id=" + dto.getSeasonTournamentGroupId());
            } else {
                entity.setSeasonTournamentGroup(g);
            }
        }

        if (dto.getTeam() != null) {
            if (dto.getTeam().getId() != null) {
                Team t = teamRepo.findOne(dto.getTeam().getId());
                if (t == null) {
                    LOG.error("neexistuje team s id={}", dto.getTeam().getId());
                    throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje team s id=" + dto.getTeam().getId());
                } else {
                    entity.setTeam(t);
                }
            }
        }
        return entity;
    }

}
