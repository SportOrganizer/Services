/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.season;

import com.so.core.controller.converter.season.SeasonTournamentConverter;
import com.so.core.controller.dto.season.SeasonTournamentRoundDTO;
import com.so.core.exception.AppException;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.model.season.SeasonTournamentRound;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
import com.so.dal.core.repository.season.SeasonTournamentRoundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

@Service
public class SeasonTournamentRoundService {

    private final static Logger LOG = LoggerFactory.getLogger(SeasonTournamentRoundService.class); /// logovanie..

    @Autowired
    SeasonTournamentRoundRepository seasonTournamentRoundRepository;

    @Autowired
    SeasonTournamentRepository seasonTournamentRepo;

    @Autowired
    private SeasonTournamentConverter stConverter;

    @Autowired
    private SeasonTournamentRoundRepository roundRepo;

    public SeasonTournamentRoundDTO findById(Integer id) throws AppException {
        LOG.info("findById({})", id);
        if (id == null) {
            LOG.error("id can't be null: {}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "required parameter null");
        }

        SeasonTournamentRound s = seasonTournamentRoundRepository.findOne(id);

        if (s == null) {
            LOG.error("nenajdena location s id: {}", id);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdena location s id: " + id);
        }
        return stConverter.roundEntityToDto(s);
    }

    public List<SeasonTournamentRound> findByNameContaining(String name) {
        LOG.info("findByNameContaining({})", name);

        if (name == null) {
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }
        List<SeasonTournamentRound> ls = seasonTournamentRoundRepository.findByNameContaining(name);
        return ls;
    }

    public SeasonTournamentRound findByName(String name) {
        LOG.info("findByNameContaining({})", name);
        if (name == null) {
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }

        SeasonTournamentRound s = seasonTournamentRoundRepository.findByName(name);
        return s;
    }

    public List<SeasonTournamentRoundDTO> findAll() {
        LOG.info("findAll()");
        List<SeasonTournamentRoundDTO> l = new ArrayList<>();
        List<SeasonTournamentRound> ls = seasonTournamentRoundRepository.findAll();
        for (SeasonTournamentRound s : ls) {
            l.add(stConverter.roundEntityToDto(s));
        }
        return l;
    }

    @Transactional
    public SeasonTournamentRoundDTO createSeasonTournamentRound(SeasonTournamentRoundDTO round) throws AppException {
        LOG.info("createSeasonTournamentRound({},{})", round.getSeasonTournamentId(), round.getName());

        SeasonTournament seasonTournament;
        SeasonTournamentRound seasonTournamentRound;

        if (round.getName() == null) {
            LOG.error("nevyplnene povinne Parametre: {}", round.getName());
            throw new AppException(HttpStatus.BAD_REQUEST, "required parameter null");
        }
        //TODO: kontrolovat len pre ST nie globalne
//        if(seasonTournamentLocationRepo.findByName(name) != null){
//            LOG.error("duplicate name: {}", name);
//            return false;
//        }

        seasonTournament = seasonTournamentRepo.findOne(round.getSeasonTournamentId());

        if (seasonTournament == null) {
            LOG.error("wrong reference id: {}", round.getSeasonTournamentId());
            throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje seasonTournament s ID: " + round.getSeasonTournamentId());
        }

        seasonTournamentRound = new SeasonTournamentRound(seasonTournament, round.getName());
        seasonTournamentRound = roundRepo.saveAndFlush(seasonTournamentRound);

        if (seasonTournamentRound == null) {
            LOG.error("save has failed: {}", round.getName());
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "chyba pri ukladani group: " + round.getName());
        }

        return stConverter.roundEntityToDto(seasonTournamentRound);
    }

    public List<SeasonTournamentRoundDTO> findAllBySeasonTournament(Integer stId) throws AppException {
        LOG.info("findAllBySeasonTournament({})", stId);
        SeasonTournament st = seasonTournamentRepo.findOne(stId);
        if (st == null) {
            LOG.error("nenajdeny seasonTournament s id:{}", stId);
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny seasonTournament s id:" + stId);
        }
        List<SeasonTournamentRound> ls = seasonTournamentRoundRepository.findBySeasonTournament(st);
        List<SeasonTournamentRoundDTO> l = new ArrayList<>();
        for (SeasonTournamentRound g : ls) {
            l.add(stConverter.roundEntityToDto(g));
        }
        return l;
    }

    @Transactional
    public SeasonTournamentRoundDTO update(SeasonTournamentRoundDTO updated) throws AppException {
        LOG.info("update()");
        SeasonTournamentRound s = stConverter.roundDtoToEntity(updated);

        s = seasonTournamentRoundRepository.saveAndFlush(s);

        if (s == null) {
            LOG.error("nepodarilo sa ulozit stRound do db");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "SeasonTournamentRound sa nepodarilo aktualizovat");
        }
        return stConverter.roundEntityToDto(s);
    }
}
