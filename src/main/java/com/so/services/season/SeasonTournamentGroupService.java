/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.services.season;

import com.so.dal.model.season.SeasonTournament;
import com.so.dal.model.season.SeasonTournamentGroup;
import com.so.dal.repository.season.SeasonTournamentGroupRepository;
import java.security.InvalidParameterException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomas
 */
@Service
public class SeasonTournamentGroupService {
    
    private final static Logger LOG = LoggerFactory.getLogger(SeasonTournamentGroupService.class);
    
    @Autowired
    SeasonTournamentGroupRepository seasonTournamentGroupRepo;
    
    @Autowired
    SeasonTournamentService seasonTournamentService;
    
    
    public List<SeasonTournamentGroup> findAll(){
        LOG.info("findAll()");
        
        List<SeasonTournamentGroup> ls = seasonTournamentGroupRepo.findAll();
        
        return ls;
    }
    
    public SeasonTournamentGroup findById(Integer id){
        LOG.info("findById({})", id);
        
        if(id == null){
            LOG.error("id can't be null: {}", id);
            throw new InvalidParameterException("required parameter null");
        }

        SeasonTournamentGroup s = seasonTournamentGroupRepo.findOne(id);
        return s;
    }
    
    public List<SeasonTournamentGroup> findByNameContaining(String name){
        LOG.info("findByNameContaining({})", name);

        if(name == null){
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }
        List<SeasonTournamentGroup> ls = seasonTournamentGroupRepo.findByNameContaining(name);
        return ls;
    }
    
    @Transactional
    public Boolean createSeasonTournamentGroup(Integer seasonTournamentId, String name){
        LOG.info("createSeasonTournamentGroup({},{})", seasonTournamentId, name);
        
        SeasonTournament seasonTournament;
        SeasonTournamentGroup seasonTournamentGroup;
        
        
        if(seasonTournamentId == null || name == null){
            LOG.error("required can't be null: {}, {}, {}", seasonTournamentId, name);
            throw new InvalidParameterException("required parameter null");
        }
        
        if(seasonTournamentGroupRepo.findByName(name) != null){
            LOG.error("duplicate name: {}", name);
            return false;
        }
        
        seasonTournament = seasonTournamentService.findById(seasonTournamentId);
        
        if(seasonTournament == null){
            LOG.error("wrong reference id: {}", seasonTournamentId);
            return false;
        }
        
        seasonTournamentGroup = new SeasonTournamentGroup(seasonTournament, name);
        seasonTournamentGroup = seasonTournamentGroupRepo.saveAndFlush(seasonTournamentGroup);
        
        if (seasonTournamentGroup == null) {
            LOG.error("save has failed: {}", name);
            return false;
        }
        
        return true;
    }
}
