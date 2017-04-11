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
public class SportFloorballGameActivityTypeDto {

    private Integer id;
    private String name;
    private String showName;

    public SportFloorballGameActivityTypeDto(String name, String showName) {
        this.name = name;
        this.showName = showName;
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

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }
    
    
}
