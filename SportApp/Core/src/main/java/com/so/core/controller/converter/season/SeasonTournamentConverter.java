/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.converter.season;

import com.so.core.controller.dto.ResourceDto;
import com.so.core.controller.dto.season.SeasonTournamentDTO;
import com.so.core.exception.AppException;
import com.so.dal.core.model.Resource;
import com.so.dal.core.model.Tournament;
import com.so.dal.core.model.season.Season;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.repository.ResourceRepository;
import com.so.dal.core.repository.TournamentRepository;
import com.so.dal.core.repository.season.SeasonRepository;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
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
}
