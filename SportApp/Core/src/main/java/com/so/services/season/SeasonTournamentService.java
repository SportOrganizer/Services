/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.services.season;

import com.so.dal.model.Tournament;
import com.so.dal.model.season.Season;
import com.so.dal.model.season.SeasonTournament;
import com.so.dal.repository.season.SeasonTournamentRepository;
import com.so.services.TournamentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.List;


@Service
public class SeasonTournamentService {

    private final static Logger LOG = LoggerFactory.getLogger(SeasonTournamentService.class); /// logovanie..

    @Autowired
    SeasonTournamentRepository seasonTournamentRepo;

    @Autowired
    SeasonService seasonService;

    @Autowired
    TournamentService tournamentService;

    public SeasonTournament findById(Integer id){
        LOG.info("findById({})", id);
        if(id == null){
            LOG.error("id can't be null: {}", id);
            throw new InvalidParameterException("required parameter null");
        }

        SeasonTournament s = seasonTournamentRepo.findOne(id);
        return s;
    }

    public List<SeasonTournament> findByNameContaining(String name){
        LOG.info("findByNameContaining({})", name);

        if(name == null){
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }
        List<SeasonTournament> ls= seasonTournamentRepo.findByNameContaining(name);
        return ls;
    }

    public SeasonTournament findByName(String name){
        LOG.info("findByNameContaining({})", name);
        if(name == null){
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }

        SeasonTournament s = seasonTournamentRepo.findByName(name);
        return  s;
    }

    public List<SeasonTournament> findAll(){
        LOG.info("findAll()");

        List<SeasonTournament> ls= seasonTournamentRepo.findAll();
        return ls;
    }

    @Transactional
    public Boolean createSeasonTournament(Integer seasonId, Integer tournamentId, String name) {
        LOG.info("createSeasonTournament({},{},{})",seasonId,tournamentId,name);

        Season season;
        Tournament tournament;
        SeasonTournament seasonTournament;

        if(seasonId == null || tournamentId == null || name == null){
            LOG.error("required can't be null: {}, {}, {}", seasonId,tournamentId,name);
            throw new InvalidParameterException("required parameter null");
        }

        if(seasonTournamentRepo.findByName(name) != null){
            LOG.error("duplicate name: {}", name);
            return false;
        }

        season = seasonService.findById(seasonId);

        if(season == null){
            LOG.error("wrong reference id: {}", seasonId);
            return false;
        }

        tournament = tournamentService.findById(tournamentId);
        if(tournament == null){
            LOG.error("wrong reference id: {}", tournamentId);
            return false;
        }

        seasonTournament = new SeasonTournament(season, tournament, name);
        seasonTournament  = seasonTournamentRepo.saveAndFlush(seasonTournament);

        if (seasonTournament == null) {
            LOG.error("save has failed: {}", name);
            return false;
        }

        return true;
    }



}
