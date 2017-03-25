/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.so.core.controller.dto;

import com.so.dal.core.model.game.CompetitorTeamPlayer;
import java.util.Set;

/**
 *
 * @author Kristián Kačinetz
 */
public class TeamDTO {
    
    private Integer id;
    private Integer idLogo;
    private String name;
    private String shortName;
    private String color;
    private Set<CompetitorTeamPlayer> competitorTeamPlayer;

    public TeamDTO(Integer id, Integer idLogo, String name, String shortName, String color, Set<CompetitorTeamPlayer> competitorTeamPlayer) {
        this.id = id;
        this.idLogo = idLogo;
        this.name = name;
        this.shortName = shortName;
        this.color = color;
        this.competitorTeamPlayer = competitorTeamPlayer;
    }
    
    
    public TeamDTO(){
        
    }

    public Set<CompetitorTeamPlayer> getCompetitorTeamPlayer() {
        return competitorTeamPlayer;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdLogo() {
        return idLogo;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getColor() {
        return color;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCompetitorTeamPlayer(Set<CompetitorTeamPlayer> competitorTeamPlayer) {
        this.competitorTeamPlayer = competitorTeamPlayer;
    }

    public void setIdLogo(Integer idLogo) {
        this.idLogo = idLogo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
       
    
}
