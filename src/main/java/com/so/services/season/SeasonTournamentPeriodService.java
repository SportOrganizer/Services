/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.services.season;

import com.so.dal.model.season.SeasonTournament;
import com.so.dal.model.season.SeasonTournamentPeriod;
import com.so.dal.model.season.SeasonTournamentRound;
import com.so.dal.repository.season.SeasonTournamentPeriodRepository;
import com.so.dal.repository.season.SeasonTournamentRoundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;


@Service
public class SeasonTournamentPeriodService {

    private final static Logger LOG = LoggerFactory.getLogger(SeasonTournamentPeriodService.class); /// logovanie..

    @Autowired
    SeasonTournamentPeriodRepository seasonTournamentPeriodRepository;

    @Autowired
    SeasonTournamentService seasonTournamentService;


    public SeasonTournamentPeriod findById(Integer id){
        LOG.info("findById({})", id);
        if(id == null){
            LOG.error("id can't be null: {}", id);
            throw new InvalidParameterException("required parameter null");
        }

        SeasonTournamentPeriod str = seasonTournamentPeriodRepository.findOne(id);
        return str;
    }

    public List<SeasonTournamentPeriod> findByNameContaining(String name){
        LOG.info("findByNameContaining({})", name);

        if(name == null){
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }
        List<SeasonTournamentPeriod> ls= seasonTournamentPeriodRepository.findByNameContaining(name);
        return ls;
    }

    public SeasonTournamentPeriod findByName(String name){
        LOG.info("findByNameContaining({})", name);
        if(name == null){
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }

        SeasonTournamentPeriod  s = seasonTournamentPeriodRepository.findByName(name);
        return  s;
    }

    public List<SeasonTournamentPeriod> findAll(){
        LOG.info("findAll()");

        List<SeasonTournamentPeriod> ls= seasonTournamentPeriodRepository.findAll();
        return ls;
    }

    @Transactional
    public Boolean createSeasonTournamentPeriod(Integer seasonTournamentId,  String name, Date length) {
        LOG.info("createSeasonTournamentPeriod({},{},{})",seasonTournamentId,name,length);

        SeasonTournament seasonTournament;
        SeasonTournamentPeriod seasonTournamentPeriod ;

        if(seasonTournamentId == null || name == null || length == null){
            LOG.error("required can't be null: {}, {},{}", seasonTournamentId,name,length);
            throw new InvalidParameterException("required parameter null");
        }

        if(seasonTournamentPeriodRepository.findByName(name) != null){
            LOG.error("duplicate name: {}", name);
            return false;
        }

        seasonTournament = seasonTournamentService.findById(seasonTournamentId);

        if(seasonTournament == null){
            LOG.error("wrong reference id: {}", seasonTournament);
            return false;
        }

        seasonTournamentPeriod = new SeasonTournamentPeriod(seasonTournament, name, length);
        seasonTournamentPeriod  = seasonTournamentPeriodRepository.saveAndFlush(seasonTournamentPeriod);

        if (seasonTournamentPeriod == null) {
            LOG.error("save has failed: {}", name);
            return false;
        }

        return true;
    }



}
