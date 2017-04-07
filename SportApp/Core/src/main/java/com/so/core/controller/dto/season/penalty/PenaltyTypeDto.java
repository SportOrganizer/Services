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
public class PenaltyTypeDto {

    private Integer id;
    private String name;

    public PenaltyTypeDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public PenaltyTypeDto() {
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

    

}
