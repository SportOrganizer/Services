/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.core.repository.season;

import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.model.season.SeasonTournamentLocation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author peter
 */
@Repository
public interface SeasonTournamentLocationRepository extends JpaRepository<SeasonTournamentLocation, Integer> {
    public List<SeasonTournamentLocation> findByNameContaining(String name);
    public SeasonTournamentLocation findByName(String name);
    
    public SeasonTournamentLocation findBySeasonTournament(SeasonTournament st);

}
