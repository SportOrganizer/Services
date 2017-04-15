/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.season;

import com.so.core.controller.converter.season.SeasonTournamentConverter;
import com.so.core.controller.dto.season.SeasonTournamentPeriodDTO;
import com.so.core.exception.AppException;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.model.season.SeasonTournamentPeriod;
import com.so.dal.core.repository.season.SeasonTournamentPeriodRepository;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

@Service
public class SeasonTournamentPeriodService {

    private final static Logger LOG = LoggerFactory.getLogger(SeasonTournamentPeriodService.class); /// logovanie..

    @Autowired
    private SeasonTournamentPeriodRepository seasonTournamentPeriodRepository;

    @Autowired
    private SeasonTournamentRepository seasonTournamentRepo;

    @Autowired
    private SeasonTournamentConverter stConverter;

    public SeasonTournamentPeriodDTO findById(Integer id) throws AppException {
        LOG.info("findById({})", id);
        if (id == null) {
            LOG.error("id can't be null: {}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "required parameter null");
        }
        SeasonTournamentPeriod str = seasonTournamentPeriodRepository.findOne(id);
        if (str == null) {
            LOG.error("nenajdeny seasonPeriod s id {}", id);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdeny seasonPeriod s id: " + id);
        }
        return stConverter.stPeriodEntityToDto(str);
    }

    public List<SeasonTournamentPeriodDTO> findByNameContaining(String name) throws AppException {
        LOG.info("findByNameContaining({})", name);
        List<SeasonTournamentPeriodDTO> ls = new ArrayList<>();
        if (name == null) {
            LOG.error("name can't be null: {}", name);
            throw new AppException(HttpStatus.BAD_REQUEST, "required parameter null");
        }
        for (SeasonTournamentPeriod stp : seasonTournamentPeriodRepository.findByNameContaining(name)) {
            ls.add(stConverter.stPeriodEntityToDto(stp));
        }
        return ls;
    }

//    public SeasonTournamentPeriod findByName(String name) {
//        LOG.info("findByNameContaining({})", name);
//        if (name == null) {
//            LOG.error("name can't be null: {}", name);
//            throw new InvalidParameterException("required parameter null");
//        }
//
//        SeasonTournamentPeriod s = seasonTournamentPeriodRepository.findByName(name);
//        return s;
//    }
    public List<SeasonTournamentPeriodDTO> findBySt(Integer stId) throws AppException {
        LOG.info("findBySt({})", stId);
        if (stId == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "nezadany povinny atribut");
        }
        SeasonTournament st = seasonTournamentRepo.findOne(stId);
        if (st == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny seasonTournament s id:" + stId);
        }
        List<SeasonTournamentPeriodDTO> ls = new ArrayList<>();
        for (SeasonTournamentPeriod stp : seasonTournamentPeriodRepository.findBySeasonTournament(st)) {
            ls.add(stConverter.stPeriodEntityToDto(stp));
        }
        return ls;
    }

    @Transactional
    public void createSeasonTournamentPeriod(SeasonTournamentPeriodDTO dto) throws AppException {
        LOG.info("createSeasonTournamentPeriod({},{},{})", dto.getSeasonTournamentId(), dto.getName(), dto.getLength());

        SeasonTournamentPeriod seasonTournamentPeriod;

        if (dto.getSeasonTournamentId() == null || dto.getName() == null
                || dto.getLength() == null || dto.getIsGoldPart() == null) {
            LOG.error("required can't be null");
            throw new AppException(HttpStatus.BAD_REQUEST, "required parameter null");
        }

        seasonTournamentPeriod = stConverter.stPeriodDtoToEntity(dto);
        seasonTournamentPeriod = seasonTournamentPeriodRepository.saveAndFlush(seasonTournamentPeriod);

        if (seasonTournamentPeriod == null) {
            LOG.error("save has failed: {}", dto.getName());
            throw new AppException(HttpStatus.BAD_REQUEST, "chyba pri ukladani stPeriod");
        }
    }

    @Transactional
    public SeasonTournamentPeriodDTO editStPeriod(SeasonTournamentPeriodDTO stDto) throws AppException {
        LOG.info("editStPeriod()");
        SeasonTournamentPeriod stp = stConverter.stPeriodDtoToEntity(stDto);

        stp = seasonTournamentPeriodRepository.save(stp);

        if (stp == null) {
            LOG.error("nepodarilo sa ulozit upraveny stPeriod do databazy");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "nepodarilo sa ulozit stPeriod do dbs");
        }
        return stConverter.stPeriodEntityToDto(stp);
    }

}
