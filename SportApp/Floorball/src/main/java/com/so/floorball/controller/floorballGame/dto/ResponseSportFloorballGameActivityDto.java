/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.floorball.controller.floorballGame.dto;

import com.so.core.controller.dto.game.CompetitorTeamDto;
import com.so.core.controller.dto.game.GameDto;
import com.so.core.controller.dto.game.GamePlayerDto;
import com.so.core.controller.dto.season.SeasonTournamentPeriodDTO;
import com.so.core.controller.dto.season.penalty.PenaltyCustomDto;
import com.so.core.controller.dto.season.penalty.SeasonTournamentPenaltyTypeDto;

/**
 *
 * @author Kristian Kacinetz
 */
public class ResponseSportFloorballGameActivityDto {

    private Integer id;
    private Integer idGame;
    private Integer idCompetitorTeam;
    private Integer idGamePeriod; 
    private String gameTime;
    private String realTime;
    private Integer idActivityType;
    private GamePlayerDto assistPlayer;
    private GamePlayerDto goalPlayer;
    private GamePlayerDto assist2player;
    private GamePlayerDto penaltyPlayer;
    private PenaltyCustomDto penaltyType;
    private SportFloorballGoalTypeDto goalType;
    private Integer newScoreHome;
    private Integer newScoreAway;
    private String penaltySeconds;

    public ResponseSportFloorballGameActivityDto() {
    }

    public ResponseSportFloorballGameActivityDto(Integer idGame, Integer idCompetitorTeam, Integer idGamePeriod, String gameTime, String realTime, Integer idActivityType, GamePlayerDto assistPlayer, GamePlayerDto goalPlayer, GamePlayerDto assist2player, GamePlayerDto penaltyPlayer, PenaltyCustomDto penaltyType, SportFloorballGoalTypeDto goalType, Integer newScoreHome, Integer newScoreAway, String penaltySeconds) {
        this.idGame = idGame;
        this.idCompetitorTeam = idCompetitorTeam;
        this.idGamePeriod = idGamePeriod;
        this.gameTime = gameTime;
        this.realTime = realTime;
        this.idActivityType = idActivityType;
        this.assistPlayer = assistPlayer;
        this.goalPlayer = goalPlayer;
        this.assist2player = assist2player;
        this.penaltyPlayer = penaltyPlayer;
        this.penaltyType = penaltyType;
        this.goalType = goalType;
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

    public GamePlayerDto getAssistPlayer() {
        return assistPlayer;
    }

    public void setAssistPlayer(GamePlayerDto assistPlayer) {
        this.assistPlayer = assistPlayer;
    }

    public GamePlayerDto getGoalPlayer() {
        return goalPlayer;
    }

    public void setGoalPlayer(GamePlayerDto goalPlayer) {
        this.goalPlayer = goalPlayer;
    }

    public GamePlayerDto getAssist2player() {
        return assist2player;
    }

    public void setAssist2player(GamePlayerDto assist2player) {
        this.assist2player = assist2player;
    }

    public GamePlayerDto getPenaltyPlayer() {
        return penaltyPlayer;
    }

    public void setPenaltyPlayer(GamePlayerDto penaltyPlayer) {
        this.penaltyPlayer = penaltyPlayer;
    }

    public PenaltyCustomDto getPenaltyType() {
        return penaltyType;
    }

    public void setPenaltyType(PenaltyCustomDto penaltyType) {
        this.penaltyType = penaltyType;
    }

    public SportFloorballGoalTypeDto getGoalType() {
        return goalType;
    }

    public void setGoalType(SportFloorballGoalTypeDto goalType) {
        this.goalType = goalType;
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
