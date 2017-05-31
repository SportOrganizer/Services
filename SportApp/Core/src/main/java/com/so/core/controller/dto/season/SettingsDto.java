/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.dto.season;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peter
 */
public class SettingsDto {
    
    private Integer seasonTournamentId;
    private List<SettingDto> settings = new ArrayList<>();

    public SettingsDto() {
    }

    public Integer getSeasonTournamentId() {
        return seasonTournamentId;
    }

    public void setSeasonTournamentId(Integer seasonTournamentId) {
        this.seasonTournamentId = seasonTournamentId;
    }

    public List<SettingDto> getSettings() {
        return settings;
    }

    public void setSettings(List<SettingDto> settings) {
        this.settings = settings;
    }
    
    
}
