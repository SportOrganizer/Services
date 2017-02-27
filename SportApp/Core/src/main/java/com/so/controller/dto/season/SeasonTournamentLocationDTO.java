/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.controller.dto.season;

import com.so.dal.model.season.SeasonTournamentLocation;

/**
 *
 * @author Tomas
 */
public class SeasonTournamentLocationDTO {
    private Integer id;
    private String name;
    private Integer seasonTournamentId;

    public SeasonTournamentLocationDTO(String name, Integer seasonTournamentId) {
        this.name = name;
        this.seasonTournamentId = seasonTournamentId;
    }
    
    public SeasonTournamentLocationDTO(String name, String seasonTournamentId) {
        this.name = name;
        this.seasonTournamentId = Integer.parseInt(seasonTournamentId);
    }

    public SeasonTournamentLocationDTO() {
    }

    public SeasonTournamentLocationDTO(SeasonTournamentLocation stl) {
        this.id = stl.getId();
        this.name = stl.getName();
        this.seasonTournamentId = stl.getSeasonTournament().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeasonTournamentId() {
        return seasonTournamentId;
    }

    public void setSeasonTournamentId(Integer seasonTournamentId) {
        this.seasonTournamentId = seasonTournamentId;
    }
    
    
}
