/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services;

import com.so.dal.core.model.Resource;
import com.so.dal.core.model.Team;
import com.so.dal.core.repository.TeamRepository;
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
public class TeamService {
    
    private final static Logger LOG = LoggerFactory.getLogger(PersonService.class);
    
    @Autowired
    TeamRepository teamRepo;
    

    @Transactional
    public Team addTeam(Resource resource, String name, String shortName, String color) {

        LOG.info("addTeam({},{},{},{})", resource, name, shortName, color);

        if (resource == null || name == null || shortName == null || color == null) {
            LOG.error("idLogo name shortName color nesmu byt prazdne: resource={} name={} shortName={} color={} ", resource, name, shortName, color);
            throw new InvalidParameterException("nevyplnene povinne parametre"); // zachytavat exceptiony v controllery alebo premysliet ako kde
        }

        //todo competitorTeamPlayers sa bude v buducnu doplnat vyhladavanim timu po poslani z napr. id z FE to sa odkomentuje este
        // pri tvorbe FE
        Team t = new Team(resource, name, shortName, color);

        t = teamRepo.saveAndFlush(t);

        if (t == null) {
            LOG.info("Chyba pri pridavani timu {}", t);
            throw new IllegalStateException();
        }
        LOG.info("Uspesne pridany tim {}", t);
        return t;
    }

}
