/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.converter;

import com.so.core.controller.dto.season.SeasonTournamentSettingsDTO;
import com.so.dal.core.model.season.SeasonTournamentSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kristian
 */
@Service
public class SeasonTournamentSettingsConverter {
    
    public SeasonTournamentSettingsDTO entityToDto(SeasonTournamentSettings entity){
       SeasonTournamentSettingsDTO  dto = new SeasonTournamentSettingsDTO();
       
       dto.setId(entity.getId());
       dto.setName(entity.getName());
       dto.setValue(entity.getValue());
       dto.setIdSeasonTournament(entity.getSeasonTournament().getId());
       
       return dto;
    }
}
