/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.dto.season;

import com.so.dal.core.model.season.SeasonTournamentGroup;

/**
 *
 * @author Tomas
 */
public class SeasonTournamentGroupDTO {
     private Integer id;
     private Integer seasonTournamentId;
     private String name;
     
     public SeasonTournamentGroupDTO(){
     }

    public SeasonTournamentGroupDTO(Integer seasonTournament, String name) {
        this.seasonTournamentId = seasonTournament;
        this.name = name;
    }
    
    public SeasonTournamentGroupDTO(String seasonTournament, String name) {
        this.seasonTournamentId = Integer.parseInt(seasonTournament);
        this.name = name;
    }
     
    public SeasonTournamentGroupDTO(SeasonTournamentGroup stg){
        this.id = stg.getId();
        this.seasonTournamentId = stg.getSeasonTournament().getId();
        this.name = stg.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeasonTournamentId() {
        return seasonTournamentId;
    }

    public void setSeasonTournamentId(Integer seasonTournament) {
        this.seasonTournamentId = seasonTournament;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
