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
public class RequestSportFloorballGameActivityDto {
    
    private Integer id;
    private Integer idGame;
    private Integer idCompetitorTeam;
    private Integer idGamePeriod; 
    private String gameTime;
    private String realTime;
    private Integer idActivityType;
    private Integer idAssistPlayer;
    private Integer idGoalPlayer;
    private Integer idAssist2player;
    private Integer idPenaltyPlayer;
    private Integer idPenaltyType;
    private Integer idGoalType;
    private Integer newScoreHome;
    private Integer newScoreAway;
    private String penaltySeconds;

    public RequestSportFloorballGameActivityDto() {
    }

    public RequestSportFloorballGameActivityDto(Integer idGame, Integer idCompetitorTeam, Integer idGamePeriod, String gameTime, String realTime, Integer idActivityType, Integer idAssistPlayer, Integer idGoalPlayer, Integer idAssist2player, Integer idPenaltyPlayer, Integer idPenaltyType, Integer idGoalType, Integer newScoreHome, Integer newScoreAway, String penaltySeconds) {
        this.idGame = idGame;
        this.idCompetitorTeam = idCompetitorTeam;
        this.idGamePeriod = idGamePeriod;
        this.gameTime = gameTime;
        this.realTime = realTime;
        this.idActivityType = idActivityType;
        this.idAssistPlayer = idAssistPlayer;
        this.idGoalPlayer = idGoalPlayer;
        this.idAssist2player = idAssist2player;
        this.idPenaltyPlayer = idPenaltyPlayer;
        this.idPenaltyType = idPenaltyType;
        this.idGoalType = idGoalType;
        this.newScoreHome = newScoreHome;
        this.newScoreAway = newScoreAway;
        this.penaltySeconds = penaltySeconds;
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

    public Integer getIdCompetitorTeam() {
        return idCompetitorTeam;
    }

    public void setIdCompetitorTeam(Integer idCompetitorTeam) {
        this.idCompetitorTeam = idCompetitorTeam;
    }

    public Integer getIdGamePeriod() {
        return idGamePeriod;
    }

    public void setIdGamePeriod(Integer idGamePeriod) {
        this.idGamePeriod = idGamePeriod;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public String getRealTime() {
        return realTime;
    }

    public void setRealTime(String realTime) {
        this.realTime = realTime;
    }

    public Integer getIdActivityType() {
        return idActivityType;
    }

    public void setIdActivityType(Integer idActivityType) {
        this.idActivityType = idActivityType;
    }

    public Integer getIdAssistPlayer() {
        return idAssistPlayer;
    }

    public void setIdAssistPlayer(Integer idAssistPlayer) {
        this.idAssistPlayer = idAssistPlayer;
    }

    public Integer getIdGoalPlayer() {
        return idGoalPlayer;
    }

    public void setIdGoalPlayer(Integer idGoalPlayer) {
        this.idGoalPlayer = idGoalPlayer;
    }

    public Integer getIdAssist2player() {
        return idAssist2player;
    }

    public void setIdAssist2player(Integer idAssist2player) {
        this.idAssist2player = idAssist2player;
    }

    public Integer getIdPenaltyPlayer() {
        return idPenaltyPlayer;
    }

    public void setIdPenaltyPlayer(Integer idPenaltyPlayer) {
        this.idPenaltyPlayer = idPenaltyPlayer;
    }

    public Integer getIdPenaltyType() {
        return idPenaltyType;
    }

    public void setIdPenaltyType(Integer idPenaltyType) {
        this.idPenaltyType = idPenaltyType;
    }

    public Integer getIdGoalType() {
        return idGoalType;
    }

    public void setIdGoalType(Integer idGoalType) {
        this.idGoalType = idGoalType;
    }

    public Integer getNewScoreHome() {
        return newScoreHome;
    }

    public void setNewScoreHome(Integer newScoreHome) {
        this.newScoreHome = newScoreHome;
    }

    public Integer getNewScoreAway() {
        return newScoreAway;
    }

    public void setNewScoreAway(Integer newScoreAway) {
        this.newScoreAway = newScoreAway;
    }

    public String getPenaltySeconds() {
        return penaltySeconds;
    }

    public void setPenaltySeconds(String penaltySeconds) {
        this.penaltySeconds = penaltySeconds;
    }
    
    
    
}
