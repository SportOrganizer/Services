/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.floorball.controller.floorballGame.dto;

/**
 *
 * @author Kristián Kačinetz
 */
public class SportFloorballGameShotsDto {
    
    private Integer id;
    private Integer idGame;
    private Integer idTeam;
    private Integer idPeriod;
    private Integer count;

    public SportFloorballGameShotsDto() {
    }

    public SportFloorballGameShotsDto(Integer idGame, Integer idTeam, Integer idPeriod, Integer count) {
        this.idGame = idGame;
        this.idTeam = idTeam;
        this.idPeriod = idPeriod;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }

    public Integer getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Integer idTeam) {
        this.idTeam = idTeam;
    }

    public Integer getIdPeriod() {
        return idPeriod;
    }

    public void setIdPeriod(Integer idPeriod) {
        this.idPeriod = idPeriod;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
    
    
    
}
