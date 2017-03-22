/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.so.core.services;

import com.so.dal.core.model.Person;
import com.so.dal.core.model.Resource;
import com.so.dal.core.model.game.CompetitorTeam;
import com.so.dal.core.model.game.CompetitorTeamPlayer;
import com.so.dal.core.repository.game.CompetitorTeamPlayerRepository;
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
public class CompetitorTeamPlayerService {
    private final static Logger LOG = LoggerFactory.getLogger(PersonService.class);
    
    @Autowired
    CompetitorTeamPlayerRepository competitorTeamPlayerRepo;
    
    @Transactional
    public CompetitorTeamPlayer addCompetitorTeamPlayer(CompetitorTeam competitorTeam, Person person, Resource resource, int number, boolean isCapitan) {

        LOG.info("addTeam({},{},{},{},{})", competitorTeam, person, resource, number, isCapitan);

        if (competitorTeam == null || person == null || resource == null) {
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
}
