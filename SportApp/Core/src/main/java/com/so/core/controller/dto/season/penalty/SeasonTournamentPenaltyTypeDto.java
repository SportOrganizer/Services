/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.dto.season.penalty;

/**
 *
 * @author peter
 */
public class SeasonTournamentPenaltyTypeDto {

    private Integer id;
    private Integer seasonTournament;
    private PenaltyTypeDto penaltyType;
    private Integer penaltyDuration;
    private Integer penaltyStatsDuration;
    private Boolean isPlayerDown;

    public SeasonTournamentPenaltyTypeDto() {
    }

    public SeasonTournamentPenaltyTypeDto(Integer id, Integer seasonTournament, PenaltyTypeDto penaltyType, Integer penaltyDuration, Integer penaltyStatsDuration, Boolean isPlayerDown) {
        this.id = id;
        this.seasonTournament = seasonTournament;
        this.penaltyType = penaltyType;
        this.penaltyDuration = penaltyDuration;
        this.penaltyStatsDuration = penaltyStatsDuration;
        this.isPlayerDown = isPlayerDown;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeasonTournament() {
        return seasonTournament;
    }

    public void setSeasonTournament(Integer seasonTournament) {
        this.seasonTournament = seasonTournament;
    }

    public PenaltyTypeDto getPenaltyType() {
        return penaltyType;
    }

    public void setPenaltyType(PenaltyTypeDto penaltyType) {
        this.penaltyType = penaltyType;
    }

    public Integer getPenaltyDuration() {
        return penaltyDuration;
    }

    public void setPenaltyDuration(Integer penaltyDuration) {
        this.penaltyDuration = penaltyDuration;
    }

    public Integer getPenaltyStatsDuration() {
        return penaltyStatsDuration;
    }

    public void setPenaltyStatsDuration(Integer penaltyStatsDuration) {
        this.penaltyStatsDuration = penaltyStatsDuration;
    }

    public Boolean getIsPlayerDown() {
        return isPlayerDown;
    }

    public void setIsPlayerDown(Boolean isPlayerDown) {
        this.isPlayerDown = isPlayerDown;
    }

    @Override
    public String toString() {
        return "SeasonTournamentPenaltyTypeDto{" + "id=" + id + ", seasonTournament=" + seasonTournament + ", penaltyType=" + penaltyType + ", penaltyDuration=" + penaltyDuration + ", penaltyStatsDuration=" + penaltyStatsDuration + ", isPlayerDown=" + isPlayerDown + '}';
    }
    
    

}
