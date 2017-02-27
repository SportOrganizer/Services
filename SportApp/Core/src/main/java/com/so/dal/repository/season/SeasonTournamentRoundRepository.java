/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.repository.season;

import com.so.dal.model.Tournament;
import com.so.dal.model.season.SeasonTournament;
import com.so.dal.model.season.SeasonTournamentRound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author peter
 */
@Repository
public interface SeasonTournamentRoundRepository extends JpaRepository<SeasonTournamentRound, Integer>{
    public List<SeasonTournamentRound> findByNameContaining(String name);
    public SeasonTournamentRound findByName(String name);
}
