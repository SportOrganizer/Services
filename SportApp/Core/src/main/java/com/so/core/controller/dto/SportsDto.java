/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.dto;

/**
 *
 * @author peter
 */
public class SportsDto {

    private Integer id;
    private String name;

    public SportsDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public SportsDto() {
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
