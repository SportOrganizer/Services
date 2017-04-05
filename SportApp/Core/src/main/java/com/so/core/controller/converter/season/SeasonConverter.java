/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.converter.season;

import com.so.core.controller.dto.season.SeasonDTO;
import com.so.core.controller.dto.season.SeasonTournamentDTO;
import com.so.dal.core.model.season.Season;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.repository.season.SeasonRepository;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author peter
 */
@Service
public class SeasonConverter {

    @Autowired
    private SeasonTournamentConverter stConverter;

    @Autowired
    private SeasonRepository seasonRepo;

    public SeasonDTO entityToDto(Season entity, boolean ifCopySeasonTournament) {
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

    public Season dtoToEntity(SeasonDTO dto) {

        Season entity;

        if (dto.getId() != null) {
            entity = seasonRepo.findOne(dto.getId());

            if (entity == null) {
                throw new InvalidParameterException("nenajdeny seasonTournament podla id");
            }
        } else {
            entity = new Season();
        }

        entity.setName(dto.getName());
        return entity;
    }

}
