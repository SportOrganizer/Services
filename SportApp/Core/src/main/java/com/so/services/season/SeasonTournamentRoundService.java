/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.services.season;

import com.so.dal.model.season.SeasonTournament;
import com.so.dal.model.season.SeasonTournamentRound;
import com.so.dal.repository.season.SeasonTournamentRoundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.List;


@Service
public class SeasonTournamentRoundService {

    private final static Logger LOG = LoggerFactory.getLogger(SeasonTournamentRoundService.class); /// logovanie..

    @Autowired
    SeasonTournamentRoundRepository seasonTournamentRoundRepository;

    @Autowired
    SeasonTournamentService seasonTournamentService;


    public SeasonTournamentRound findById(Integer id){
        LOG.info("findById({})", id);
        if(id == null){
            LOG.error("id can't be null: {}", id);
            throw new InvalidParameterException("required parameter null");
        }

        SeasonTournamentRound str = seasonTournamentRoundRepository.findOne(id);
        return str;
    }

    public List<SeasonTournamentRound> findByNameContaining(String name){
        LOG.info("findByNameContaining({})", name);

        if(name == null){
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }
        List<SeasonTournamentRound> ls= seasonTournamentRoundRepository.findByNameContaining(name);
        return ls;
    }

    public SeasonTournamentRound findByName(String name){
        LOG.info("findByNameContaining({})", name);
        if(name == null){
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }

        SeasonTournamentRound  s = seasonTournamentRoundRepository.findByName(name);
        return  s;
    }

    public List<SeasonTournamentRound> findAll(){
        LOG.info("findAll()");

        List<SeasonTournamentRound> ls= seasonTournamentRoundRepository.findAll();
        return ls;
    }

    @Transactional
    public Boolean createSeasonTournamentRound(Integer seasonTournamentId,  String name) {
        LOG.info("createSeasonTournamentRound({},{})",seasonTournamentId,name);

        SeasonTournament seasonTournament;
        SeasonTournamentRound seasonTournamentRound ;

        if(seasonTournamentId == null || name == null){
            LOG.error("required can't be null: {}, {}", seasonTournamentId,name);
            throw new InvalidParameterException("required parameter null");
        }

        if(seasonTournamentRoundRepository.findByName(name) != null){
            LOG.error("duplicate name: {}", name);
            return false;
        }

        seasonTournament = seasonTournamentService.findById(seasonTournamentId);

        if(seasonTournament == null){
            LOG.error("wrong reference id: {}", seasonTournament);
            return false;
        }

        seasonTournamentRound = new SeasonTournamentRound(seasonTournament, name);
        seasonTournamentRound  = seasonTournamentRoundRepository.saveAndFlush(seasonTournamentRound);

        if (seasonTournamentRound == null) {
            LOG.error("save has failed: {}", name);
            return false;
        }

        return true;
    }



}
