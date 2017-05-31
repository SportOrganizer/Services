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
public class SportFloorballGoalTypeDto {

    private Integer id;
    private String name;
    private Boolean cancelPenalty;


    
    public SportFloorballGoalTypeDto() {
        
    }

    public SportFloorballGoalTypeDto(String name, Boolean cancelPenalty) {
        this.name = name;
        this.cancelPenalty = cancelPenalty;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCancelPenalty() {
        return cancelPenalty;
    }

    public void setCancelPenalty(Boolean cancelPenalty) {
        this.cancelPenalty = cancelPenalty;
    }
    
          

}
