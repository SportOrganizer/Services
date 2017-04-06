/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.dto.game;

/**
 *
 * @author Kristián Kačinetz
 */
public class GamePlayerRequestDto {
    
    private Integer idCompetitorTeam;
    private Integer idGame;
    
    public GamePlayerRequestDto(){
        
    }

    public GamePlayerRequestDto(Integer idCompetitorTeam, Integer idGame) {
        this.idCompetitorTeam = idCompetitorTeam;
        this.idGame = idGame;
    }
    
    

    public Integer getIdCompetitorTeam() {
        return idCompetitorTeam;
    }

    public void setIdCompetitorTeam(Integer idCompetitorTeam) {
        this.idCompetitorTeam = idCompetitorTeam;
    }

    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }
    
    
    
}
