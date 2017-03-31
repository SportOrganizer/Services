/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.season;

import com.so.core.controller.converter.season.SeasonConverter;
import com.so.core.controller.dto.season.SeasonDTO;
import com.so.core.exception.AppException;
import com.so.dal.core.model.season.Season;
import com.so.dal.core.repository.season.SeasonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

@Service
public class SeasonService {

    private final static Logger LOG = LoggerFactory.getLogger(SeasonService.class); /// logovanie..

    @Autowired
    private SeasonRepository seasonRepo;

    @Autowired
    private SeasonConverter seasonConverter;

    @Transactional
    public SeasonDTO findById(Integer id) throws AppException {
        LOG.info("findById({})", id);
        if (id == null) {
            LOG.error("nevyplneny povinny atribut id:{}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplneny povinny atribut id=" + id);
        }
        Season s = seasonRepo.findOne(id);
        if (s == null) {
            LOG.error("k danemu id neexistuje zaznam v dbs");
            throw new AppException(HttpStatus.NOT_FOUND, "pre dane id neexistuje Season id=" + id);
        }
        return seasonConverter.entityToDto(s, true);
    }

    @Transactional
    public List<SeasonDTO> findByNameContaining(String name) throws AppException {
        LOG.info("findByNameContaining({})", name);

        if (name == null) {
            LOG.error("nevyplneny povinny atribut name:{}", name);
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplneny povinny atribut id=" + name);
        }
        List<SeasonDTO> seasonList = new ArrayList<>();
        List<Season> ls = seasonRepo.findByNameContaining(name);

        for (Season s : ls) {
            seasonList.add(seasonConverter.entityToDto(s, false));
        }

        return seasonList;
    }

    @Transactional
    public Season findByName(String name) throws AppException {
        LOG.info("findByNameContaining({})", name);
        if (name == null) {
            LOG.error("nevyplneny povinny atribut name:{}", name);
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplneny povinny atribut name=" + name);
        }
        Season s = seasonRepo.findByName(name);
        if (s == null) {
            LOG.error("k danemu name neexistuje zaznam v dbs");
            throw new AppException(HttpStatus.NOT_FOUND, "pre dane name neexistuje Season name=" + name);
        }
        return s;
    }

    @Transactional
    public List<SeasonDTO> findAll() throws AppException {
        LOG.info("findAll()");
        List<Season> ls = seasonRepo.findAll();
        List<SeasonDTO> seasonList = new ArrayList<>();
        for (Season s : ls) {
            seasonList.add(seasonConverter.entityToDto(s, false));
        }
        return seasonList;
    }

    @Transactional
    public SeasonDTO createSeason(String name) throws AppException {
        LOG.info("createSeason({})", name);

        if (name == null) {
            LOG.error("nevyplneny povinny atribut name:{}", name);
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplneny povinny atribut name=" + name);
        }

        if (seasonRepo.findByName(name) != null) {
            LOG.error("dane meno sa uz pouziva: {}", name);
            throw new AppException(HttpStatus.BAD_REQUEST, "tim s takymto nazvom uz existuje=" + name);
        }

        Season s = new Season(name);
        s = seasonRepo.saveAndFlush(s);

        if (s == null) {
            LOG.error("sesona:{} sa neulozila do databazy");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "sezona:" + name + " sa neulozila do databazy");
        }

        return seasonConverter.entityToDto(s, true);

    }

    @Transactional
    public void deleteSeason(Integer id) throws AppException {
        LOG.info("deleteSeason({})", id);
        Season s = seasonRepo.findOne(id);

        if (s == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "seasonTournament s id=" + id + "nebol najdeny");
        }
        seasonRepo.delete(s);
    }

    @Transactional
    public SeasonDTO update(SeasonDTO updated) throws AppException {
        LOG.info("update()");
        Season s = seasonConverter.dtoToEntity(updated);
        Season saved = seasonRepo.saveAndFlush(s);

        if (saved == null) {
            LOG.error("nepodarilo sa aktualizovat season");
            throw new AppException(HttpStatus.NOT_MODIFIED, "nepodarilo sa aktualizovat season");
        }
        return seasonConverter.entityToDto(saved, true);
    }

}
