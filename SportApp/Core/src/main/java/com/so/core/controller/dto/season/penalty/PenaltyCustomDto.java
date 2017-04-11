/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.dto.season.penalty;

/**
 *
 * @author Kristián Kačinetz
 */
public class PenaltyCustomDto {
    
    private Integer id;
    private String namePenalty;
    private String shortNamePenalty;
    private String namePenaltyType;
    private Integer penaltyDuration;
    private Integer penaltyStatsDuration;
    private Boolean isPlayerDown;

    public PenaltyCustomDto() {
    }

    public PenaltyCustomDto(String namePenalty, String shortNamePenalty, String namePenaltyType, Integer penaltyDuration, Integer penaltyStatsDuration, Boolean isPlayerDown) {
        this.namePenalty = namePenalty;
        this.shortNamePenalty = shortNamePenalty;
        this.namePenaltyType = namePenaltyType;
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


    public String getNamePenalty() {
        return namePenalty;
    }

    public void setNamePenalty(String namePenalty) {
        this.namePenalty = namePenalty;
    }

    public String getShortNamePenalty() {
        return shortNamePenalty;
    }

    public void setShortNamePenalty(String shortNamePenalty) {
        this.shortNamePenalty = shortNamePenalty;
    }

    public String getNamePenaltyType() {
        return namePenaltyType;
    }

    public void setNamePenaltyType(String namePenaltyType) {
        this.namePenaltyType = namePenaltyType;
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
    
    
    
}
