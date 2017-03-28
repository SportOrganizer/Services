/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services;

import com.so.core.controller.converter.TournamentConverter;
import com.so.core.controller.dto.TournamentDTO;
import com.so.core.controller.dto.season.SeasonTournamentDTO;
import com.so.dal.core.model.Tournament;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.repository.TournamentRepository;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TournamentService {

    private final static Logger LOG = LoggerFactory.getLogger(TournamentService.class); /// logovanie..

    @Autowired
    TournamentRepository tournamentRepo;

    @Autowired
    TournamentConverter tournamentConverter;

    @Transactional
    public List<TournamentDTO> findAll() {
        LOG.info("findAll()");

        List<TournamentDTO> l = new ArrayList<>();

        List<Tournament> lt = tournamentRepo.findAll();

        for (Tournament t : lt) {
            l.add(tournamentConverter.entityToDto(t, false));
        }
        return l;
    }

    @Transactional
    public List<TournamentDTO> findByNameContaining(String name) {
        LOG.info("findByNameContaining({})", name);

        if (name == null) {
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }
        List<Tournament> lt = tournamentRepo.findByNameContaining(name);
        List<TournamentDTO> l = new ArrayList<>();

        for (Tournament t : lt) {
            l.add(tournamentConverter.entityToDto(t, false));
        }

        return l;
    }

    public Tournament findByName(String name) {
        Tournament s = tournamentRepo.findByName(name);
        return s;
    }

    @Transactional
    public TournamentDTO findById(Integer id) {
        LOG.info("findById({})", id);
        if (id == null) {
            LOG.error("id can't be null: {}", id);
            throw new InvalidParameterException("required parameter null");
        }

        Tournament t = tournamentRepo.findOne(id);

        if (t == null) {
            return new TournamentDTO();
        }

        return tournamentConverter.entityToDto(t, true);
    }

    @Transactional
    public TournamentDTO createTournament(TournamentDTO dto) throws IOException {

//        if (tournamentRepo.findByName(name) != null) {
//            return false;
//        }
//
//        Tournament s = new Tournament(name);
//        s = tournamentRepo.saveAndFlush(s);
//
//        if (s == null) {
//            return false;
//        }
//
//        return true;
        
        LOG.info("createTournament({})", dto.getName());

        if (dto.getName() == null) {
            LOG.error("required can't be null: {}",dto.getName());
            throw new InvalidParameterException("required parameter null");
        }

        if (tournamentRepo.findByName(dto.getName()) != null) {
            LOG.error("duplicate name: {}", dto.getName());
            throw new InvalidParameterException("duplicate name");
        }


        Tournament t = tournamentConverter.dtoToEntity(dto);
        Tournament tournament = tournamentRepo.saveAndFlush(t);

        if (tournament == null) {
            LOG.error("save has failed: {}", dto.getName());
            throw new InvalidParameterException("save has faild");
        }

        return tournamentConverter.entityToDto(tournament, false);
    }
    
    @Transactional
    public void deleteTournament(Integer id) {
        LOG.info("deleteTournament({})",id);
        Tournament t = tournamentRepo.findOne(id);

        if (t == null) {
            throw new InvalidParameterException("nenajdene seasonTournament");
        }

        tournamentRepo.delete(t);
    }

    @Transactional
    public TournamentDTO update(TournamentDTO updated) {
     LOG.info("update()");
        Tournament t = tournamentConverter.dtoToEntity(updated);

        Tournament saved = tournamentRepo.saveAndFlush(t);

        if (saved == null) {
            LOG.error("nepodarilo sa ulozit st do db");
            throw new IllegalStateException("nepodarilo sa ulozit st do db");
        }
        return tournamentConverter.entityToDto(saved, false);
    }
    

}
