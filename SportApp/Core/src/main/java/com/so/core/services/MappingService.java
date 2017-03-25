/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.so.core.services;

import com.so.core.controller.dto.IncompatiblePlayersDTO;
import com.so.dal.core.model.Person;
import com.so.dal.core.model.Team;
import com.so.dal.core.model.game.CompetitorTeam;
import com.so.dal.core.model.registration.RegistrationPlayer;
import com.so.dal.core.model.registration.RegistrationTeam;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
import java.util.HashSet;
import java.util.Set;
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
    SeasonTournamentRepository seasonTournamentRepo;
    
    Set<IncompatiblePlayersDTO> incompatiblePersons;
    
    @Transactional
    public void MappingPlayers(Set<RegistrationPlayer> players, CompetitorTeam competitorTeam){
        LOG.info("MappingPlayers");
        

        for(RegistrationPlayer rp : players){
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
                    
                    incompatiblePersons.add(new IncompatiblePlayersDTO(rp, existedPerson));
                    
                }
            }
        }
        
    }
    
    //tato sa bude volat v controlleri
    @Transactional
    public Set<IncompatiblePlayersDTO> MappingTeamsAndPlayers(Integer idSeasonTournament){
        LOG.info("MappingTeams(idSeasonTournament) idSeasonTournament={}", idSeasonTournament);
        incompatiblePersons = new HashSet<>();
        
        SeasonTournament seasonTournament;     
        seasonTournament = seasonTournamentRepo.findOne(idSeasonTournament);
        
        for(RegistrationTeam rt : seasonTournament.getRegistrationTeams()){
            Team team = teamService.addTeam(rt.getResource(), rt.getName(), rt.getShortName(), rt.getColor());
            CompetitorTeam competitorTeam = competitorTeamService.addCompetitorTeam(team.getResource(), null, team);
            MappingPlayers(rt.getRegistrationPlayers(), competitorTeam);
            
        }
        return incompatiblePersons;
        
    }
    
}