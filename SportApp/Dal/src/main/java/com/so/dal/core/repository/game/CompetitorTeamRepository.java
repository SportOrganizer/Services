/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.core.repository.game;

import com.so.dal.core.model.game.CompetitorTeam;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.model.season.SeasonTournamentGroup;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author peter
 */
@Repository
public interface CompetitorTeamRepository extends JpaRepository<CompetitorTeam, Integer>{
    public List<CompetitorTeam> findBySeasonTournament(SeasonTournament st); 
    public List<CompetitorTeam> findBySeasonTournamentGroup(SeasonTournamentGroup group);
    public int countBySeasonTournamentGroup(SeasonTournamentGroup group); 
}
