/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services;

import com.so.core.controller.converter.TournamentConverter;
import com.so.core.controller.dto.TournamentDTO;
import com.so.core.exception.AppException;
import com.so.dal.core.model.Tournament;
import com.so.dal.core.repository.TournamentRepository;
import java.io.IOException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.http.HttpStatus;

@Service
public class TournamentService {

    private final static Logger LOG = LoggerFactory.getLogger(TournamentService.class); /// logovanie..

    @Autowired
    TournamentRepository tournamentRepo;

    @Autowired
    TournamentConverter tournamentConverter;

    @Transactional
    public List<TournamentDTO> findAll() throws AppException {
        LOG.info("findAll()");

        List<TournamentDTO> l = new ArrayList<>();

        List<Tournament> lt = tournamentRepo.findAll();

        for (Tournament t : lt) {
            l.add(tournamentConverter.entityToDto(t, false));
        }
        return l;
    }

    @Transactional
    public List<TournamentDTO> findByNameContaining(String name) throws AppException {
        LOG.info("findByNameContaining({})", name);

        if (name == null) {
            LOG.error("parameter name nemoze byt null name: {}", name);
            throw new AppException(HttpStatus.BAD_REQUEST, "parameter name nemoze byt null");
        }
        List<Tournament> lt = tournamentRepo.findByNameContaining(name);
        List<TournamentDTO> l = new ArrayList<>();

        for (Tournament t : lt) {
            l.add(tournamentConverter.entityToDto(t, false));
        }

        return l;
    }

    public TournamentDTO findByName(String name) throws AppException {
        Tournament s = tournamentRepo.findByName(name);
        if (s == null) {
            LOG.error("nenajdeny turnaj s menom: {}", name);
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny turnaj s menom:" + name);
        }
        return tournamentConverter.entityToDto(s, true);
    }

    @Transactional
    public TournamentDTO findById(Integer id) throws AppException {
        LOG.info("findById({})", id);
        if (id == null) {
            LOG.error("id nemoze byt null: {}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "parameter id nemoze byt null");
        }

        Tournament t = tournamentRepo.findOne(id);

        if (t == null) {
            LOG.error("nenajdeny tournament s id: {}", id);
            throw new AppException(HttpStatus.NOT_FOUND, " nenajdeny tournament s id:" + id);
        }

        return tournamentConverter.entityToDto(t, true);
    }

    @Transactional
    public TournamentDTO createTournament(TournamentDTO dto) throws IOException, AppException {
        LOG.info("createTournament({})", dto.getName());

        if (dto.getName() == null) {
            LOG.error("parameter name nemoze byt null: {}", dto.getName());
            throw new AppException(HttpStatus.BAD_REQUEST, "nie je zadany parameter name");
        }

        if (tournamentRepo.findByName(dto.getName()) != null) {
            LOG.error("duplicitne meno: {}", dto.getName());
            throw new AppException(HttpStatus.CONFLICT, "dane meno turnaja sa uz pouziva name=" + dto.getName());
        }
        Tournament t = tournamentConverter.dtoToEntity(dto);
        Tournament tournament = tournamentRepo.saveAndFlush(t);

        if (tournament == null) {
            LOG.error("ulozenie turnaja sa nepodarilo tournament: {}", dto.getName());
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "nepodarilo sa ulozit turnaj do db");
        }
        return tournamentConverter.entityToDto(tournament, false);
    }

    @Transactional
    public void deleteTournament(Integer id) throws AppException {
        LOG.info("deleteTournament({})", id);
        Tournament t = tournamentRepo.findOne(id);

        if (t == null) {
            LOG.error("nepodarilo sa vymazat turnaj, turnaj s id={} nenajdeny", id);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdeny turnaj s id=" + id);
        }
        tournamentRepo.delete(t);
    }

    @Transactional
    public TournamentDTO update(TournamentDTO updated) throws AppException {
        LOG.info("update()");

        if (updated == null) {
            LOG.error("na vstupe updatu mam prazdny objekt");
            throw new AppException(HttpStatus.BAD_REQUEST, "zle vyplneny objekt");
        }
        Tournament t = tournamentConverter.dtoToEntity(updated);

        Tournament saved = tournamentRepo.saveAndFlush(t);

        if (saved == null) {
            LOG.error("turnaj sa nepodarilo ulozit do databazy");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "nepodarilo sa ulozit turnaj do db");
        }
        return tournamentConverter.entityToDto(saved, false);
    }
}
