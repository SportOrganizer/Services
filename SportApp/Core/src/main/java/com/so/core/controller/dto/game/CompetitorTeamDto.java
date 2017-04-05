/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.dto.game;

import com.so.core.controller.dto.ResourceDto;
import com.so.core.controller.dto.TeamDTO;
import java.util.ArrayList;
import java.util.List;
 
/**
 *
 * @author peter
 */
public class CompetitorTeamDto {
    
     private Integer id;
     private ResourceDto logo;
   //  private SeasonTournamentGroupDTO seasonTournamentGroup;
     private Integer groupId;
     private TeamDTO team;
   //  private Set<SportFlorbalGameActivity> sportFlorbalGameActivities = new HashSet<>(0);
   //  private Set<SportFlorbalGameShots> sportFlorbalGameShotses = new HashSet<>(0);
     private List<CompetitorTeamPlayerDto> competitorTeamPlayers = new ArrayList<>(0);
 //    private Set<Game> gamesForIdAwayTeam = new HashSet<>(0);
  //   private Set<Game> gamesForIdHomeTeam = new HashSet<>(0);

    public CompetitorTeamDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ResourceDto getLogo() {
        return logo;
    }

    public void setLogo(ResourceDto logo) {
        this.logo = logo;
    }

    public List<CompetitorTeamPlayerDto> getCompetitorTeamPlayers() {
        return competitorTeamPlayers;
    }

    public void setCompetitorTeamPlayers(List<CompetitorTeamPlayerDto> competitorTeamPlayers) {
        this.competitorTeamPlayers = competitorTeamPlayers;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

   

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

     
}
