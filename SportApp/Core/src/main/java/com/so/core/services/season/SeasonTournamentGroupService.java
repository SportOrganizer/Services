/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.season;

import com.so.core.controller.converter.season.SeasonTournamentConverter;
import com.so.core.controller.dto.season.SeasonTournamentGroupDTO;
import com.so.core.exception.AppException;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.model.season.SeasonTournamentGroup;
import com.so.dal.core.repository.season.SeasonTournamentGroupRepository;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    SeasonTournamentRepository seasonTournamentRepo;

    @Autowired
    private SeasonTournamentConverter stConverter;

    @Transactional
    public List<SeasonTournamentGroupDTO> findAll() {
        LOG.info("findAll()");
        List<SeasonTournamentGroupDTO> l = new ArrayList<>();
        List<SeasonTournamentGroup> ls = seasonTournamentGroupRepo.findAll();

        for (SeasonTournamentGroup g : ls) {
            l.add(stConverter.groupEntityToDto(g));
        }

        return l;
    }
    @Transactional
    public SeasonTournamentGroupDTO findById(Integer id) throws AppException {
        LOG.info("findById({})", id);

        if (id == null) {
            LOG.error("id can't be null: {}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "required parameter null");
        }
        SeasonTournamentGroup s = seasonTournamentGroupRepo.findOne(id);
        if (s == null) {
            LOG.error("nenajdena group s id: {}", id);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdena group s id: " + id);
        }
        return stConverter.groupEntityToDto(s);
    }
    
    @Transactional
    public List<SeasonTournamentGroup> findByNameContaining(String name) {
        LOG.info("findByNameContaining({})", name);

        if (name == null) {
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }
        List<SeasonTournamentGroup> ls = seasonTournamentGroupRepo.findByNameContaining(name);
        return ls;
    }

    @Transactional
    public SeasonTournamentGroupDTO createSeasonTournamentGroup(SeasonTournamentGroupDTO group) throws AppException {
        LOG.info("createSeasonTournamentGroup({},{})", group.getId(), group.getName());

        SeasonTournament seasonTournament;
        SeasonTournamentGroup seasonTournamentGroup;

        if (group.getName() == null) {
            LOG.error("nevyplnene povinne Parametre: {}", group.getName());
            throw new AppException(HttpStatus.BAD_REQUEST, "required parameter null");
        }

        //TODO: kontrola len pre 1 seasonTournment nie vseobecne alebo vytvarat automaticky
        //   if(seasonTournamentGroupRepo.findByName(name) != null){
        //      LOG.error("duplicate name: {}", name);
        //     return false;
        //  }
        seasonTournament = seasonTournamentRepo.findOne(group.getSeasonTournamentId());

        if (seasonTournament == null) {
            LOG.error("wrong reference id: {}", group.getSeasonTournamentId());
            throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje seasonTournament s ID: " + group.getSeasonTournamentId());
        }

        seasonTournamentGroup = new SeasonTournamentGroup(seasonTournament, group.getName());
        seasonTournamentGroup = seasonTournamentGroupRepo.saveAndFlush(seasonTournamentGroup);

        if (seasonTournamentGroup == null) {
            LOG.error("save has failed: {}", group.getName());
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "chyba pri ukladani group: " + group.getName());
        }
        return stConverter.groupEntityToDto(seasonTournamentGroup);
    }

    @Transactional
    public List<SeasonTournamentGroupDTO> findAllBySeasonTournament(Integer stId) throws AppException {
        LOG.info("findAllBySeasonTournament({})", stId);
        SeasonTournament st = seasonTournamentRepo.findOne(stId);
        if (st == null) {
            LOG.error("nenajdeny seasonTournament s id:{}", stId);
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny seasonTournament s id:" + stId);
        }
        List<SeasonTournamentGroup> ls = seasonTournamentGroupRepo.findBySeasonTournament(st);
        List<SeasonTournamentGroupDTO> l = new ArrayList<>();
        for (SeasonTournamentGroup g : ls) {
            l.add(stConverter.groupEntityToDto(g));
        }
        return l;
    }

    @Transactional
    public SeasonTournamentGroupDTO update(SeasonTournamentGroupDTO updated) throws AppException {
        LOG.info("update()");
        SeasonTournamentGroup s = stConverter.groupDtoToEntity(updated);

        s = seasonTournamentGroupRepo.saveAndFlush(s);

        if (s == null) {
            LOG.error("nepodarilo sa ulozit stGround do db");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "SeasonTournamentGround sa nepodarilo aktualizovat");
        }
        return stConverter.groupEntityToDto(s);
    }
}
