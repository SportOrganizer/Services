/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.floorball.services;

import com.so.core.exception.AppException;
import com.so.core.services.PersonService;
import com.so.dal.floorball.model.game.SportFlorbalGameActivity;
import com.so.dal.floorball.model.game.SportFlorbalGoalType;
import com.so.dal.floorball.repository.game.SportFlorbalGameShotsRepository;
import com.so.dal.floorball.repository.game.SportFlorbalGoalTypeRepository;
import com.so.floorball.controller.converter.SportFloorballGoalTypeConverter;
import com.so.floorball.controller.floorballGame.dto.RequestSportFloorballGameActivityDto;
import com.so.floorball.controller.floorballGame.dto.ResponseSportFloorballGameActivityDto;
import com.so.floorball.controller.floorballGame.dto.SportFloorballGoalTypeDto;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Kristián Kačinetz
 */
public class SportFloorballGoalTypeService {
    
    private final static Logger LOG = LoggerFactory.getLogger(PersonService.class);
    
    @Autowired
    SportFlorbalGoalTypeRepository goalTypeRepo;
    
    @Autowired
    SportFloorballGoalTypeConverter converter;
    
    @Transactional
    public SportFloorballGoalTypeDto addSportFloorballGoalType(SportFloorballGoalTypeDto dto) throws AppException {
        LOG.info("addSportFloorballGoalType()");
        
        if(dto.getName() == null || dto.getCancelPenalty() == null){
            LOG.error(" name, cancelPenalty {}, {}", dto.getName(), dto.getCancelPenalty());
            throw new AppException(HttpStatus.BAD_REQUEST, "nie je zadany povinny parameter");
        }
        
        SportFlorbalGoalType a = converter.sportFloorbalGoalTypeDtoToEntity(dto);
        a = goalTypeRepo.saveAndFlush(a);

        if (a == null) {
            LOG.error("addSportFloorballGoalType sa neulozila do databazy: {}", dto.getId());
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "addSportFloorballGoalType s ID:" + dto.getId() + " sa neulozil do databazy");
        }

        return converter.sportFloorbalGoalTypeEntityToDto(a);

    }
    
    @Transactional
    public List<SportFloorballGoalTypeDto> findAllGoalType() {
        LOG.info("findAllGoalType()");

        List<SportFlorbalGoalType> lg = goalTypeRepo.findAll();
        List<SportFloorballGoalTypeDto> l = new ArrayList<>();

        for (SportFlorbalGoalType g : lg) {
            l.add(converter.sportFloorbalGoalTypeEntityToDto(g));
        }
        return l;
    }
    
     @Transactional
    public SportFloorballGoalTypeDto findOneGoalType(Integer id) throws AppException {
        LOG.info("findOneGoalType({})", id);

        SportFlorbalGoalType g = goalTypeRepo.findOne(id);

        if (g == null) {
            LOG.error("nenajdeny goalType s id:{}", id);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdena goalType s id:" + id);
        }
        return converter.sportFloorbalGoalTypeEntityToDto(g);
    }
    
    @Transactional
    public void deleteSportFlorbalGoalType(Integer id) throws AppException {
        LOG.info("deleteSportFlorbalGoalType({})", id);
        SportFlorbalGoalType p = goalTypeRepo.findOne(id);

        if (p == null) {
            LOG.error("nenajdena SportFlorbalGoalType na vymazanie id={}", id);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdena SportFlorbalGoalType id=" + id);
        }
        goalTypeRepo.delete(p);
    }
    
    @Transactional
    public SportFloorballGoalTypeDto updateSportFlorbalGoalType(SportFloorballGoalTypeDto updated) throws AppException {
        LOG.info("updateSportFlorbalGoalType()");

        if (updated == null) {
            LOG.error("aktualizovany objekt je null");
            throw new AppException(HttpStatus.BAD_REQUEST, "zadany objekt je nespravne vyplneny");
        }
        SportFlorbalGoalType a = converter.sportFloorbalGoalTypeDtoToEntity(updated);
        a = goalTypeRepo.saveAndFlush(a);

        if (a == null) {
            LOG.error("nepodarilo sa ulozit SportFlorbalGoalType do db");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "nepodarilo sa ulozit SportFlorbalGoalType do db");
        }
        return converter.sportFloorbalGoalTypeEntityToDto(a);
    }
    
}
