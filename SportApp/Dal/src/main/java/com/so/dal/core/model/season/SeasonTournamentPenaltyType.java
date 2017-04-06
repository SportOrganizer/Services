/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.core.model.season;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Kristián Kačinetz
 */
@Entity
@Table(name="seasonTournamentPenaltyType"
    ,catalog="sport_app_dev"
)
public class SeasonTournamentPenaltyType implements java.io.Serializable {
    private Integer id;
    private String displayName;
    private Integer penaltyDuration;
    private Integer penaltyStatsDuration;
    private Boolean isPlayerDown;
    private Set<SeasonTournamentPenaltySettings> seasonTournamentPenaltySettings = new HashSet<>(0);

    public SeasonTournamentPenaltyType(String displayName, Integer penaltyDuration, Integer penaltyStatsDuration, Boolean isPlayerDown) {
        this.displayName = displayName;
        this.penaltyDuration = penaltyDuration;
        this.penaltyStatsDuration = penaltyStatsDuration;
        this.isPlayerDown = isPlayerDown;
    }
    
    public SeasonTournamentPenaltyType(String displayName, Integer penaltyDuration, Integer penaltyStatsDuration, Boolean isPlayerDown, Set<SeasonTournamentPenaltySettings> seasonTournamentPenaltySettings) {
        this.displayName = displayName;
        this.penaltyDuration = penaltyDuration;
        this.penaltyStatsDuration = penaltyStatsDuration;
        this.isPlayerDown = isPlayerDown;
        this.seasonTournamentPenaltySettings = seasonTournamentPenaltySettings;
    }

    @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="DisplayName", nullable=false, length=2500)
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Column(name="penaltyDuration", nullable=false)
    public Integer getPenaltyDuration() {
        return penaltyDuration;
    }

    public void setPenaltyDuration(Integer penaltyDuration) {
        this.penaltyDuration = penaltyDuration;
    }

    @Column(name="penaltyStatsDuration", nullable=false)
    public Integer getPenaltyStatsDuration() {
        return penaltyStatsDuration;
    }

    public void setPenaltyStatsDuration(Integer penaltyStatsDuration) {
        this.penaltyStatsDuration = penaltyStatsDuration;
    }

    @Column(name="isPlayerDown", nullable=false)
    public Boolean getIsPlayerDown() {
        return isPlayerDown;
    }

    public void setIsPlayerDown(Boolean isPlayerDown) {
        this.isPlayerDown = isPlayerDown;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="seasonTournamentPenaltyType", orphanRemoval=true)
    public Set<SeasonTournamentPenaltySettings> getSeasonTournamentPenaltySettings() {
        return seasonTournamentPenaltySettings;
    }

    public void setSeasonTournamentPenaltySettings(Set<SeasonTournamentPenaltySettings> seasonTournamentPenaltySettings) {
        this.seasonTournamentPenaltySettings = seasonTournamentPenaltySettings;
    }
    
    
    
}
