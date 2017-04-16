package com.so.core.controller.dto.season;

/**
 * Created by janpolacek on 1/28/17.
 */
public class SeasonTournamentPeriodDTO {
    private Integer id;
    private Integer seasonTournamentId;
    private String name;
    private String length;
    private Boolean isGoldPart;

    public SeasonTournamentPeriodDTO() {
    }

    public SeasonTournamentPeriodDTO(Integer id, Integer seasonTournamentId, String name, String length, Boolean isGoldPart) {
        this.id = id;
        this.seasonTournamentId = seasonTournamentId;
        this.name = name;
        this.length = length;
        this.isGoldPart = isGoldPart;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public Boolean getIsGoldPart() {
        return isGoldPart;
    }

    public void setIsGoldPart(Boolean isGoldPart) {
        this.isGoldPart = isGoldPart;
    }


  
    
}
