/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.dto.season;

/**
 *
 * @author Kristian
 */
public class SeasonTournamentSettingsDTO {
    Integer id;
    Integer idSeasonTournament;
    String name;
    String value;

    public SeasonTournamentSettingsDTO() {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdSeasonTournament() {
        return idSeasonTournament;
    }

    public void setIdSeasonTournament(Integer idSeasonTournament) {
        this.idSeasonTournament = idSeasonTournament;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
}
