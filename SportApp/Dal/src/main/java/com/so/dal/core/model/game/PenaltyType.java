/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.core.model.game;

import com.so.dal.core.model.season.SeasonTournamentPenaltyType;
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
@Table(name = "penaltyType",
        catalog = "sport_app_dev"
)
public class PenaltyType implements java.io.Serializable {
    
    private Integer id;
    private String name;
    private Set<SeasonTournamentPenaltyType> seasonTournamentPenaltyType = new HashSet<>(0);

    public PenaltyType() {
    }

    public PenaltyType(String name) {
        this.name = name;
    }
    
    public PenaltyType(String name, Set<SeasonTournamentPenaltyType> seasonTournamentPenaltyType) {
        this.name = name;
        this.seasonTournamentPenaltyType = seasonTournamentPenaltyType;
    }
    
    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="name", nullable=false, length=1500)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "penaltyType", orphanRemoval = true)
    public Set<SeasonTournamentPenaltyType> getSeasonTournamentPenaltyType() {
        return seasonTournamentPenaltyType;
    }

    public void setSeasonTournamentPenaltyType(Set<SeasonTournamentPenaltyType> seasonTournamentPenaltyType) {
        this.seasonTournamentPenaltyType = seasonTournamentPenaltyType;
    }
        
    
}
