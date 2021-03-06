/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.core.repository.season;


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
public interface SeasonTournamentGroupRepository extends JpaRepository<SeasonTournamentGroup, Integer>{
    public List<SeasonTournamentGroup> findByNameContaining(String name);
    public SeasonTournamentGroup findByName(String name);
    public List<SeasonTournamentGroup> findBySeasonTournament(SeasonTournament seasonTournament); 
}
