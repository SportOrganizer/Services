/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.so.core.services.game;

import com.so.core.controller.converter.game.CompetitorEntityConverter;
import com.so.core.controller.dto.game.CompetitorTeamDto;
import com.so.core.controller.dto.game.CompetitorTeamPlayerDto;
import com.so.core.exception.AppException;
import com.so.core.services.PersonService;
import com.so.core.services.document.DocumentService;
import com.so.dal.core.model.Person;
import com.so.dal.core.model.Resource;
import com.so.dal.core.model.game.CompetitorTeam;
import com.so.dal.core.model.game.CompetitorTeamPlayer;
import com.so.dal.core.repository.game.CompetitorTeamPlayerRepository;
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
 * @author Kristián Kačinetz
 */
@Service
public class CompetitorTeamPlayerService {
    private final static Logger LOG = LoggerFactory.getLogger(PersonService.class);
    
    @Autowired
    CompetitorTeamPlayerRepository competitorTeamPlayerRepo;
    
    @Autowired
    CompetitorEntityConverter competitorEntityConverter;

    @Autowired
    DocumentService documentService;
    
    @Transactional
    public CompetitorTeamPlayer addCompetitorTeamPlayer(CompetitorTeam competitorTeam, Person person, Resource resource, Integer number, Boolean isCapitan) {

        LOG.info("addTeam({},{},{},{},{})", competitorTeam, person, resource, number, isCapitan);

        if (competitorTeam == null || person == null || resource == null || number == null || isCapitan == null) {
            LOG.error("competitorTeam person resource number isCapitan nesmu byt prazdne: competitorTeam={} person={} resource={} number={} isCapitan={}", 
                    competitorTeam, person, resource, number, isCapitan);
            throw new InvalidParameterException("nevyplnene povinne parametre"); // zachytavat exceptiony v controllery alebo premysliet ako kde
        }

       
        CompetitorTeamPlayer ctp = new CompetitorTeamPlayer(competitorTeam, person, resource, number, isCapitan);

        ctp = competitorTeamPlayerRepo.saveAndFlush(ctp);

        if (ctp == null) {
            LOG.info("Chyba pri pridavani timu {}", ctp);
            throw new IllegalStateException();
        }
        LOG.info("Uspesne pridany CompetitorTeamPlayer {}", ctp);
        return ctp;
    }
    
    
    @Transactional
    public CompetitorTeamPlayerDto createCompetitorTeamPlayer(CompetitorTeamPlayerDto ct) throws AppException {
        LOG.info("createCompetitorTeam({})", ct.getId());

        CompetitorTeamPlayer c = competitorEntityConverter.competitorTeamPlayerDtoToEntity(ct);
        CompetitorTeamPlayer competitorTeamPlayer = competitorTeamPlayerRepo.saveAndFlush(c);

        if (competitorTeamPlayer == null) {
            LOG.error("CompetitorTeamPlayer sa neulozit do databazy: {}", ct.getId());
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "CompetitorTeamPlayer s ID:" + ct.getId() + " sa neulozit do databazy");
        }

        return competitorEntityConverter.competitorTeamPlayerEntityToDto(competitorTeamPlayer);

    }

    @Transactional
    public CompetitorTeamPlayerDto findByID(Integer id) throws AppException {
        LOG.info("findByID({})", id);
        if (id == null) {
            LOG.error("Nevyplneny povinny atribut: {}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplneny povinny atribut id=" + id);
        }

        CompetitorTeamPlayer c = competitorTeamPlayerRepo.findOne(id);

        if (c == null) {
            LOG.info("nenajdeny CompetitorTeamPlayer podla id=" + id);
            throw new AppException(HttpStatus.NOT_FOUND, "pre dany nazov neexistuje CompetitorTeamPlayer id=" + id);
        }

        return competitorEntityConverter.competitorTeamPlayerEntityToDto(c);
    }

    @Transactional
    public List<CompetitorTeamPlayerDto> findAll() throws AppException {
        LOG.info("findAll()");
        List<CompetitorTeamPlayerDto> l = new ArrayList<>();
        List<CompetitorTeamPlayer> lc = competitorTeamPlayerRepo.findAll();

        for (CompetitorTeamPlayer ctp : lc) {
            l.add(competitorEntityConverter.competitorTeamPlayerEntityToDto(ctp));
        }
        return l;
    }

    @Transactional
    public void deleteCompetitorTeamPlayer(Integer id) throws AppException {
        LOG.info("deleteSeasonTournament({})", id);
        CompetitorTeamPlayer c = competitorTeamPlayerRepo.findOne(id);

        if (c == null) {
            LOG.error("nenajdeny CompetitorTeamPlayer s id={}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny CompetitorTeamPlayer s id=" + id);
        }

        competitorTeamPlayerRepo.delete(c);

    }

    @Transactional
    public CompetitorTeamPlayerDto update(CompetitorTeamPlayerDto updated) throws AppException {
        LOG.info("update()");
        CompetitorTeamPlayer c = competitorEntityConverter.competitorTeamPlayerDtoToEntity(updated);

        if (updated.getPhoto() != null) {
            if (updated.getPhoto().getData() != null && updated.getPhoto().getMimeType() != null) {
                Resource r = documentService.createFile(updated.getPhoto().getData(), updated.getPhoto().getMimeType());
                c.setResource(r);
            }
        }

        CompetitorTeamPlayer saved = competitorTeamPlayerRepo.saveAndFlush(c);

        if (saved == null) {
            LOG.error("nepodarilo sa ulozit st do db");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "CompetitorTeamPlayer sa nepodarilo aktualizovat");
        }
        return competitorEntityConverter.competitorTeamPlayerEntityToDto(saved);
    }
    
}
