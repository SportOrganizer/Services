/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.converter;

import com.so.core.controller.dto.season.SettingDto;
import com.so.core.controller.dto.season.SettingsDto;
import com.so.core.exception.AppException;
import com.so.dal.core.model.season.SeasonTournamentSettings;
import com.so.dal.core.repository.season.SeasonTournamentSettingsRepository;
import com.so.dal.core.repository.season.SeasonTournamentSettingsTypeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kristian
 */
@Service
public class SeasonTournamentSettingsConverter {
    
        @Autowired
    SeasonTournamentSettingsRepository stSettingsRepo;
        
           @Autowired
    SeasonTournamentSettingsTypeRepository stSettingsTypeRepo;
    
    public SettingsDto entityToDto(List<SeasonTournamentSettings> entity){
       SettingsDto  dto = new SettingsDto();
       dto.setSettings(new ArrayList<SettingDto>());
       
       
       for(SeasonTournamentSettings s: entity){
       SettingDto dtoSet= new SettingDto();
       dtoSet.setId(s.getId());
       dtoSet.setName(s.getName());
       dtoSet.setValue(s.getValue());
       dto.getSettings().add(dtoSet);
       }
       
       return dto;
    }
    
    public SeasonTournamentSettings dtoToEntity(SettingDto dto) throws AppException{
           
        SeasonTournamentSettings set = stSettingsRepo.findOne(dto.getId());
           if(set==null){
               throw new AppException(HttpStatus.NOT_FOUND,"nenajdeny settings s id "+dto.getId());
           }
           
           if(dto.getName()!=null){
          set.setName(dto.getName());
           }
           if(dto.getValue()!=null){
          set.setValue(dto.getValue());
           }
           if(dto.getName()!=null){
          set.setSeasonTournamentSettingsType(stSettingsTypeRepo.findByName(dto.getName()));
         
           }
           return set;
    }
    
    
    public SettingDto entityToDto(SeasonTournamentSettings entity){
        SettingDto dto = new SettingDto();
        
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setValue(entity.getValue());
        return dto;
    }
}
