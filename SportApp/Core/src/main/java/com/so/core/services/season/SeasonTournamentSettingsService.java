/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.season;

import com.so.core.controller.converter.SeasonTournamentSettingsConverter;
import com.so.core.controller.dto.season.SeasonTournamentSettingsDTO;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.model.season.SeasonTournamentSettings;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
import com.so.dal.core.repository.season.SeasonTournamentSettingsRepository;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kristian
 */
@Service
public class SeasonTournamentSettingsService {
    private final static Logger LOG = LoggerFactory.getLogger(SeasonTournamentSettingsService.class); /// logovanie..
    
    @Autowired 
    SeasonTournamentSettingsRepository seasonTournamentSett;
    
    @Autowired
    SeasonTournamentSettingsConverter seasonTournamentConv;
    
    @Autowired
    SeasonTournamentRepository seasonTournamentRep;
    
    @Transactional
    public List<SeasonTournamentSettingsDTO> findByTournamentId(Integer id) {
        LOG.info("findByTournamentId({})", id);
        if (id == null) {
            LOG.error("id tournament can't be null: {}", id);
            throw new InvalidParameterException("required parameter null");
        }

        SeasonTournament seasonTourn = seasonTournamentRep.findOne(id);
        
        if(seasonTourn == null) {
            LOG.error("id tournament can't be null: {}", id);
            throw new InvalidParameterException("required parameter null");
        }
        List<SeasonTournamentSettings> s = seasonTournamentSett.findBySeasonTournament(seasonTourn);

        if (s.isEmpty()) {
            return new ArrayList<>();
        }

        List<SeasonTournamentSettingsDTO> DTOList = new ArrayList<>();
        
        for(SeasonTournamentSettings st: s) {
            DTOList.add(seasonTournamentConv.entityToDto(st));
        }
        
        return DTOList;
    }
}
