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
        validateRegistration(teamDto);
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

//        if (team.getResource().getCompetitorTeams().isEmpty()
//                && team.getResource().getRegistrationTeams().size() == 1) {
//            documentService.deleteFile(team.getResource());
//        }
    }

    @Transactional
    public void deleteRegistrationPlayer(Integer id) throws AppException {
        RegistrationPlayer p = regPlayerRepo.findOne(id);

        if (p == null) {
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdeny hrac s :" + id);
        }
        regPlayerRepo.delete(id);
//        if (p.getPhoto().getCompetitorTeamPlayers().isEmpty()
//                && p.getPhoto().getRegistrationPlayers().size() == 1) {
//            documentService.deleteFile(p.getPhoto());
//        }
    }

    @Transactional
    public RegistrationTeamDto editTeam(RegistrationTeamDto team) throws AppException {
        if (regTeamRepo.ifUniqeName(team.getSeasonTournamentId(), team.getId(), team.getName())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "meno tymu: " + team.getName() + " sa v tomto turnaji uz pouziva");
        }
        RegistrationTeam entity = converter.regTeamDtoToEntity(team, false);
        if (team.getZnak() != null) {
            if (team.getZnak().getData() != null && team.getZnak().getMimeType() != null) {
                Resource r = documentService.createFile(team.getZnak().getData(), team.getZnak().getMimeType());
                entity.setResource(r);
            }
        }
        RegistrationTeam savedTeam = regTeamRepo.save(entity);

        if (savedTeam == null) {
            LOG.error("nepodarilo sa ulozit aktualizovany tim do db timId={}", team.getId());
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "nepodarilo sa ulozit aktualizovany tim do db");
        }
        return converter.regTeamEntityToDto(savedTeam, false);
    }

    @Transactional
    public RegistrationPlayerDto editPlayer(RegistrationPlayerDto player) throws AppException {
        validatePlayerEdit(player);

        RegistrationPlayer entity = converter.regPlayerDtoToEntity(player);

        if (player.getPhoto() != null) {
            if (player.getPhoto().getData() != null && player.getPhoto().getMimeType() != null) {
                Resource r = documentService.createFile(player.getPhoto().getData(), player.getPhoto().getMimeType());
                entity.setPhoto(r);
            }
        }

        RegistrationPlayer savedPlayer = regPlayerRepo.saveAndFlush(entity);

        if (savedPlayer == null) {
            LOG.error("nepodarilo sa ulozit aktualizovaneho hraca do db playerId={}", player.getId());
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "nepodarilo sa ulozit aktualizovaneho hraca do db");
        }
        return converter.regPlayerEntityToDto(savedPlayer);
    }

    private void validateRegistration(RegistrationTeamDto teamDto) throws AppException {
        List<String> errorList = new ArrayList<>();
        Integer captain = 0;
        Set<String> errorEmail = new HashSet<>();
        Set<Integer> errorNumber = new HashSet<>();
        if (regTeamRepo.ifContainName(teamDto.getSeasonTournamentId(), teamDto.getName())) {
            errorList.add("nazov timu:" + teamDto.getName() + " sa uz v tomto turnaji pouziva");
        }
        for (RegistrationPlayerDto p : teamDto.getRegistrationPlayers()) {
            if (p.getIsCaptain()) {
                captain++;
            }
            if (!isValidEmail(p.getMail())) {
                errorList.add("email: " + p.getMail() + " nema spravny format");
            }

            if (!errorEmail.contains(p.getMail())) {
                errorEmail.add(p.getMail());
            } else {
                errorList.add("Email: " + p.getMail() + " je pouzity viac krat");
            }
            if (!errorNumber.contains(p.getNumber())) {
                errorNumber.add(p.getNumber());
            } else {
                errorList.add("cislo: " + p.getNumber() + "je pouzite viac krat");
            }
        }

        if (captain == 0) {
            errorList.add("ziadny hrac nie je oznaceny ako kapitan");
        } else if (captain > 1) {
            errorList.add("viac hracov je oznacenych ako kapitan");
        }

        if (!errorList.isEmpty()) {
            LOG.info("registracia nepresla validaciou {}", errorList.toString());
            throw new AppException(HttpStatus.BAD_REQUEST, errorList.toString());
        }
    }

    private void validatePlayerEdit(RegistrationPlayerDto p) throws AppException {
        List<String> errorList = new ArrayList<>();

        if (regPlayerRepo.ifUniqueCaptain(p.getRegistrationTeam(), p.getId(), p.getIsCaptain())) {
            errorList.add("hodnota ifCaptain nemoze byt zmenena, (tym by nemal ziadneho alebo dvoch kapitanov)");
        }
        if (regPlayerRepo.ifUniqueEmail(p.getRegistrationTeam(), p.getId(), p.getMail())) {
            errorList.add("email: " + p.getMail() + " pouziva iny hrac");
        }

        if (regPlayerRepo.ifUniqueNumber(p.getRegistrationTeam(), p.getId(), p.getNumber())) {
            errorList.add("cislo:" + p.getNumber() + " pouziva iny hrac");
        }
        if (!isValidEmail(p.getMail())) {
            errorList.add("email:" + p.getMail() + "nema spravny format");
        }
        if (!errorList.isEmpty()) {
            LOG.info("editacia hraca nepresla validaciou {}", errorList.toString());
            throw new AppException(HttpStatus.BAD_REQUEST, errorList.toString());
        }

    }

    public boolean isValidEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
