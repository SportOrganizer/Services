/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.core.repository.season;

import com.so.dal.core.model.Tournament;
import com.so.dal.core.model.season.Season;
import com.so.dal.core.model.season.SeasonTournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author peter
 */
@Repository
public interface SeasonTournamentRepository extends JpaRepository<SeasonTournament, Integer>{
    public List<SeasonTournament> findByNameContaining(String name);
    public SeasonTournament findByName(String name);

    public SeasonTournament findByTournament(Tournament t);
    public SeasonTournament findBySeason(Season s);

}
