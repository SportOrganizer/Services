/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services;

import com.so.core.controller.converter.RegistrationConverter;
import com.so.core.controller.dto.registration.RegistrationPlayerDto;
import com.so.core.controller.dto.registration.RegistrationTeamDto;
import com.so.core.services.document.DocumentService;
import com.so.dal.core.model.Resource;
import com.so.dal.core.model.registration.RegistrationPlayer;
import com.so.dal.core.model.registration.RegistrationTeam;
import com.so.dal.core.repository.registration.RegistrationPlayerRepository;
import com.so.dal.core.repository.registration.RegistrationTeamRepository;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public RegistrationTeamDto registrationTeam(RegistrationTeamDto teamDto) throws IOException {
        LOG.info("registrationTeam(teamDto)");

        if (teamDto.getColor() == null || teamDto.getName() == null || teamDto.getShortName() == null
                || teamDto.getZnak() == null || teamDto.getRegistrationPlayers() == null || teamDto.getSeasonTournamentId() == null) {
            LOG.error("nevyplneny povinny atribut color={} name={} shortName={} znak={} players={} seasonTournamentId= {}", teamDto.getColor(),
                    teamDto.getName(), teamDto.getShortName(), teamDto.getZnak(), teamDto.getRegistrationPlayers(), teamDto.getSeasonTournamentId());
            throw new InvalidParameterException("nevyplnene povinne parametre");
        }
        Resource znak = documentService.createFile(teamDto.getZnak().getData(), teamDto.getZnak().getMimeType(), "/opt/glassfish4/glassfish/domains/domain1/applications/resources/logos");
        teamDto.getZnak().setId(znak.getId());
        teamDto.setCreatedTime(new Date());

        RegistrationTeam team = converter.regTeamDtoToEntity(teamDto, false);
        RegistrationTeam savedTeam = regTeamRepo.saveAndFlush(team);

        if (savedTeam == null) {
            LOG.error(" do databazy sa nepodarilo ulozit team={} ", team);
            throw new IllegalStateException("do databazy sa neulozil novo vytvoreny team, koncim spracovanie");
        }

        for (RegistrationPlayerDto p : teamDto.getRegistrationPlayers()) {
            try {
                savedTeam.getRegistrationPlayers().add(registrationPlayer(p, savedTeam));
            } catch (IllegalStateException e) {
                //catch aby ak jeden hrac zakape dalsi sa ulozili .... ake najlepsie riesenie?
                // bud prejde vsetko alebo nic? alebo ulozime dobrych hracov??

            }
        }
        return converter.regTeamEntityToDto(savedTeam); // vraciam z dbs aby mal uz aj odkaz na playerov
    }

    @Transactional
    private RegistrationPlayer registrationPlayer(RegistrationPlayerDto playerDto, RegistrationTeam team) {
        LOG.info("registrationPlayer(playerDto, team)-> playerDto={} teamID={}", playerDto, team.getId());

        if (playerDto.getIsProfessional() == null || playerDto.getIsStudent() == null || playerDto.getName() == null
                || playerDto.getSurname() == null || playerDto.getNumber() == null || playerDto.getSex() == null) {
            LOG.error("nevyplnene povinne parametre isProfessional={} isStudent={} name={} surName={} Number={} sex={}",
                    playerDto.getIsProfessional(), playerDto.getIsStudent(), playerDto.getName(), playerDto.getSurname(),
                    playerDto.getNumber(), playerDto.getSex());
            throw new InvalidParameterException("nevyplnene povinne parametre");
        }

        RegistrationPlayer player = converter.regPlayerDtoToEntity(playerDto);
        player.setRegistrationTeam(team);
        RegistrationPlayer savedPlayer = regPlayerRepo.saveAndFlush(player);
        if (savedPlayer == null) {
            LOG.error(" do databazy sa nepodarilo ulozit hraca={} ", player);
            throw new IllegalStateException("do databazy sa neulozil novo vytvoreny hrac, koncim spracovanie");
        }
        return savedPlayer;
    }

    @Transactional
    public void deleteRegistrationTeam(Integer id) {

        RegistrationTeam team = regTeamRepo.findOne(id);

        if (team == null) {
            throw new InvalidParameterException("nenajdeny tim s id:" + id);
        }

        //explicitne mazanie playerov kvoli resourcosom
        for (RegistrationPlayer p : team.getRegistrationPlayers()) {
            deleteRegistrationPlayer(p.getId());
        }
        regTeamRepo.delete(id);

        if (team.getResource().getCompetitorTeams().isEmpty()) {
            documentService.deleteFile(team.getResource());
        }

    }

    @Transactional
    public void deleteRegistrationPlayer(Integer id) {

        RegistrationPlayer p = regPlayerRepo.findOne(id);

        if (p == null) {
            throw new InvalidParameterException("nenajdeny hrac s :" + id);
        }

        regPlayerRepo.delete(id);

        //TODO doplnit vymazenie resourcov ak sa doplni do db
        //   if(p.g)
    }

    @Transactional
    public RegistrationTeamDto editTeam(RegistrationTeamDto team) {

        RegistrationTeam entity = converter.regTeamDtoToEntity(team, false);
        RegistrationTeam savedTeam = regTeamRepo.save(entity);

        if (savedTeam == null) {
            LOG.error("nepodarilo sa ulozit aktualizovany tim do db timId={}", team.getId());
            throw new IllegalStateException("nepodarilo sa ulozit aktualizovany tim do db");
        }

        return converter.regTeamEntityToDto(savedTeam);
    }

    @Transactional
    public RegistrationPlayerDto editPlayer(RegistrationPlayerDto player) {

        RegistrationPlayer entity = converter.regPlayerDtoToEntity(player);
        RegistrationPlayer savedPlayer = regPlayerRepo.saveAndFlush(entity);

        if (savedPlayer == null) {
            LOG.error("nepodarilo sa ulozit aktualizovaneho hraca do db playerId={}", player.getId());
            throw new IllegalStateException("nepodarilo sa ulozit aktualizovaneho hraca do db");
        }

        return converter.regPlayerEntityToDto(savedPlayer);
    }
}
