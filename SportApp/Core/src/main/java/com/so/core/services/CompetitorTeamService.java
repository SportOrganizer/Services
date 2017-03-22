/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services;

import com.so.dal.core.model.Resource;
import com.so.dal.core.model.Team;
import com.so.dal.core.model.game.CompetitorTeam;
import com.so.dal.core.model.season.SeasonTournamentGroup;
import com.so.dal.core.repository.game.CompetitorTeamRepository;
import java.security.InvalidParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

}


