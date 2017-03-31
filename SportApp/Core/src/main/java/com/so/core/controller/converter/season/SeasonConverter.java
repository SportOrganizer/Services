/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.converter.season;

import com.so.core.controller.dto.season.SeasonDTO;
import com.so.core.controller.dto.season.SeasonTournamentDTO;
import com.so.core.exception.AppException;
import com.so.dal.core.model.season.Season;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.repository.season.SeasonRepository;
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
public class SeasonConverter {

    private final static Logger LOG = LoggerFactory.getLogger(SeasonConverter.class);

    @Autowired
    private SeasonTournamentConverter stConverter;

    @Autowired
    private SeasonRepository seasonRepo;

    public SeasonDTO entityToDto(Season entity, boolean ifCopySeasonTournament) throws AppException {

        if (entity == null) {
            LOG.info("entity je null");
            return new SeasonDTO();
        }
        SeasonDTO dto = new SeasonDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        List<SeasonTournamentDTO> seasonTournaments = new ArrayList<>();
        if (ifCopySeasonTournament) {
            for (SeasonTournament st : entity.getSeasonTournaments()) {
                seasonTournaments.add(stConverter.entityToDto(st));
            }
            dto.setSeasonTournaments(seasonTournaments);
        }
        return dto;
    }

    public Season dtoToEntity(SeasonDTO dto) throws AppException {
        if (dto == null) {
            LOG.error("nevyplnene dto v dtoToEntity(Season dto)");
            return new Season();
        }
        Season entity;

        if (dto.getId() != null) {
            entity = seasonRepo.findOne(dto.getId());
            if (entity == null) {
                throw new AppException(HttpStatus.BAD_REQUEST, "id Season nezodpoveda"
                        + "ziadnemu zaznamu id=" + dto.getId());
            }
        } else {
            entity = new Season();
        }
        entity.setName(dto.getName());
        return entity;
    }

}
