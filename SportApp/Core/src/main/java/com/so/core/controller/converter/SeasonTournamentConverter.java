/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.converter;

import com.so.core.controller.dto.ResourceDto;
import com.so.core.controller.dto.season.SeasonTournamentDTO;
import com.so.dal.core.model.Resource;
import com.so.dal.core.model.Tournament;
import com.so.dal.core.model.season.Season;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.repository.ResourceRepository;
import com.so.dal.core.repository.TournamentRepository;
import com.so.dal.core.repository.season.SeasonRepository;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
import java.security.InvalidParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    public SeasonTournamentDTO entityToDto(SeasonTournament entity) {
        SeasonTournamentDTO dto = new SeasonTournamentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSeasonId(entity.getSeason().getId());
        dto.setTournamentId(entity.getTournament().getId());
     if(entity.getResource()!=null){
        dto.setLogo(new ResourceDto(entity.getResource().getId(), entity.getResource().getPath()));
     }
        return dto;
    }
    
    public SeasonTournament dtoToEntity(SeasonTournamentDTO dto) {
        
        SeasonTournament entity;
        
        if (dto.getId() != null) {
            entity = seasonTournamentRepo.findOne(dto.getId());
            
            if (entity == null) {
                throw new InvalidParameterException("nenajdeny seasonTournament podla id");
            }
        } else {
            entity = new SeasonTournament();
        }
        
        if (dto.getSeasonId() != null) {
            Season season = seasonRepo.findOne(dto.getSeasonId());
            
            if (season != null) {
                entity.setSeason(season);
            } else {
                LOG.error("wrong reference id: {}", dto.getSeasonId());
                throw new InvalidParameterException("wrong reference id");
            }
        }
        
        if (dto.getTournamentId() != null) {
            Tournament tournament = tournamentRepo.findOne(dto.getTournamentId());
            if (tournament == null) {
                LOG.error("wrong reference id: {}", dto.getTournamentId());
                throw new InvalidParameterException("wrong reference id");
            }else{
                entity.setTournament(tournament);
            }
            
        }
        
        if (dto.getLogo() != null) {
            Resource resource = resourceRepo.getOne(dto.getLogo().getId());
            
            if (resource == null) {
                LOG.error("wrong reference id: {}", dto.getLogo().getId());
                throw new InvalidParameterException("wrong reference id");
            }else{
                entity.setResource(resource);
            }
        }
        
        entity.setName(dto.getName());
        return entity;
    }
}
