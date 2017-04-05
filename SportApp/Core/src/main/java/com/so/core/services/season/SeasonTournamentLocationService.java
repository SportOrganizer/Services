/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.season;

import com.so.core.controller.converter.season.SeasonTournamentConverter;
import com.so.core.controller.dto.season.SeasonTournamentLocationDTO;
import com.so.core.exception.AppException;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.model.season.SeasonTournamentLocation;
import com.so.dal.core.repository.season.SeasonTournamentLocationRepository;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
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
public class SeasonTournamentLocationService {

    private final static Logger LOG = LoggerFactory.getLogger(SeasonTournamentLocationService.class);

    @Autowired
    SeasonTournamentLocationRepository seasonTournamentLocationRepo;

    @Autowired
    SeasonTournamentRepository seasonTournamentRepo;

    @Autowired
    private SeasonTournamentConverter stConverter;

    public SeasonTournamentLocationDTO findById(Integer id) throws AppException {
        LOG.info("findById({})", id);
        if (id == null) {
            LOG.error("id can't be null: {}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "required parameter null");
        }

        SeasonTournamentLocation s = seasonTournamentLocationRepo.findOne(id);

        if (s == null) {
            LOG.error("nenajdena location s id: {}", id);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdena location s id: " + id);
        }
        return stConverter.locationEntityToDto(s);
    }

    public List<SeasonTournamentLocation> findByNameContaining(String name) {
        LOG.info("findByNameContaining({})", name);

        if (name == null) {
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }
        List<SeasonTournamentLocation> ls = seasonTournamentLocationRepo.findByNameContaining(name);
        return ls;
    }

    public SeasonTournamentLocation findByName(String name) {
        LOG.info("findByName({})", name);
        if (name == null) {
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }

        SeasonTournamentLocation s = seasonTournamentLocationRepo.findByName(name);
        return s;
    }

    public List<SeasonTournamentLocationDTO> findAll() {
        LOG.info("findAll()");

        List<SeasonTournamentLocationDTO> l = new ArrayList<>();

        List<SeasonTournamentLocation> ls = seasonTournamentLocationRepo.findAll();

        for (SeasonTournamentLocation s : ls) {
            l.add(stConverter.locationEntityToDto(s));
        }

        return l;
    }

    @Transactional
    public SeasonTournamentLocationDTO createSeasonTournamentLocation(SeasonTournamentLocationDTO location) throws AppException {
        LOG.info("createSeasonTournamentLocation({},{})", location.getSeasonTournamentId(), location.getName());

        SeasonTournament seasonTournament;
        SeasonTournamentLocation seasonTournamentLocation;

        if (location.getName() == null) {
            LOG.error("nevyplnene povinne Parametre: {}",location.getName());
            throw new AppException(HttpStatus.BAD_REQUEST, "required parameter null");
        }
        //TODO: kontrolovat len pre ST nie globalne
//        if(seasonTournamentLocationRepo.findByName(name) != null){
//            LOG.error("duplicate name: {}", name);
//            return false;
//        }

        seasonTournament = seasonTournamentRepo.findOne(location.getSeasonTournamentId());

        if (seasonTournament == null) {
            LOG.error("wrong reference id: {}", location.getSeasonTournamentId());
            throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje seasonTournament s ID: " + location.getSeasonTournamentId());
        }

        seasonTournamentLocation = new SeasonTournamentLocation(seasonTournament, location.getName());
        seasonTournamentLocation = seasonTournamentLocationRepo.saveAndFlush(seasonTournamentLocation);

        if (seasonTournamentLocation == null) {
            LOG.error("save has failed: {}", location.getName());
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "chyba pri ukladani group: " + location.getName());
        }

        return stConverter.locationEntityToDto(seasonTournamentLocation);
    }
    
      public List<SeasonTournamentLocationDTO> findAllBySeasonTournament(Integer stId) throws AppException{
      LOG.info("findAllBySeasonTournament({})",stId);
      SeasonTournament st = seasonTournamentRepo.findOne(stId);
      if(st==null){
      LOG.error("nenajdeny seasonTournament s id:{}",stId);
      throw new AppException(HttpStatus.BAD_REQUEST,"nenajdeny seasonTournament s id:"+stId);
  }
      List<SeasonTournamentLocation> ls = seasonTournamentLocationRepo.findBySeasonTournament(st);
      List<SeasonTournamentLocationDTO> l = new ArrayList<>();
        for (SeasonTournamentLocation g : ls) {
            l.add(stConverter.locationEntityToDto(g));
        }
      return l;
   }
}
