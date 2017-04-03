/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.season;

import com.so.core.controller.converter.season.SeasonTournamentConverter;
import com.so.core.controller.dto.ResourceDto;
import com.so.core.controller.dto.season.SeasonTournamentDTO;
import com.so.core.exception.AppException;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
import com.so.core.services.document.DocumentService;
import com.so.dal.core.model.Resource;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

@Service
public class SeasonTournamentService {

    private final static Logger LOG = LoggerFactory.getLogger(SeasonTournamentService.class); /// logovanie..

    @Autowired
    private SeasonTournamentRepository seasonTournamentRepo;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private SeasonTournamentConverter seasonTournamentConverter;

    @Transactional
    public SeasonTournamentDTO findById(Integer id) throws AppException {
        LOG.info("findById({})", id);
        if (id == null) {
            LOG.error("Nevyplneny povinny atribut: {}", id);
            throw new AppException(HttpStatus.BAD_REQUEST,"nevyplneny povinny atribut id=" + id);
        }

        SeasonTournament s = seasonTournamentRepo.findOne(id);
        if (s == null) {
            LOG.info("nenajdeny seasonTournament podla id=", id);
           throw new AppException(HttpStatus.NOT_FOUND,"pre dane id neexistuje seasonTournament id=" + id);
        }
        return seasonTournamentConverter.entityToDto(s);
    }

    @Transactional
    public List<SeasonTournamentDTO> findByNameContaining(String name) throws AppException {
        LOG.info("findByNameContaining({})", name);

        if (name == null) {
            LOG.error("Nevyplneny povinny atribut: {}", name);
            throw new AppException(HttpStatus.BAD_REQUEST,"nevyplneny povinny atribut name=" + name);
        }
        List<SeasonTournament> ls = seasonTournamentRepo.findByNameContaining(name);
        List<SeasonTournamentDTO> l = new ArrayList<>();

        for (SeasonTournament st : ls) {
            l.add(seasonTournamentConverter.entityToDto(st));
        }

        return l;
    }

    @Transactional
    public SeasonTournamentDTO findByName(String name) throws AppException {
        LOG.info("findByNameContaining({})", name);
        if (name == null) {
            LOG.error("Nevyplneny povinny atribut: {}", name);
            throw new AppException(HttpStatus.BAD_REQUEST,"nevyplneny povinny atribut name=" + name);
        }

        SeasonTournament s = seasonTournamentRepo.findByName(name);

        if (s == null) {
            LOG.info("nenajdeny SeasonTournament podla mena=" + name);
            throw new AppException(HttpStatus.NOT_FOUND,"pre dany nazov neexistuje seasonTournament name=" + name);
        }

        return seasonTournamentConverter.entityToDto(s);
    }

    @Transactional
    public List<SeasonTournamentDTO> findAll() throws AppException {
        LOG.info("findAll()");
        List<SeasonTournamentDTO> l = new ArrayList<>();
        List<SeasonTournament> ls = seasonTournamentRepo.findAll();

        for (SeasonTournament st : ls) {
            l.add(seasonTournamentConverter.entityToDto(st));
        }
        return l;
    }

    @Transactional
    public SeasonTournamentDTO createSeasonTournament(SeasonTournamentDTO dto) throws IOException, AppException {
        LOG.info("createSeasonTournament({},{},{})", dto.getSeasonId(), dto.getName(), dto.getTournamentId());

        if (dto.getSeasonId() == null || dto.getTournamentId() == null || dto.getName() == null) {
            LOG.error("required can't be null: {}, {}, {}", dto.getSeasonId(), dto.getTournamentId(), dto.getName());
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplnene povinne parametre");
        }

        if (seasonTournamentRepo.findByName(dto.getName()) != null) {
            LOG.error("nazov turnaja uz je pouzivany: {}", dto.getName());
            throw new AppException(HttpStatus.BAD_REQUEST, "nazov turnaja uz je pouzivany");
        }
        Resource r = documentService.createFile(dto.getLogo().getData(), dto.getLogo().getMimeType());

        dto.setLogo(new ResourceDto(r.getId(), r.getPath()));

        SeasonTournament s = seasonTournamentConverter.dtoToEntity(dto);
        SeasonTournament seasonTournament = seasonTournamentRepo.saveAndFlush(s);

        if (seasonTournament == null) {
            LOG.error("seasonTournament sa neulozit do databazy: {}", dto.getName());
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "seasonTournament:" + dto.getName() + " sa neulozit do databazy");
        }

        return seasonTournamentConverter.entityToDto(seasonTournament);
    }

    @Transactional
    public void deleteSeasonTournament(Integer id) throws AppException {
        LOG.info("deleteSeasonTournament({})", id);
        SeasonTournament s = seasonTournamentRepo.findOne(id);

        if (s == null) {
            LOG.error("nenajdeny SeasonTournament s id={}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny SeasonTournament s id=" + id);
        }
//        if (s.getResource() != null) {
//            documentService.deleteFile(s.getResource());
//        }

        seasonTournamentRepo.delete(s);
    }

    @Transactional
    public SeasonTournamentDTO update(SeasonTournamentDTO updated) throws AppException {
        LOG.info("update()");
        SeasonTournament s = seasonTournamentConverter.dtoToEntity(updated);
        
                if (updated.getLogo()!= null) {
            if (updated.getLogo().getData() != null && updated.getLogo().getMimeType() != null) {
                Resource r = documentService.createFile(updated.getLogo().getData(), updated.getLogo().getMimeType());
                s.setResource(r);
            }
        }
                
        SeasonTournament saved = seasonTournamentRepo.saveAndFlush(s);

        if (saved == null) {
            LOG.error("nepodarilo sa ulozit st do db");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "SeasonTournament sa nepodarilo aktualizovat");
        }
        return seasonTournamentConverter.entityToDto(saved);
    }
}
