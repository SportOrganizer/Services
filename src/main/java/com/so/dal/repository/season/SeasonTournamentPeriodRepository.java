/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.repository.season;

import com.so.dal.model.season.SeasonTournamentPenalty;
import com.so.dal.model.season.SeasonTournamentPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonTournamentPeriodRepository  extends JpaRepository<SeasonTournamentPeriod, Integer>{
    public List<SeasonTournamentPeriod> findByNameContaining(String name);
    public SeasonTournamentPeriod findByName(String name);
}
