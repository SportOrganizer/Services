/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.core.repository.season;

import com.so.dal.core.model.game.Penalty;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.model.season.SeasonTournamentPenaltySettings;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author peter
 */
@Repository
public interface SeasonTournamentPenaltySettingsRepository extends JpaRepository<SeasonTournamentPenaltySettings, Integer> {
    public List<SeasonTournamentPenaltySettings> findBySeasonTournament(SeasonTournament st);
    public SeasonTournamentPenaltySettings findByPenalty(Penalty p);
}
