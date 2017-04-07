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
public class SeasonTournamentPenaltySettingsDto {
    private Integer id;
    private PenaltyDto penalty;
    private Integer seasonTournamentId;
    private SeasonTournamentPenaltyTypeDto seasonTournamentPenaltyType;

    public SeasonTournamentPenaltySettingsDto() {
    }

    public SeasonTournamentPenaltySettingsDto(Integer id, PenaltyDto penalty, Integer seasonTournamentId, SeasonTournamentPenaltyTypeDto seasonTournamentPenaltyType) {
        this.id = id;
        this.penalty = penalty;
        this.seasonTournamentId = seasonTournamentId;
        this.seasonTournamentPenaltyType = seasonTournamentPenaltyType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PenaltyDto getPenalty() {
        return penalty;
    }

    public void setPenalty(PenaltyDto penalty) {
        this.penalty = penalty;
    }

    public Integer getSeasonTournamentId() {
        return seasonTournamentId;
    }

    public void setSeasonTournamentId(Integer seasonTournamentId) {
        this.seasonTournamentId = seasonTournamentId;
    }

    public SeasonTournamentPenaltyTypeDto getSeasonTournamentPenaltyType() {
        return seasonTournamentPenaltyType;
    }

    public void setSeasonTournamentPenaltyType(SeasonTournamentPenaltyTypeDto seasonTournamentPenaltyType) {
        this.seasonTournamentPenaltyType = seasonTournamentPenaltyType;
    }

    
    
    
}
