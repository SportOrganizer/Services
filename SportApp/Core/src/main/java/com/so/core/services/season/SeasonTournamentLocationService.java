/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.season;


import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.model.season.SeasonTournamentLocation;
import com.so.dal.core.repository.season.SeasonTournamentLocationRepository;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
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
public class SeasonTournamentLocationService {
    
    private final static Logger LOG = LoggerFactory.getLogger(SeasonTournamentLocationService.class);
    
    @Autowired
    SeasonTournamentLocationRepository seasonTournamentLocationRepo;
    
    @Autowired
    SeasonTournamentRepository seasonTournamentRepo;
    
    public SeasonTournamentLocation findById(Integer id){
        LOG.info("findById({})", id);
        if(id == null){
            LOG.error("id can't be null: {}", id);
            throw new InvalidParameterException("required parameter null");
        }

        SeasonTournamentLocation s = seasonTournamentLocationRepo.findOne(id);
        return s;
    }
    
    public List<SeasonTournamentLocation> findByNameContaining(String name){
        LOG.info("findByNameContaining({})", name);

        if(name == null){
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }
        List<SeasonTournamentLocation> ls= seasonTournamentLocationRepo.findByNameContaining(name);
        return ls;
    }
    
    public SeasonTournamentLocation findByName(String name){
        LOG.info("findByName({})", name);
        if(name == null){
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }

        SeasonTournamentLocation s = seasonTournamentLocationRepo.findByName(name);
        return  s;
    }
    
    public List<SeasonTournamentLocation> findAll(){
        LOG.info("findAll()");

        List<SeasonTournamentLocation> ls= seasonTournamentLocationRepo.findAll();
        return ls;
    }
    
    @Transactional
    public Boolean createSeasonTournamentLocation(Integer seasonTournamentId, String name) {
        LOG.info("createSeasonTournamentLocation({},{})",seasonTournamentId,name);

        
        SeasonTournament seasonTournament;
        SeasonTournamentLocation seasonTournamentLocation;

        if(seasonTournamentId == null || name == null){
            LOG.error("required can't be null: {}, {}", seasonTournamentId, name);
            throw new InvalidParameterException("required parameter null");
        }

        if(seasonTournamentLocationRepo.findByName(name) != null){
            LOG.error("duplicate name: {}", name);
            return false;
        }

        seasonTournament = seasonTournamentRepo.findOne(seasonTournamentId);

        if(seasonTournament == null){
            LOG.error("wrong reference id: {}", seasonTournamentId);
            return false;
        }


        seasonTournamentLocation = new SeasonTournamentLocation(seasonTournament, name);
        seasonTournamentLocation  = seasonTournamentLocationRepo.saveAndFlush(seasonTournamentLocation);

        if (seasonTournamentLocation == null) {
            LOG.error("save has failed: {}", name);
            return false;
        }

        return true;
    }
}
