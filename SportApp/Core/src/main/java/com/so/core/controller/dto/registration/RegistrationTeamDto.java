/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.dto.registration;

import com.so.core.controller.dto.ResourceDto;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author peter
 */
public class RegistrationTeamDto {

    private Integer id;
    private ResourceDto znak;
    private Integer seasonTournamentId;
    private String name;
    private String shortName;
    private String color;
    private Boolean isCancelled;
    private Boolean isVerify;
    private String createdTime;
    private Set<RegistrationPlayerDto> registrationPlayers = new HashSet<>(0);

    public RegistrationTeamDto() {
    }

    public RegistrationTeamDto(Integer id, String createdTime, ResourceDto znak, Integer seasonTournamentId, String name, String shortName, String color, Boolean isCancelled, Boolean isVerify) {
        this.id = id;
        this.znak = znak;
        this.seasonTournamentId = seasonTournamentId;
        this.name = name;
        this.shortName = shortName;
        this.color = color;
        this.isCancelled = isCancelled;
        this.isVerify = isVerify;
        this.createdTime = createdTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsCancelled() {
        return isCancelled;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
       // SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
       // String date = DATE_FORMAT.format(createdTime);
        this.createdTime = createdTime;
    }

    public void setIsCancelled(Boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public Boolean getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Boolean isVerify) {
        this.isVerify = isVerify;
    }

    public ResourceDto getZnak() {
        return znak;
    }

    public void setZnak(ResourceDto znak) {
        this.znak = znak;
    }

    public Integer getSeasonTournamentId() {
        return seasonTournamentId;
    }

    public void setSeasonTournamentId(Integer seasonTournamentId) {
        this.seasonTournamentId = seasonTournamentId;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<RegistrationPlayerDto> getRegistrationPlayers() {
        return registrationPlayers;
    }

    public void setRegistrationPlayers(Set<RegistrationPlayerDto> registrationPlayers) {
        this.registrationPlayers = registrationPlayers;
    }

}
