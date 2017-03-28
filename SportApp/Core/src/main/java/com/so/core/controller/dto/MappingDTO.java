/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.dto;

import com.so.core.controller.dto.registration.RegistrationPlayerDto;

/**
 *
 * @author Kristián Kačinetz
 */
public class MappingDTO {
    
    RegistrationPlayerDto newPlayer;
    Integer competitorTeamId;

    public MappingDTO(RegistrationPlayerDto newPlayer, Integer competitorTeamId) {
        this.newPlayer = newPlayer;
        this.competitorTeamId = competitorTeamId;
    }

    public MappingDTO() {
    }
    
    

    public RegistrationPlayerDto getNewPlayer() {
        return newPlayer;
    }

    public void setNewPlayer(RegistrationPlayerDto newPlayer) {
        this.newPlayer = newPlayer;
    }

    public Integer getCompetitorTeamId() {
        return competitorTeamId;
    }

    public void setCompetitorTeamId(Integer competitorTeamId) {
        this.competitorTeamId = competitorTeamId;
    }
    
    
    
}
