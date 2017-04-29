/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.game;

import com.so.core.controller.converter.game.PenaltyConverter;
import com.so.core.controller.dto.season.penalty.PenaltyCustomDto;
import com.so.core.controller.dto.season.penalty.PenaltyDto;
import com.so.core.controller.dto.season.penalty.PenaltyTypeDto;
import com.so.core.controller.dto.season.penalty.SeasonTournamentPenaltySettingsDto;
import com.so.core.controller.dto.season.penalty.SeasonTournamentPenaltyTypeDto;
import com.so.core.exception.AppException;
import com.so.dal.core.model.Sports;
import com.so.dal.core.model.game.Penalty;
import com.so.dal.core.model.game.PenaltyType;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.model.season.SeasonTournamentPenaltySettings;
import com.so.dal.core.model.season.SeasonTournamentPenaltyType;
import com.so.dal.core.repository.SportsRepository;
import com.so.dal.core.repository.game.PenaltyRepository;
import com.so.dal.core.repository.game.PenaltyTypeRepository;
import com.so.dal.core.repository.season.SeasonTournamentPenaltySettingsRepository;
import com.so.dal.core.repository.season.SeasonTournamentPenaltyTypeRepository;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
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
 * @author peter
 */
@Service
public class PenaltyService {

    private final static Logger LOG = LoggerFactory.getLogger(PenaltyService.class);
    @Autowired
    private SportsRepository sportRepo;

    @Autowired
    private PenaltyRepository penaltyRepo;

    @Autowired
    private PenaltyTypeRepository penaltyTypeRepo;

    @Autowired
    private SeasonTournamentPenaltyTypeRepository stPenaltyTypeRepo;

    @Autowired
    private SeasonTournamentRepository stRepo;

    @Autowired
    private SeasonTournamentPenaltySettingsRepository stPenaltySettingsRepo;

    @Autowired
    private PenaltyConverter converter;

    //////////////////////PENALTY///////////////////
    @Transactional
    public PenaltyDto addPenalty(PenaltyDto penalty) throws AppException {
        LOG.info("addPenalty({})", penalty);
        if (penalty.getName() == null || penalty.getShortName() == null || penalty.getShortName() == null || penalty.getSportsId() == null) {
            LOG.error("nevplnene povinne parametre");
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplnene povinne parametre");
        }
        Penalty p = converter.penaltyDtoToEntity(penalty);
        p = penaltyRepo.saveAndFlush(p);

        if (p == null) {
            LOG.error("nepodarilo sa ulozit penalty do databazy");
            throw new AppException(HttpStatus.BAD_REQUEST, "nepodarilo sa ulozit penalty do databazy");
        }
        return converter.penaltyEntityToDto(p);
    }

    @Transactional
    public List<PenaltyDto> getAllPenalties() {
        LOG.info("getAllPenalties");
        List<Penalty> l = penaltyRepo.findAll();
        List<PenaltyDto> lDto = new ArrayList<>();

        for (Penalty p : l) {
            lDto.add(converter.penaltyEntityToDto(p));
        }
        return lDto;
    }

    @Transactional
    public List<PenaltyDto> getAllPenaltiesBySport(Integer sId) throws AppException {
        LOG.info("getAllPenaltiesBySport({})", sId);

        Sports s = sportRepo.findOne(sId);
        if (s == null) {
            LOG.error("nenajdeny sport podla id");
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny st sport id: " + sId);
        }
        List<Penalty> l = penaltyRepo.findBySports(s);
        List<PenaltyDto> lDto = new ArrayList<>();

        for (Penalty p : l) {
            lDto.add(converter.penaltyEntityToDto(p));
        }
        return lDto;

    }

    @Transactional
    public PenaltyDto getOnePenalty(Integer id) throws AppException {
        LOG.info("getOnePenalty");
        Penalty p = penaltyRepo.findOne(id);
        if (p == null) {
            LOG.error("nenajdena penalty podla id {}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdena penalty id: " + id);
        }
        return converter.penaltyEntityToDto(p);
    }

    // ak chcem prepouzit penalty pre iny sport
    //TODO NEFUNGUJE ENTITY SU AZ VELMI PERZISTENTNE
    @Transactional
    public PenaltyDto duplicatePenalty(PenaltyDto penalty) throws AppException {
        LOG.info("duplicatePenalty({})", penalty);
        if (penalty.getId() == null || penalty.getSportsId() == null) {
            LOG.error("nevplnene povinne parametre");
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplnene povinne parametre");
        }
        Sports s = sportRepo.findOne(penalty.getSportsId());
        if (s == null) {
            LOG.error("nenajdeny sport podla id {}", penalty.getSportsId());
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny sport id: " + penalty.getSportsId());
        }

        Penalty p = penaltyRepo.findOne(penalty.getId());
        if (p == null) {
            LOG.error("nenajdena penalty podla id {}", penalty.getId());
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdena penalty s id: " + penalty.getId());
        }
        Penalty duplicated = new Penalty(p.getName(),p.getShortName(),s);

        p = penaltyRepo.saveAndFlush(duplicated);

        if (p == null) {
            LOG.error("nepodarilo sa ulozit penalty do databazy");
            throw new AppException(HttpStatus.BAD_REQUEST, "nepodarilo sa ulozit penalty do databazy");
        }
        return converter.penaltyEntityToDto(p);
    }

    @Transactional
    public PenaltyDto editPenalty(PenaltyDto penalty) throws AppException {
        LOG.info("editPenalty({})", penalty);

        Penalty p = converter.penaltyDtoToEntity(penalty);
        p = penaltyRepo.saveAndFlush(p);

        if (p == null) {
            LOG.error("nepodarilo sa ulozit penalty do databazy");
            throw new AppException(HttpStatus.BAD_REQUEST, "nepodarilo sa ulozit penalty do databazy");
        }
        return converter.penaltyEntityToDto(p);

    }

    @Transactional
    public void deletePenalty(Integer id) {
        LOG.info("deletePenalty({id})", id);
        penaltyRepo.delete(id);
    }
    //////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////   PENALTY TYPE   ///////////////////////////////////

    @Transactional
    public PenaltyTypeDto addPenaltyType(PenaltyTypeDto pt) throws AppException {
        LOG.info("addPenaltyType({})", pt);
        if (pt.getName() == null) {
            LOG.error("nevplnene povinne parametre");
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplnene povinne parametre");
        }

        PenaltyType penalty = converter.penaltyTypeDtoToEntity(pt);
        penalty = penaltyTypeRepo.saveAndFlush(penalty);

        if (penalty == null) {
            LOG.error("nepodarilo sa ulozit penaltyType do databazy");
            throw new AppException(HttpStatus.BAD_REQUEST, "nepodarilo sa ulozit penaltyType do databazy");
        }
        return converter.penaltyTypeEntityToDto(penalty);
    }

    @Transactional
    public PenaltyTypeDto getOnePenaltyType(Integer id) throws AppException {
        LOG.info("getOnePenaltyType");
        PenaltyType p = penaltyTypeRepo.findOne(id);
        if (p == null) {
            LOG.error("nenajdena PenaltyType podla id {}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdena PenaltyType id: " + id);
        }
        return converter.penaltyTypeEntityToDto(p);
    }

    @Transactional
    public List<PenaltyTypeDto> getAllPenaltyType() {
        LOG.info("getAllPenaltyType");
        List<PenaltyTypeDto> l = new ArrayList<>();
        for (PenaltyType p : penaltyTypeRepo.findAll()) {
            l.add(converter.penaltyTypeEntityToDto(p));
        }
        return l;
    }

    @Transactional
    public PenaltyTypeDto editPenaltyType(PenaltyTypeDto edited) throws AppException {
        LOG.info("editPenaltyType({edited})", edited);
        PenaltyType penalty = converter.penaltyTypeDtoToEntity(edited);
        penalty = penaltyTypeRepo.saveAndFlush(penalty);

        if (penalty == null) {
            LOG.error("nepodarilo sa ulozit penaltyType do databazy");
            throw new AppException(HttpStatus.BAD_REQUEST, "nepodarilo sa ulozit penaltyType do databazy");
        }
        return converter.penaltyTypeEntityToDto(penalty);
    }

    @Transactional
    public void deletePenaltyType(Integer id) {
        LOG.info("deletePenaltyType({})", id);
        penaltyTypeRepo.delete(id);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// SEASON TOURNAMENT PENALTY TYPE /////////////////////////////////////
    @Transactional
    public SeasonTournamentPenaltyTypeDto addStPenaltyType(SeasonTournamentPenaltyTypeDto stPenalty) throws AppException {
        LOG.info("addStPenaltyType({})", stPenalty.toString());
        if (stPenalty.getIsPlayerDown() == null || stPenalty.getPenaltyDuration() == null || stPenalty.getPenaltyStatsDuration() == null
                || stPenalty.getPenaltyType() == null || stPenalty.getSeasonTournament() == null) {
            LOG.error("nevplnene povinne parametre");
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplnene povinne parametre");
        }

        SeasonTournamentPenaltyType stp = stPenaltyTypeRepo.saveAndFlush(converter.stPenaltyTypeDtoToEntity(stPenalty));
        if (stp == null) {
            LOG.error("nepodarilo sa ulozit stPenaltyType do databazy");
            throw new AppException(HttpStatus.BAD_REQUEST, "nepodarilo sa ulozit stPenaltyType do databazy");
        }
        return converter.stPenaltyTypeEntityToDto(stp);
    }

    @Transactional
    public List<SeasonTournamentPenaltyTypeDto> getAllStPenaltyType() {
        LOG.info("getAllStPenaltyType()");
        List<SeasonTournamentPenaltyTypeDto> l = new ArrayList<>();

        for (SeasonTournamentPenaltyType p : stPenaltyTypeRepo.findAll()) {
            l.add(converter.stPenaltyTypeEntityToDto(p));
        }
        return l;
    }

    @Transactional
    public SeasonTournamentPenaltyTypeDto getOneSeasonTournamentPenaltyType(Integer id) throws AppException {
        LOG.info("getOnePenaltyType");
        SeasonTournamentPenaltyType p = stPenaltyTypeRepo.findOne(id);
        if (p == null) {
            LOG.error("nenajdena PenaltyType podla id {}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdena PenaltyType id: " + id);
        }
        return converter.stPenaltyTypeEntityToDto(p);
    }

    @Transactional
    public List<SeasonTournamentPenaltyTypeDto> getAllStPenaltyTypeBySt(Integer stId) throws AppException {
        LOG.info("getAllStPenaltyType()");

        SeasonTournament st = stRepo.findOne(stId);
        if (st == null) {
            LOG.error("nenajdeny st podla id");
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny st  id: " + stId);
        }

        List<SeasonTournamentPenaltyTypeDto> l = new ArrayList<>();

        for (SeasonTournamentPenaltyType p : stPenaltyTypeRepo.findBySeasonTournament(st)) {
            l.add(converter.stPenaltyTypeEntityToDto(p));
        }
        return l;
    }

    @Transactional
    public SeasonTournamentPenaltyTypeDto editStPenaltyType(SeasonTournamentPenaltyTypeDto edited) throws AppException {
        LOG.info("editStPenaltyType", edited);

        SeasonTournamentPenaltyType stp = converter.stPenaltyTypeDtoToEntity(edited);
        stp = stPenaltyTypeRepo.saveAndFlush(stp);
        if (stp == null) {
            LOG.error("nepodarilo sa ulozit stPenaltyType do databazy");
            throw new AppException(HttpStatus.BAD_REQUEST, "nepodarilo sa ulozit stPenaltyType do databazy");
        }
        return converter.stPenaltyTypeEntityToDto(stp);
    }

    @Transactional
    public void deleteStPenaltyType(Integer id) {
        LOG.info("deleteStPenaltyType");
        stPenaltyTypeRepo.delete(id);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////// SEASON TOURNAMENT PENALTY SETTINGS ///////////////////////////////////////////////////

    @Transactional
    public SeasonTournamentPenaltySettingsDto addStPenaltySettings(SeasonTournamentPenaltySettingsDto request) throws AppException {
        LOG.info("addStPenaltySettings({})", request);

        if (request.getPenalty() == null || request.getSeasonTournamentId() == null || request.getSeasonTournamentPenaltyType() == null) {
            LOG.error("nevplnene povinne parametre");
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplnene povinne parametre");
        }

        SeasonTournamentPenaltySettings ps = stPenaltySettingsRepo.saveAndFlush(converter.stPenaltySettingsDtoToEntity(request));

        if (ps == null) {
            LOG.error("nepodarilo sa ulozit stPenaltySettings do databazy");
            throw new AppException(HttpStatus.BAD_REQUEST, "nepodarilo sa ulozit stPenaltySettings do databazy");
        }
        return converter.stPenaltySettingsEntityToDto(ps);
    }

    @Transactional
    public SeasonTournamentPenaltySettingsDto getOneSeasonTournamentPenaltySettings(Integer id) throws AppException {
        LOG.info("SeasonTournamentPenaltySettings({})", id);
        SeasonTournamentPenaltySettings p = stPenaltySettingsRepo.findOne(id);
        if (p == null) {
            LOG.error("nenajdena PenaltyType podla id {}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdena PenaltyType id: " + id);
        }
        return converter.stPenaltySettingsEntityToDto(p);
    }

    @Transactional
    public List<SeasonTournamentPenaltySettingsDto> getAllStPenaltySettings() {
        LOG.info("getAllStPenaltySettings()");
        List<SeasonTournamentPenaltySettingsDto> l = new ArrayList<>();

        for (SeasonTournamentPenaltySettings p : stPenaltySettingsRepo.findAll()) {
            l.add(converter.stPenaltySettingsEntityToDto(p));
        }
        return l;
    }

    @Transactional
    public List<SeasonTournamentPenaltySettingsDto> getAllStPenaltySettingsBySt(Integer stId) throws AppException {
        LOG.info("getAllStPenaltySettings()");
        List<SeasonTournamentPenaltySettingsDto> l = new ArrayList<>();

        SeasonTournament st = stRepo.findOne(stId);
        if (st == null) {
            LOG.error("nenajdeny st podla id");
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny st  id: " + stId);
        }
        
        for (SeasonTournamentPenaltySettings p : stPenaltySettingsRepo.findBySeasonTournament(st)) {
            l.add(converter.stPenaltySettingsEntityToDto(p));
        }
        return l;
    }

    @Transactional
    public SeasonTournamentPenaltySettingsDto editStPenaltySettings(SeasonTournamentPenaltySettingsDto edited) throws AppException {
        LOG.info("editStPenaltySettings({})", edited);

        SeasonTournamentPenaltySettings s = stPenaltySettingsRepo.saveAndFlush(converter.stPenaltySettingsDtoToEntity(edited));
        if (s == null) {
            LOG.error("nepodarilo sa ulozit stPenaltySettings do databazy");
            throw new AppException(HttpStatus.BAD_REQUEST, "nepodarilo sa ulozit stPenaltySettings do databazy");
        }
        return converter.stPenaltySettingsEntityToDto(s);
    }

    @Transactional
    public void deleteStPenaltySettings(Integer id) {
        LOG.info("deleteStPenaltySettings(Integer id)");

        stPenaltySettingsRepo.delete(id);
    }

    @Transactional
    public List<SeasonTournamentPenaltySettingsDto> addStSettingsBySt(List<SeasonTournamentPenaltySettingsDto> settings) {
        List<SeasonTournamentPenaltySettingsDto> l = new ArrayList<>();

        return l;

    }
}
