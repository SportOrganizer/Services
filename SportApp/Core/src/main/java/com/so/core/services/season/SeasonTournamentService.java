/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.season;

import com.so.core.controller.converter.season.SeasonTournamentConverter;
import com.so.core.controller.dto.ResourceDto;
import com.so.core.controller.dto.season.SeasonTournamentDTO;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
import com.so.core.services.TournamentService;
import com.so.core.services.document.DocumentService;
import com.so.dal.core.model.Resource;
import com.so.dal.core.repository.season.SeasonRepository;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeasonTournamentService {

    private final static Logger LOG = LoggerFactory.getLogger(SeasonTournamentService.class); /// logovanie..

    @Autowired
    SeasonTournamentRepository seasonTournamentRepo;

    @Autowired
    SeasonService seasonService;

    @Autowired
    SeasonRepository seasonRepo;

    @Autowired
    TournamentService tournamentService;

    @Autowired
    DocumentService documentService;

    @Autowired
    SeasonTournamentConverter seasonTournamentConverter;

    @Transactional
    public SeasonTournamentDTO findById(Integer id) {
        LOG.info("findById({})", id);
        if (id == null) {
            LOG.error("id can't be null: {}", id);
            throw new InvalidParameterException("required parameter null");
        }

        SeasonTournament s = seasonTournamentRepo.findOne(id);

        if (s == null) {
            return new SeasonTournamentDTO();
        }

        return seasonTournamentConverter.entityToDto(s);
    }

    @Transactional
    public List<SeasonTournamentDTO> findByNameContaining(String name) {
        LOG.info("findByNameContaining({})", name);

        if (name == null) {
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }
        List<SeasonTournament> ls = seasonTournamentRepo.findByNameContaining(name);
        List<SeasonTournamentDTO> l = new ArrayList<>();

        for (SeasonTournament st : ls) {
            l.add(seasonTournamentConverter.entityToDto(st));
        }

        return l;
    }

    @Transactional
    public SeasonTournamentDTO findByName(String name) {
        LOG.info("findByNameContaining({})", name);
        if (name == null) {
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }

        SeasonTournament s = seasonTournamentRepo.findByName(name);

        if (s == null) {
            return new SeasonTournamentDTO();
        }

        return seasonTournamentConverter.entityToDto(s);
    }

    @Transactional
    public List<SeasonTournamentDTO> findAll() {
        LOG.info("findAll()");

        List<SeasonTournamentDTO> l = new ArrayList<>();

        List<SeasonTournament> ls = seasonTournamentRepo.findAll();

        for (SeasonTournament st : ls) {
            l.add(seasonTournamentConverter.entityToDto(st));
        }
        return l;
    }

    @Transactional
    public SeasonTournamentDTO createSeasonTournament(SeasonTournamentDTO dto) throws IOException {
        LOG.info("createSeasonTournament({},{},{})", dto.getSeasonId(), dto.getName(), dto.getTournamentId());

        if (dto.getSeasonId() == null || dto.getTournamentId() == null || dto.getName() == null) {
            LOG.error("required can't be null: {}, {}, {}", dto.getSeasonId(), dto.getTournamentId(), dto.getName());
            throw new InvalidParameterException("required parameter null");
        }

        if (seasonTournamentRepo.findByName(dto.getName()) != null) {
            LOG.error("duplicate name: {}", dto.getName());
            throw new InvalidParameterException("duplicate name");
        }
        Resource r = documentService.createFile(dto.getLogo().getData(), dto.getLogo().getMimeType());

        dto.setLogo(new ResourceDto(r.getId(), r.getPath()));

        SeasonTournament s = seasonTournamentConverter.dtoToEntity(dto);
        SeasonTournament seasonTournament = seasonTournamentRepo.saveAndFlush(s);

        if (seasonTournament == null) {
            LOG.error("save has failed: {}", dto.getName());
            throw new InvalidParameterException("save has faild");
        }

        return seasonTournamentConverter.entityToDto(seasonTournament);
    }

    @Transactional
    public void deleteSeasonTournament(Integer id) {
        LOG.info("deleteSeasonTournament({})",id);
        SeasonTournament s = seasonTournamentRepo.findOne(id);

        if (s == null) {
            throw new InvalidParameterException("nenajdene seasonTournament");
        }
        if (s.getResource() != null) {
            documentService.deleteFile(s.getResource());
        }

        seasonTournamentRepo.delete(s);
    }

    @Transactional
    public SeasonTournamentDTO update(SeasonTournamentDTO updated) {
     LOG.info("update()");
        SeasonTournament s = seasonTournamentConverter.dtoToEntity(updated);

        SeasonTournament saved = seasonTournamentRepo.saveAndFlush(s);

        if (saved == null) {
            LOG.error("nepodarilo sa ulozit st do db");
            throw new IllegalStateException("nepodarilo sa ulozit st do db");
        }
        return seasonTournamentConverter.entityToDto(saved);
    }
}
