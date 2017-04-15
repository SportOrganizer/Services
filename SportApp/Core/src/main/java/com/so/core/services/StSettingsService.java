/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services;

import com.so.core.controller.dto.season.SeasonTournamentSettingsDTO;
import com.so.dal.core.repository.season.SeasonTournamentSettingsRepository;
import com.so.dal.core.repository.season.SeasonTournamentSettingsTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author peter
 */
@Service
public class StSettingsService {
    
    @Autowired
    private SeasonTournamentSettingsTypeRepository stSettingsTypeRepo;
    @Autowired
    private SeasonTournamentSettingsRepository stSettingsRepo;
    
    public SeasonTournamentSettingsDTO addStSetting(SeasonTournamentSettingsDTO dto){
     
        return dto;
    }
}
