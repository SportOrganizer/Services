/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.dto.registration;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author peter
 */
public class RegistrationTeamDto {

    private String znak;
    private Integer seasonTournamentId;
    private String name;
    private String shortName;
    private String color;
    private Set<RegistrationPlayerDto> registrationPlayers = new HashSet<RegistrationPlayerDto>(0);

    public RegistrationTeamDto() {
    }

    public RegistrationTeamDto( String znak, Integer seasonTournamentId, String name, String shortName, String color) {
        this.znak = znak;
        this.seasonTournamentId = seasonTournamentId;
        this.name = name;
        this.shortName = shortName;
        this.color = color;
    }

    public String getZnak() {
        return znak;
    }

    public void setZnak(String znak) {
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
