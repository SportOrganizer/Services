/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.dto.game;

import com.so.core.controller.dto.PersonDTO;
import com.so.core.controller.dto.ResourceDto;

/**
 *
 * @author peter
 */
public class CompetitorTeamPlayerDto {

    private Integer id;
    private Integer competitorTeamId;
    private PersonDTO personInfo;
    private ResourceDto photo;
    private int number;
    private boolean isCapitan;

    public CompetitorTeamPlayerDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompetitorTeamId() {
        return competitorTeamId;
    }

    public void setCompetitorTeamId(Integer competitorTeamId) {
        this.competitorTeamId = competitorTeamId;
    }

    public PersonDTO getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonDTO personInfo) {
        this.personInfo = personInfo;
    }

    public ResourceDto getPhoto() {
        return photo;
    }

    public void setPhoto(ResourceDto photo) {
        this.photo = photo;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isIsCapitan() {
        return isCapitan;
    }

    public void setIsCapitan(boolean isCapitan) {
        this.isCapitan = isCapitan;
    }


}
