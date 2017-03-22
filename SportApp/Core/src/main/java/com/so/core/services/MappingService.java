/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.so.core.services;

import com.so.core.controller.dto.IncompatiblePlayers;
import com.so.core.services.season.SeasonTournamentService;
import com.so.dal.core.model.Person;
import com.so.dal.core.model.Team;
import com.so.dal.core.model.game.CompetitorTeam;
import com.so.dal.core.model.registration.RegistrationPlayer;
import com.so.dal.core.model.registration.RegistrationTeam;
import com.so.dal.core.model.season.SeasonTournament;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kristián Kačinetz
 */
@Service
public class MappingService {
    
    private final static Logger LOG = LoggerFactory.getLogger(RegistrationService.class);
    
    @Autowired
    TeamService teamService;
    
    @Autowired
    CompetitorTeamService competitorTeamService;
    
    @Autowired
    CompetitorTeamPlayerService competitorTeamPlayerService;
    
    @Autowired
    PersonService personService;
    
    @Autowired
    SeasonTournamentService seasonTournamentService;
    
    Set<RegistrationTeam> registrationTeams;
    Set<RegistrationPlayer> registrationPlayers; 
    Set<IncompatiblePlayers> incompatiblePersons;
    
    
    public void MappingPlayers(Integer idSeasonTournament, CompetitorTeam competitorTeam){
        LOG.info("MappingPlayers(idSeasonTournament) idSeasonTournament={}", idSeasonTournament);
        
        SeasonTournament seasonTournament;     
        seasonTournament = seasonTournamentService.findById(idSeasonTournament);
        
        registrationTeams.addAll(seasonTournament.getRegistrationTeams());
        for(RegistrationTeam t : registrationTeams){
            registrationPlayers.addAll(t.getRegistrationPlayers());
        }
        
        for(RegistrationPlayer rp : registrationPlayers){
            //ak neexistuje taky mail, tak pridaj personu
            if(personService.findPersonByEmail(rp.getMail()) == null){
                Person person = personService.addPerson(rp.getName(), rp.getSurname(), rp.getBirthDate(), rp.getMail(), rp.getPhone(), rp.isIsStudent(),
                        rp.getSex());
                rp.setIsVerified(true);
                //opravit null pre Resource ked bude opravena databaza a opravit aj posledny parameter isCapitan na hodnotu
                competitorTeamPlayerService.addCompetitorTeamPlayer(competitorTeam, person, null, rp.getNumber(), true);
                               
            }
            else{
                Person existedPerson = personService.findPersonByEmail(rp.getMail());
                if(!(rp.getBirthDate().equals(existedPerson.getBirthDate())) || !(rp.getName().equals(existedPerson.getName())) || 
                        !(rp.getPhone().equals(existedPerson.getPhone())) || !(rp.getSurname().equals(existedPerson.getSurname())) || 
                        !(rp.getSex().equals(existedPerson.getSex())) || !(rp.isIsStudent().equals(existedPerson.isIsStudent()))){
                    //vrat obidve objekty? 
                    
                    incompatiblePersons.add(new IncompatiblePlayers(rp, existedPerson));
                    
                }
            }
        }
        
    }
    
    //tato sa bude volat v controlleri
    public Set<IncompatiblePlayers> MappingTeamsAndPlayers(Integer idSeasonTournament){
        LOG.info("MappingTeams(idSeasonTournament) idSeasonTournament={}", idSeasonTournament);
        
        SeasonTournament seasonTournament;     
        seasonTournament = seasonTournamentService.findById(idSeasonTournament);
        
        registrationTeams.addAll(seasonTournament.getRegistrationTeams());
        for(RegistrationTeam rt : registrationTeams){
            Team team = teamService.addTeam(rt.getResource(), rt.getName(), rt.getShortName(), rt.getColor());
            CompetitorTeam competitorTeam = competitorTeamService.addCompetitorTeam(team.getResource(), null, team);
            MappingPlayers(idSeasonTournament, competitorTeam);
            
        }
        return incompatiblePersons;
        
    }
    
}
