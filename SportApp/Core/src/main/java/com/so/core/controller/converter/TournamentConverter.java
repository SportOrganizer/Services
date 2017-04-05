/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.converter;

import com.so.core.controller.converter.season.SeasonTournamentConverter;
import com.so.core.controller.dto.TournamentDTO;
import com.so.core.controller.dto.season.SeasonTournamentDTO;
import com.so.dal.core.model.Tournament;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.repository.TournamentRepository;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kristián Kačinetz
 */
@Service
public class TournamentConverter {

    @Autowired
    private SeasonTournamentConverter stConverter;

    @Autowired
    private TournamentRepository tournamentRepo;

    public TournamentDTO entityToDto(Tournament entity, boolean ifCopySeasonTournament) {
        TournamentDTO dto = new TournamentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        List<SeasonTournamentDTO> seasonTournaments = new ArrayList<>();
        if (ifCopySeasonTournament) {
            for (SeasonTournament st : entity.getSeasonTournaments()) {
                seasonTournaments.add(stConverter.entityToDto(st));
            }
            dto.setSeasonTournaments(seasonTournaments);
            dto.setLength(dto.getSeasonTournaments().size());
        }
       

        return dto;
    }

    public Tournament dtoToEntity(TournamentDTO dto) {

        Tournament entity;

        if (dto.getId() != null) {
            entity = tournamentRepo.findOne(dto.getId());

            if (entity == null) {
                throw new InvalidParameterException("nenajdeny Tournament podla id");
            }

        } else {
            entity = new Tournament();
        }

        entity.setName(dto.getName());
        return entity;
    }

}
