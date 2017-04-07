/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services;

import com.so.core.controller.converter.TeamConverter;
import com.so.core.controller.dto.TeamDTO;
import com.so.core.exception.AppException;
import com.so.dal.core.model.Team;
import com.so.dal.core.repository.TeamRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kristián Kačinetz
 */
@Service
public class TeamService {

    private final static Logger LOG = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private TeamConverter teamConverter;

    @Transactional
    public TeamDTO addTeam(String name, String shortName, String color) throws AppException {

        LOG.info("addTeam({},{},{})", name, shortName, color);

        if (name == null || shortName == null || color == null) {
            LOG.error("idLogo name shortName color nesmu byt prazdne: name={} shortName={} color={} ", name, shortName, color);
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplnene povinne parametre"); // zachytavat exceptiony v controllery alebo premysliet ako kde
        }

        Team t = new Team(name, shortName, color);

        t = teamRepo.saveAndFlush(t);

        if (t == null) {
            LOG.error("Chyba pri pridavani timu {}", t);
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "chyba pri ukladani timu");
        }
        LOG.info("Uspesne pridany tim {}", t);
        return teamConverter.teamEntityToDto(t);
    }

    @Transactional
    public TeamDTO createTeam(TeamDTO tDto) throws AppException {
        LOG.info("createTeam({})", tDto.getId());
        
        if (tDto.getName() == null || tDto.getShortName() == null || tDto.getColor() == null) {
            LOG.error("parameter name, shortName, color nemoze byt null: {}, {}, {}", tDto.getName(), tDto.getShortName(), tDto.getColor());
            throw new AppException(HttpStatus.BAD_REQUEST, "nie je zadany povinny parameter");
        }

//        if (teamRepo.findByName(tDto.getName()) != null) {
//            LOG.error("duplicitny name: {}", tDto.getName());
//            throw new AppException(HttpStatus.CONFLICT, "dany name teamu sa uz pouziva; name=" + tDto.getName());
//        }

        Team t = teamConverter.teamDtoToEntity(tDto);
        t = teamRepo.saveAndFlush(t);

        if (t == null) {
            LOG.error("Team sa neulozil do databazy: {}", tDto.getId());
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "Team s ID:" + tDto.getId() + " sa neulozil do databazy");
        }

        return teamConverter.teamEntityToDto(t);

    }

    @Transactional
    public Team addTeam2(String name, String shortName, String color) throws AppException {

        LOG.info("addTeam({},{},{})", name, shortName, color);

        if (name == null || shortName == null || color == null) {
            LOG.error("idLogo name shortName color nesmu byt prazdne: name={} shortName={} color={} ", name, shortName, color);
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplnene povinne parametre"); // zachytavat exceptiony v controllery alebo premysliet ako kde
        }

        Team t = new Team(name, shortName, color);

        t = teamRepo.saveAndFlush(t);

        if (t == null) {
            LOG.error("Chyba pri pridavani timu {}", t);
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "chyba pri ukladani timu");
        }
        LOG.info("Uspesne pridany tim {}", t);
        return t;
    }

    @Transactional
    public TeamDTO findById(Integer id) throws AppException {
        LOG.info("Team findById({})", id);
        if (id == null) {
            LOG.error("id nemoze byt null: {}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "parameter id je null");
        }

        Team t = teamRepo.findOne(id);
        if (t == null) {
            LOG.error("k danemu id neexistuje zaznam v dbs");
            throw new AppException(HttpStatus.NOT_FOUND, "pre id:" + id + " neexistuje team v db");
        }

        return teamConverter.teamEntityToDto(t);
    }

    @Transactional
    public Set<TeamDTO> findAll() {
        LOG.info("Team findByAll()");
        List<Team> lt = teamRepo.findAll();
        Set<TeamDTO> teamList = new HashSet<>();
        for (Team t : lt) {
            teamList.add(teamConverter.teamEntityToDto(t));
        }
        return teamList;
    }
    
    @Transactional
    public void deleteTeam(Integer id) throws AppException {
        LOG.info("deleteTeam({})", id);
        Team t = teamRepo.findOne(id);

        if (t == null) {
            LOG.error("nenajdeny team na vymazanie id={}", id);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdeny team id=" + id);
        }
        teamRepo.delete(t);
    }

    @Transactional
    public TeamDTO update(TeamDTO updated) throws AppException {
        LOG.info("update()");

        if (updated == null) {
            LOG.error("aktualizovany objekt je null");
            throw new AppException(HttpStatus.BAD_REQUEST, "zadany objekt je nespravne vyplneny");
        }
        Team t = teamConverter.teamDtoToEntity(updated);
        Team saved = teamRepo.saveAndFlush(t);

        if (saved == null) {
            LOG.error("nepodarilo sa ulozit team do db");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "nepodarilo sa ulozit team do db");
        }
        return teamConverter.teamEntityToDto(saved);
    }

}
