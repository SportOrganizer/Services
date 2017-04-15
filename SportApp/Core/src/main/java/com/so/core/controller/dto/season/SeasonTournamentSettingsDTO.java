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

    private Integer id;
    private Integer idSeasonTournament;
    private SeasonTournamentSettingsTypeDto type;
    private String name;
    private String value;

    public SeasonTournamentSettingsDTO() {
    }

    public SeasonTournamentSettingsDTO(Integer id, Integer idSeasonTournament, SeasonTournamentSettingsTypeDto type, String name, String value) {
        this.id = id;
        this.idSeasonTournament = idSeasonTournament;
        this.type = type;
        this.name = name;
        this.value = value;
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

    public SeasonTournamentSettingsTypeDto getType() {
        return type;
    }

    public void setType(SeasonTournamentSettingsTypeDto type) {
        this.type = type;
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
