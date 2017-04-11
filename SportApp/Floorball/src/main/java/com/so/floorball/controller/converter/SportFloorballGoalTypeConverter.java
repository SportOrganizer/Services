/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.floorball.controller.converter;

import com.so.core.exception.AppException;
import com.so.dal.core.model.game.Game;
import com.so.dal.floorball.model.game.SportFlorbalGoalType;
import com.so.dal.floorball.repository.game.SportFlorbalGoalTypeRepository;
import com.so.floorball.controller.floorballGame.dto.SportFloorballGoalTypeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kristián Kačinetz
 */
@Service
public class SportFloorballGoalTypeConverter {
    
    private final static Logger LOG = LoggerFactory.getLogger(SportFloorballGoalTypeConverter.class);
    
    @Autowired
    SportFlorbalGoalTypeRepository sportFlorbalGoalTypeRepo;
    
    public SportFloorballGoalTypeDto sportFloorbalGoalTypeEntityToDto(SportFlorbalGoalType entity){
        
        SportFloorballGoalTypeDto dto = new SportFloorballGoalTypeDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        
        return dto;
    }
    
    public SportFlorbalGoalType sportFloorbalGoalTypeDtoToEntity(SportFloorballGoalTypeDto dto) throws AppException{
        
        SportFlorbalGoalType entity;
        if (dto.getId() != null) {
            entity = sportFlorbalGoalTypeRepo.findOne(dto.getId());
            if (entity == null) {
                LOG.error("nenajdeny sportFloorbalGoalType podla id: {}", dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje sportFloorbalGoalType s id: " + dto.getId());
            }
        } else {
            entity = new SportFlorbalGoalType();
        }
        
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        
        return entity;
        
    }
    
}
