/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services;

import com.so.core.controller.converter.PersonConverter;
import com.so.core.controller.converter.RegistrationConverter;
import com.so.core.controller.dto.IncompatiblePlayersDTO;
import com.so.core.controller.dto.PersonDTO;
import com.so.core.controller.dto.registration.RegistrationPlayerDto;
import com.so.core.exception.AppException;
import com.so.dal.core.model.Person;
import com.so.dal.core.model.Team;
import com.so.dal.core.model.game.CompetitorTeam;
import com.so.dal.core.model.registration.RegistrationPlayer;
import com.so.dal.core.model.registration.RegistrationTeam;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.repository.PersonRepository;
import com.so.dal.core.repository.ResourceRepository;
import com.so.dal.core.repository.TeamRepository;
import com.so.dal.core.repository.game.CompetitorTeamRepository;
import com.so.dal.core.repository.registration.RegistrationPlayerRepository;
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

    @Autowired
    RegistrationConverter registratioConverter;

    @Autowired
    PersonConverter personConverter;

    @Autowired
    TeamRepository teamRepo;

    @Autowired
    PersonRepository personRepo;
    
    @Autowired
    ResourceRepository resourceRepo;
    
    @Autowired
    CompetitorTeamRepository competitorTeamRepo;
    
    @Autowired
    RegistrationPlayerRepository registrationPlayerRepo;

    Set<IncompatiblePlayersDTO> incompatiblePersons;

    @Transactional
    public void MappingPlayers(Set<RegistrationPlayer> players, CompetitorTeam competitorTeam) throws AppException {
        LOG.info("MappingPlayers");

        for (RegistrationPlayer rp : players) {
            //ak neexistuje taky mail, tak pridaj personu
            if (personRepo.findByMail(rp.getMail()) == null) {
                Person person = personService.addPerson2(rp.getName(), rp.getSurname(), rp.getBirthDate(), rp.getMail(), rp.getPhone(), rp.isIsStudent(),
                        rp.getSex());
                
                competitorTeamPlayerService.addCompetitorTeamPlayer(competitorTeam, person, rp.getPhoto(), rp.getNumber(), rp.getIsCaptain());
                rp.setIsVerified(true);

            } else {
                Person existedPerson = personRepo.findByMail(rp.getMail());
                if (!(rp.getBirthDate().equals(existedPerson.getBirthDate())) || !(rp.getName().equals(existedPerson.getName()))
                        || !(rp.getPhone().equals(existedPerson.getPhone())) || !(rp.getSurname().equals(existedPerson.getSurname()))
                        || !(rp.getSex().equals(existedPerson.getSex())) || !(rp.isIsStudent().equals(existedPerson.isIsStudent()))) {
                    //vrat obidve objekty? 

                    incompatiblePersons.add(new IncompatiblePlayersDTO(registratioConverter.regPlayerEntityToDto(rp), personConverter.personEntityToDto(existedPerson), competitorTeam.getId()));

                } else {                    
                    competitorTeamPlayerService.addCompetitorTeamPlayer(competitorTeam, personRepo.findByMail(rp.getMail()), rp.getPhoto(), rp.getNumber(), rp.getIsCaptain());
                    rp.setIsVerified(true);
                }
            }
        }

    }

    //tato sa bude volat v controlleri
    @Transactional
    public Set<IncompatiblePlayersDTO> MappingTeamsAndPlayers(Integer idSeasonTournament) throws AppException {
        LOG.info("MappingTeams(idSeasonTournament) idSeasonTournament={}", idSeasonTournament);
        incompatiblePersons = new HashSet<>();

        SeasonTournament seasonTournament;
        seasonTournament = seasonTournamentRepo.findOne(idSeasonTournament);

        for (RegistrationTeam rt : seasonTournament.getRegistrationTeams()) {
            Team team = teamRepo.findByName(rt.getName());
            if (team == null) {
                team = teamService.addTeam2(rt.getName(), rt.getShortName(), rt.getColor());
            }
            CompetitorTeam competitorTeam = competitorTeamService.addCompetitorTeam(rt.getResource(), null, team);
            MappingPlayers(rt.getRegistrationPlayers(), competitorTeam);

        }
        return incompatiblePersons;

    }

    @Transactional
    public PersonDTO ConfirmIncompatiblePlayers(RegistrationPlayerDto ip, Integer idCT) throws AppException {
        Person p = personRepo.findByMail(ip.getMail());
        if(p == null){
            p = personService.addPerson2(ip.getName(), ip.getSurname(), ip.getBirthDate(), ip.getMail(), ip.getPhone(), ip.getIsStudent(),ip.getSex());
        }
        else{
            p = new Person(ip.getName(), ip.getSurname(), ip.getBirthDate(), ip.getMail(), ip.getPhone(), ip.getIsStudent(), ip.getSex(), personRepo.findByMail(ip.getMail()).getCompetitorTeamPlayers());
            p.setId(personRepo.findByMail(ip.getMail()).getId());
        }
        PersonDTO pDTO = personService.update(personConverter.personEntityToDto(p));
        competitorTeamPlayerService.addCompetitorTeamPlayer(competitorTeamRepo.findOne(idCT), personConverter.dtoToEntity(pDTO), resourceRepo.findOne(ip.getPhoto().getId()), ip.getNumber(), ip.getIsCaptain());
        registrationPlayerRepo.findOne(ip.getId()).setIsVerified(true);
        return pDTO;
        
    }

}
