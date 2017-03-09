/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services;

import com.so.core.controller.dto.registration.RegistrationPlayerDto;
import com.so.core.controller.dto.registration.RegistrationTeamDto;
import com.so.core.services.document.DocumentService;
import com.so.dal.core.model.registration.RegistrationPlayer;
import com.so.dal.core.model.registration.RegistrationTeam;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.repository.registration.RegistrationPlayerRepository;
import com.so.dal.core.repository.registration.RegistrationTeamRepository;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
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
    RegistrationTeamRepository regTeamRepo;

    @Autowired
    RegistrationPlayerRepository regPlayerRepo;

    @Autowired
    SeasonTournamentRepository seasonTournamentRepo;

    @Autowired
    DocumentService documentService;

    @Transactional
    public RegistrationTeam registrationTeam(RegistrationTeamDto teamDto) throws IOException {
        LOG.info("registrationTeam(teamDto) teamDto={}", teamDto);

        if (teamDto.getColor() == null || teamDto.getName() == null || teamDto.getShortName() == null
                || teamDto.getZnak() == null || teamDto.getRegistrationPlayers() == null || teamDto.getSeasonTournamentId() == null) {
            LOG.error("nevyplneny povinny atribut color={} name={} shortName={} znak={} players={} seasonTournamentId= {}", teamDto.getColor(),
                    teamDto.getName(), teamDto.getShortName(), teamDto.getZnak(), teamDto.getRegistrationPlayers(), teamDto.getSeasonTournamentId());
            throw new InvalidParameterException("nevyplnene povinne parametre");
        }
        RegistrationTeam team = regTeamDtoToEntity(teamDto);

        RegistrationTeam savedTeam = regTeamRepo.saveAndFlush(team);

        if (savedTeam == null) {
            LOG.error(" do databayz sa nepodarilo ulozit team={} ", team);
            throw new IllegalStateException("do databazy sa neulozil novo vytvoreny team, koncim spracovanie");
        }

        for (RegistrationPlayerDto p : teamDto.getRegistrationPlayers()) {
            try {
                registrationPlayer(p, savedTeam);
            } catch (IllegalStateException e) {
            //catch aby ak jeden hrac zakape dalsi sa ulozili .... ake najlepsie riesenie?
                // bud prejde vsetko alebo nic? alebo ulozime dobrych hracov??
            }
        }
        return regTeamRepo.findOne(savedTeam.getId()); // vraciam z dbs aby mal uz aj odkaz na playerov
    }

    @Transactional
  private void registrationPlayer(RegistrationPlayerDto playerDto, RegistrationTeam team) {
        LOG.info("registrationPlayer(playerDto, team)-> playerDto={} teamID={}", playerDto, team.getId());

        if (playerDto.getIsProfessional() == null || playerDto.getIsStudent() == null || playerDto.getName() == null
                || playerDto.getSurname() == null || playerDto.getNumber() == null || playerDto.getSex() == null) {
            LOG.error("nevyplnene povinne parametre isProfessional={} isStudent={} name={} surName={} Number={} sex={}",
                    playerDto.getIsProfessional(), playerDto.getIsStudent(), playerDto.getName(), playerDto.getSurname(),
                    playerDto.getNumber(), playerDto.getSex());
            throw new InvalidParameterException("nevyplnene povinne parametre");
        }

        RegistrationPlayer player = regPlayerDtoToEntity(playerDto);
        player.setRegistrationTeam(team);
        RegistrationPlayer savedPlayer = regPlayerRepo.saveAndFlush(player);
        if (savedPlayer == null) {
            LOG.error(" do databazy sa nepodarilo ulozit hraca={} ", player);
            throw new IllegalStateException("do databazy sa neulozil novo vytvoreny hrac, koncim spracovanie");

        }
    }

    private RegistrationTeam regTeamDtoToEntity(RegistrationTeamDto dto) throws IOException {
        SeasonTournament st = seasonTournamentRepo.findOne(dto.getSeasonTournamentId());

        if (st == null) {
            LOG.error(" chyba v konvertore regTeam, nenajdeny seasonTournament s id={}  koncim spracovanie", dto.getSeasonTournamentId());
            throw new IllegalStateException("nenajdeny seasonTournament s id=" + dto.getSeasonTournamentId());

        }

        RegistrationTeam entity = new RegistrationTeam();
        entity.setColor(dto.getColor());
        entity.setCreatedTime(new Date()); // malo by vratit aktualny datum testnut ci naozaj
        entity.setIsCancelled(false);
        entity.setIsVerify(false);
        entity.setName(dto.getName());
        entity.setSeasonTournament(st);
        entity.setResource(documentService.createFile(dto.getZnak(), "jpeg", "photos", dto.getName()));
        entity.setShortName(dto.getShortName());

        return entity;
    }

    private RegistrationPlayer regPlayerDtoToEntity(RegistrationPlayerDto dto) {
        RegistrationPlayer entity = new RegistrationPlayer();

        entity.setBirthDate(dto.getBirthDate());
        entity.setIsProfessional(dto.getIsProfessional());
        entity.setIsStudent(dto.getIsStudent());
        entity.setIsVerified(false);
        entity.setMail(dto.getMail());
        entity.setName(dto.getName());
        entity.setNote(dto.getNote());
        entity.setNumber(dto.getNumber());
        entity.setPhone(dto.getPhone());
        //entity.setRegistrationTeam();
        entity.setSex(dto.getSex());
        entity.setSurname(dto.getSurname());

        return entity;
    }
}
