/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.dto.game;

import com.so.dal.core.model.game.CompetitorTeamPlayer;
import com.so.dal.core.model.game.Game;
import com.so.dal.floorball.model.game.SportFlorbalGameActivity;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author peter
 */
public class GamePlayerDto {
    
     private Integer id;
     private Integer competitorTeamPlayerId;
     private Integer gameId;
    // private Set<SportFlorbalGameActivity> sportFlorbalGameActivitiesForIdAssistPlayer = new HashSet<>(0);
    // private Set<SportFlorbalGameActivity> sportFlorbalGameActivitiesForIdGoalPlayer = new HashSet<>(0);
    // private Set<SportFlorbalGameActivity> sportFlorbalGameActivitiesForIdAssist2player = new HashSet<>(0);
    // private Set<SportFlorbalGameActivity> sportFlorbalGameActivitiesForIdPenaltyPlayer = new HashSet<>(0);

    public GamePlayerDto() {
    }

    public GamePlayerDto(Integer id, Integer competitorTeamPlayerId, Integer gameId) {
        this.id = id;
        this.competitorTeamPlayerId = competitorTeamPlayerId;
        this.gameId = gameId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompetitorTeamPlayerId() {
        return competitorTeamPlayerId;
    }

    public void setCompetitorTeamPlayerId(Integer competitorTeamPlayerId) {
        this.competitorTeamPlayerId = competitorTeamPlayerId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
    
     
}
