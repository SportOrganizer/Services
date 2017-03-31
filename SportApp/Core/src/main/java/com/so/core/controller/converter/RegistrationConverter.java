/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.converter;

import com.so.core.controller.dto.ResourceDto;
import com.so.core.controller.dto.registration.RegistrationPlayerDto;
import com.so.core.controller.dto.registration.RegistrationTeamDto;
import com.so.core.exception.AppException;
import com.so.dal.core.model.Resource;
import com.so.dal.core.model.registration.RegistrationPlayer;
import com.so.dal.core.model.registration.RegistrationTeam;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.repository.ResourceRepository;
import com.so.dal.core.repository.registration.RegistrationPlayerRepository;
import com.so.dal.core.repository.registration.RegistrationTeamRepository;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author peter
 */
@Service
public class RegistrationConverter {

    private final static Logger LOG = LoggerFactory.getLogger(RegistrationConverter.class);

    @Autowired
    private RegistrationTeamRepository regTeamRepo;

    @Autowired
    private RegistrationPlayerRepository regPlayerRepo;

    @Autowired
    private SeasonTournamentRepository seasonTournamentRepo;

    @Autowired
    private ResourceRepository resourceRepo;

    public RegistrationPlayer regPlayerDtoToEntity(RegistrationPlayerDto dto) throws AppException {
        RegistrationPlayer entity;

        if (dto.getId() != null) {
            entity = regPlayerRepo.findOne(dto.getId());
        } else {
            entity = new RegistrationPlayer();
        }
        if (dto.getRegistrationTeam() != null) {
            RegistrationTeam t = regTeamRepo.findOne(dto.getRegistrationTeam());
            if (t == null) {
                LOG.error("neexistuje tim s id={}", dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje tim s id=" + dto.getId());
            } else {
                entity.setRegistrationTeam(t);
            }
        }
        if (dto.getPhoto() != null) {
            if (dto.getPhoto().getId() != null) {
                Resource r = resourceRepo.findOne(entity.getPhoto().getId());
                if (r == null) {
                    LOG.error("neexistuje resource s id={}", dto.getId());
                    throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje resource s id=" + dto.getId());
                } else {
                    entity.setPhoto(r);
                }
            }
        }

        entity.setBirthDate(dto.getBirthDate());
        entity.setIsProfessional(dto.getIsProfessional());
        entity.setIsStudent(dto.getIsStudent());
        entity.setIsVerified(dto.getIsVerified());
        entity.setMail(dto.getMail());
        entity.setName(dto.getName());
        entity.setNote(dto.getNote());
        entity.setNumber(dto.getNumber());
        entity.setPhone(dto.getPhone());
        entity.setSex(dto.getSex());
        entity.setSurname(dto.getSurname());
        entity.setIsCaptain(dto.getIsCaptain());

        return entity;
    }

    public RegistrationTeam regTeamDtoToEntity(RegistrationTeamDto dto, Boolean ifCopyPlayers) throws AppException {

        RegistrationTeam entity;

        if (dto.getId() != null) {
            entity = regTeamRepo.findOne(dto.getId());
        } else {
            entity = new RegistrationTeam();
        }

        SeasonTournament st = seasonTournamentRepo.findOne(dto.getSeasonTournamentId());
        if (st == null) {
            LOG.error(" chyba v konvertore regTeam, nenajdeny seasonTournament s id={}  koncim spracovanie", dto.getSeasonTournamentId());
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny seasonTournament s id=" + dto.getSeasonTournamentId());
        }
        if (dto.getZnak().getId() != null) {
            Resource r = resourceRepo.findOne(dto.getZnak().getId());
            if (r == null) {
                LOG.error("neexistuje resource s id={}", dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "neexistuje resource s id=" + dto.getId());
            } else {
                entity.setResource(r);
            }
        }
        entity.setColor(dto.getColor());
        entity.setCreatedTime(dto.getCreatedTime());
        entity.setIsCancelled(dto.getIsCancelled());
        entity.setIsVerify(dto.getIsVerify());
        entity.setName(dto.getName());
        entity.setSeasonTournament(st);
        entity.setShortName(dto.getShortName());

        if (ifCopyPlayers) {
            Set<RegistrationPlayer> set = new HashSet<>();
            for (RegistrationPlayerDto player : dto.getRegistrationPlayers()) {
                set.add(regPlayerDtoToEntity(player));
            }
            entity.setRegistrationPlayers(set);
        }
        return entity;
    }

    public RegistrationPlayerDto regPlayerEntityToDto(RegistrationPlayer entity) {
        RegistrationPlayerDto dto = new RegistrationPlayerDto();

        dto.setBirthDate(entity.getBirthDate());
        dto.setId(entity.getId());
        dto.setIsProfessional(entity.isIsProfessional());
        dto.setIsVerified(entity.isIsVerified());
        dto.setIsStudent(entity.isIsStudent());
        dto.setMail(entity.getMail());
        dto.setName(entity.getName());
        dto.setNote(entity.getNote());
        dto.setNumber(entity.getNumber());
        dto.setPhone(entity.getPhone());
        dto.setRegistrationTeam(entity.getRegistrationTeam().getId());
        dto.setSex(entity.getSex());
        dto.setSurname(entity.getSurname());
        dto.setIsCaptain(entity.getIsCaptain());

        if (entity.getPhoto() != null) {
            dto.setPhoto(new ResourceDto(entity.getPhoto().getId(), entity.getPhoto().getPath()));
        }
        return dto;
    }

    public RegistrationTeamDto regTeamEntityToDto(RegistrationTeam entity, Boolean ifCopyPlayer) {
        RegistrationTeamDto dto = new RegistrationTeamDto();

        dto.setColor(entity.getColor());
        dto.setCreatedTime(entity.getCreatedTime());
        dto.setId(entity.getId());
        dto.setIsCancelled(entity.isIsCancelled());
        dto.setIsVerify(entity.isIsVerify());
        dto.setName(entity.getName());
        dto.setSeasonTournamentId(entity.getSeasonTournament().getId());
        dto.setShortName(entity.getShortName());
        dto.setZnak(new ResourceDto(entity.getResource().getId(), entity.getResource().getPath()));

        if (ifCopyPlayer) {
            dto.setRegistrationPlayers(new HashSet<RegistrationPlayerDto>());
            for (RegistrationPlayer player : entity.getRegistrationPlayers()) {
                dto.getRegistrationPlayers().add(regPlayerEntityToDto(player));
            }
        }
        return dto;
    }
}
