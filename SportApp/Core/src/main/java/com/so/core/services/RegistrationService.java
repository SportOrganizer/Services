/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services;

import com.so.core.controller.converter.RegistrationConverter;
import com.so.core.controller.dto.registration.RegistrationPlayerDto;
import com.so.core.controller.dto.registration.RegistrationTeamDto;
import com.so.core.exception.AppException;
import com.so.core.services.document.DocumentService;
import com.so.dal.core.model.Resource;
import com.so.dal.core.model.registration.RegistrationPlayer;
import com.so.dal.core.model.registration.RegistrationTeam;
import com.so.dal.core.repository.registration.RegistrationPlayerRepository;
import com.so.dal.core.repository.registration.RegistrationTeamRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
public class RegistrationService {

    private final static Logger LOG = LoggerFactory.getLogger(RegistrationService.class);

    @Autowired
    private RegistrationTeamRepository regTeamRepo;

    @Autowired
    private RegistrationPlayerRepository regPlayerRepo;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private RegistrationConverter converter;

    @Transactional
    public List<RegistrationTeamDto> getAllTeams() throws AppException {
        LOG.info("getAllTeams()");
        List<RegistrationTeamDto> lDto = new ArrayList<>();

        List<RegistrationTeam> lEntity = regTeamRepo.findAll();
        for (RegistrationTeam t : lEntity) {
            lDto.add(converter.regTeamEntityToDto(t, false));
        }
        return lDto;
    }

    @Transactional
    public RegistrationTeamDto getTeam(Integer id) throws AppException {
        LOG.info("getTeam({})", id);

        if (id == null) {
            LOG.error("nevyplneny povinny atribut id={}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplneny povinny atribut id:" + id);
        }
        RegistrationTeam team = regTeamRepo.findOne(id);

        if (team == null) {
            LOG.info("nenajdeny ziadny tim podla id={}", id);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdeny tim s :" + id);
        }
        return converter.regTeamEntityToDto(team, true);
    }

    @Transactional
    public RegistrationPlayerDto getPlayer(Integer id) throws AppException {
        LOG.info("getPlayer({})", id);

        if (id == null) {
            LOG.error("nevyplneny povinny atribut id={}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplneny povinny atribut id:" + id);
        }

        RegistrationPlayer p = regPlayerRepo.findOne(id);
        if (p == null) {
            LOG.info("nenajdeny ziadny regPlayer pre id={}", id);
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdeny regPlayer s id:" + id);
        }
        return converter.regPlayerEntityToDto(p);
    }

    @Transactional
    public RegistrationTeamDto registrationTeam(RegistrationTeamDto teamDto) throws IOException, AppException {
        LOG.info("registrationTeam(teamDto)");

        if (teamDto.getColor() == null || teamDto.getName() == null || teamDto.getShortName() == null
                || teamDto.getZnak() == null || teamDto.getRegistrationPlayers() == null || teamDto.getSeasonTournamentId() == null) {
            LOG.error("nevyplneny povinny atribut color={} name={} shortName={} znak={} players={} seasonTournamentId= {}", teamDto.getColor(),
                    teamDto.getName(), teamDto.getShortName(), teamDto.getZnak(), teamDto.getRegistrationPlayers(), teamDto.getSeasonTournamentId());
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplnene povinne parametre");
        }
        Resource znak = documentService.createFile(teamDto.getZnak().getData(), teamDto.getZnak().getMimeType());
        teamDto.getZnak().setId(znak.getId());
        teamDto.setCreatedTime(new Date());

        RegistrationTeam team = converter.regTeamDtoToEntity(teamDto, false);
        RegistrationTeam savedTeam = regTeamRepo.saveAndFlush(team);

        if (savedTeam == null) {
            LOG.error(" do databazy sa nepodarilo ulozit team={} ", team);
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "do databazy sa neulozil novo vytvoreny team, koncim spracovanie");
        }
        for (RegistrationPlayerDto p : teamDto.getRegistrationPlayers()) {
            savedTeam.getRegistrationPlayers().add(registrationPlayer(p, savedTeam));
        }
        return converter.regTeamEntityToDto(savedTeam, true);
    }

    @Transactional
    private RegistrationPlayer registrationPlayer(RegistrationPlayerDto playerDto, RegistrationTeam team) throws IOException, AppException {
        LOG.info("registrationPlayer(playerDto, team)-> playerDto={} teamID={}", playerDto, team.getId());

        if (playerDto.getIsProfessional() == null || playerDto.getIsStudent() == null || playerDto.getName() == null
                || playerDto.getSurname() == null || playerDto.getNumber() == null || playerDto.getSex() == null) {
            LOG.error("nevyplnene povinne parametre isProfessional={} isStudent={} name={} surName={} Number={} sex={}",
                    playerDto.getIsProfessional(), playerDto.getIsStudent(), playerDto.getName(), playerDto.getSurname(),
                    playerDto.getNumber(), playerDto.getSex());
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplnene povinne parametre");
        }
        Resource r = null;

        if (playerDto.getPhoto() != null) {
            r = documentService.createFile(playerDto.getPhoto().getData(), playerDto.getPhoto().getMimeType());
        }
        RegistrationPlayer player = converter.regPlayerDtoToEntity(playerDto);

        player.setPhoto(r);
        player.setRegistrationTeam(team);
        RegistrationPlayer savedPlayer = regPlayerRepo.saveAndFlush(player);
        if (savedPlayer == null) {
            LOG.error(" do databazy sa nepodarilo ulozit hraca={} ", player);
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "do databazy sa neulozil novo vytvoreny hrac, koncim spracovanie");
        }
        return savedPlayer;
    }

    @Transactional
    public void deleteRegistrationTeam(Integer id) throws AppException {
        RegistrationTeam team = regTeamRepo.findOne(id);

        if (team == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny tim s id:" + id);
        }

        //explicitne mazanie playerov kvoli resourcosom
        for (RegistrationPlayer p : team.getRegistrationPlayers()) {
            deleteRegistrationPlayer(p.getId());
        }
        regTeamRepo.delete(id);

        if (team.getResource().getCompetitorTeams().isEmpty()
                && team.getResource().getRegistrationTeams().size() == 1) {
            documentService.deleteFile(team.getResource());
        }
    }

    @Transactional
    public void deleteRegistrationPlayer(Integer id) throws AppException {
        RegistrationPlayer p = regPlayerRepo.findOne(id);

        if (p == null) {
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdeny hrac s :" + id);
        }
        regPlayerRepo.delete(id);
        if (p.getPhoto().getCompetitorTeamPlayers().isEmpty()
                && p.getPhoto().getRegistrationPlayers().size() == 1) {
            documentService.deleteFile(p.getPhoto());
        }
    }

    @Transactional
    public RegistrationTeamDto editTeam(RegistrationTeamDto team) throws AppException {

        RegistrationTeam entity = converter.regTeamDtoToEntity(team, false);
        RegistrationTeam savedTeam = regTeamRepo.save(entity);

        if (savedTeam == null) {
            LOG.error("nepodarilo sa ulozit aktualizovany tim do db timId={}", team.getId());
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "nepodarilo sa ulozit aktualizovany tim do db");
        }
        return converter.regTeamEntityToDto(savedTeam, false);
    }

    @Transactional
    public RegistrationPlayerDto editPlayer(RegistrationPlayerDto player) throws AppException {

        RegistrationPlayer entity = converter.regPlayerDtoToEntity(player);
        RegistrationPlayer savedPlayer = regPlayerRepo.saveAndFlush(entity);

        if (savedPlayer == null) {
            LOG.error("nepodarilo sa ulozit aktualizovaneho hraca do db playerId={}", player.getId());
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "nepodarilo sa ulozit aktualizovaneho hraca do db");
        }
        return converter.regPlayerEntityToDto(savedPlayer);
    }
}
