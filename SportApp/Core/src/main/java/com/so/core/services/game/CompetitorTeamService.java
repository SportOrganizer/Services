/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.game;

import com.so.core.controller.converter.game.CompetitorEntityConverter;
import com.so.core.controller.dto.game.CompetitorTeamDto;
import com.so.core.exception.AppException;
import com.so.core.services.PersonService;
import com.so.core.services.document.DocumentService;
import com.so.dal.core.model.Resource;
import com.so.dal.core.model.Team;
import com.so.dal.core.model.game.CompetitorTeam;
import com.so.dal.core.model.season.SeasonTournamentGroup;
import com.so.dal.core.repository.game.CompetitorTeamRepository;
import com.so.dal.core.repository.season.SeasonTournamentGroupRepository;
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
public class CompetitorTeamService {

    private final static Logger LOG = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    CompetitorTeamRepository competitorTeamRepo;

    @Autowired
    CompetitorEntityConverter competitorEntityConverter;

    @Autowired
    DocumentService documentService;

    @Transactional
    public CompetitorTeam addCompetitorTeam(Resource resource, SeasonTournamentGroup seasonTournamentGroup, Team team) {

        LOG.info("addTeam({},{},{})", resource, seasonTournamentGroup, team);

        if (resource == null || team == null) {
            LOG.error("resource team nesmu byt prazdne: resource={} team={}", resource, team);
            throw new InvalidParameterException("nevyplnene povinne parametre"); // zachytavat exceptiony v controllery alebo premysliet ako kde
        }

        //todo competitorTeamPlayers sa bude v buducnu doplnat vyhladavanim timu po poslani z napr. id z FE to sa odkomentuje este
        // pri tvorbe FE
        CompetitorTeam ct = new CompetitorTeam(resource, seasonTournamentGroup, team);

        ct = competitorTeamRepo.saveAndFlush(ct);

        if (ct == null) {
            LOG.info("Chyba pri pridavani timu {}", ct);
            throw new IllegalStateException();
        }
        LOG.info("Uspesne pridany CompetitorTeam {}", ct);
        return ct;
    }

    @Transactional
    public CompetitorTeamDto createCompetitorTeam(CompetitorTeamDto ct) throws AppException {
        LOG.info("createCompetitorTeam({})", ct.getId());

        CompetitorTeam c = competitorEntityConverter.competitorTeamDtoToEntity(ct);
        CompetitorTeam competitorTeam = competitorTeamRepo.saveAndFlush(c);

        if (competitorTeam == null) {
            LOG.error("competitorTeam sa neulozit do databazy: {}", ct.getId());
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "competitorTeam s ID:" + ct.getId() + " sa neulozit do databazy");
        }

        return competitorEntityConverter.competitorTeamEntityToDto(competitorTeam, true);

    }

    @Transactional
    public CompetitorTeamDto findByID(Integer id) throws AppException {
        LOG.info("findByID({})", id);
        if (id == null) {
            LOG.error("Nevyplneny povinny atribut: {}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplneny povinny atribut id=" + id);
        }

        CompetitorTeam c = competitorTeamRepo.findOne(id);

        if (c == null) {
            LOG.info("nenajdeny CompetitorTeam podla id=" + id);
            throw new AppException(HttpStatus.NOT_FOUND, "pre dany nazov neexistuje CompetitorTeam id=" + id);
        }

        return competitorEntityConverter.competitorTeamEntityToDto(c, true);
    }

    @Transactional
    public List<CompetitorTeamDto> findAll() throws AppException {
        LOG.info("findAll()");
        List<CompetitorTeamDto> l = new ArrayList<>();
        List<CompetitorTeam> lc = competitorTeamRepo.findAll();

        for (CompetitorTeam ct : lc) {
            l.add(competitorEntityConverter.competitorTeamEntityToDto(ct, false));
        }
        return l;
    }

    @Transactional
    public void deleteCompetitorTeam(Integer id) throws AppException {
        LOG.info("deleteSeasonTournament({})", id);
        CompetitorTeam c = competitorTeamRepo.findOne(id);

        if (c == null) {
            LOG.error("nenajdeny CompetitorTeam s id={}", id);
            throw new AppException(HttpStatus.BAD_REQUEST, "nenajdeny CompetitorTeam s id=" + id);
        }

        competitorTeamRepo.delete(c);

    }

    @Transactional
    public CompetitorTeamDto update(CompetitorTeamDto updated) throws AppException {
        LOG.info("update()");
        CompetitorTeam c = competitorEntityConverter.competitorTeamDtoToEntity(updated);

        if (updated.getLogo() != null) {
            if (updated.getLogo().getData() != null && updated.getLogo().getMimeType() != null) {
                Resource r = documentService.createFile(updated.getLogo().getData(), updated.getLogo().getMimeType());
                c.setResource(r);
            }
        }

        CompetitorTeam saved = competitorTeamRepo.saveAndFlush(c);

        if (saved == null) {
            LOG.error("nepodarilo sa ulozit st do db");
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "CompetitorTeam sa nepodarilo aktualizovat");
        }
        return competitorEntityConverter.competitorTeamEntityToDto(saved, true);
    }

}
