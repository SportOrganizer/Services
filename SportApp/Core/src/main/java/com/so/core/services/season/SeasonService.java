/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.season;

import com.so.core.controller.converter.season.SeasonConverter;
import com.so.core.controller.dto.season.SeasonDTO;
import com.so.dal.core.model.season.Season;
import com.so.dal.core.repository.season.SeasonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;


@Service
public class SeasonService {

    private final static Logger LOG = LoggerFactory.getLogger(SeasonService.class); /// logovanie..

    @Autowired
    SeasonRepository seasonRepo;
    
    @Autowired
    SeasonConverter seasonConverter;

    @Transactional
    public SeasonDTO findById(Integer id){
        LOG.info("findById({})", id);
        if(id == null){
            LOG.error("id can't be null: {}", id);
            throw new InvalidParameterException("required parameter null");
        }

        Season s = seasonRepo.findOne(id);
        if(s==null){
            LOG.error("k danemu id neexistuje zaznam v dbs");
        }
        
        return seasonConverter.entityToDto(s, true);
    }

    public List<SeasonDTO> findByNameContaining(String name){
        LOG.info("findByNameContaining({})", name);

        if(name == null){
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }
        List<SeasonDTO> seasonList = new ArrayList<>();
        List<Season> ls= seasonRepo.findByNameContaining(name);
        
        for(Season s:ls){
            seasonList.add(seasonConverter.entityToDto(s, false));
        }
        
        return seasonList;
    }

    public Season findByName(String name){
        LOG.info("findByNameContaining({})", name);
        if(name == null){
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }

        Season s = seasonRepo.findByName(name);
        return  s;
    }

    public List<SeasonDTO> findAll(){
        LOG.info("findAll()");
        List<Season> ls= seasonRepo.findAll();
        List<SeasonDTO> seasonList = new ArrayList<>();
        
        for(Season s:ls){
            seasonList.add(seasonConverter.entityToDto(s, false));
        }
        
        return seasonList;
    }

    @Transactional
    public Boolean createSeason(String name) {
        LOG.info("createSeason({})",name);

        if(name == null){
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }

        if(seasonRepo.findByName(name) != null){
            LOG.error("duplicate name: {}", name);
            return false;
        }

        Season s = new Season(name);
        s  = seasonRepo.saveAndFlush(s);

        if (s == null) {
            LOG.error("save has failed: {}", name);
            return false;
        }

        return true;
        

    }

 @Transactional
    public void deleteSeason(Integer id) {
        LOG.info("deleteSeason({})",id);
        Season s = seasonRepo.findOne(id);

        if (s == null) {
            throw new InvalidParameterException("nenajdene seasonTournament");
        }

        seasonRepo.delete(s);
    }

    @Transactional
    public SeasonDTO update(SeasonDTO updated) {
     LOG.info("update()");
        Season s = seasonConverter.dtoToEntity(updated);

        Season saved = seasonRepo.saveAndFlush(s);

        if (saved == null) {
            LOG.error("nepodarilo sa ulozit st do db");
            throw new IllegalStateException("nepodarilo sa ulozit st do db");
        }
        return seasonConverter.entityToDto(saved, false);
    }

}
