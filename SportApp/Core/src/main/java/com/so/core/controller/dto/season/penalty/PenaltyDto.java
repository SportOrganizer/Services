/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.dto.season.penalty;

/**
 *
 * @author peter
 */
public class PenaltyDto {

    private Integer id;
    private String name;
    private String shortName;
    private Integer sportsId;

    public PenaltyDto() {
    }

    public PenaltyDto(Integer id, String name, String shortName, Integer sportsId) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.sportsId = sportsId;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getSportsId() {
        return sportsId;
    }

    public void setSportsId(Integer sportsId) {
        this.sportsId = sportsId;
    }



}
