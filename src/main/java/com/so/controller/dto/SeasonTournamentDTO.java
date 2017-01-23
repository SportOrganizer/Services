package com.so.controller.dto;

import com.so.dal.model.Resource;
import com.so.dal.model.Tournament;
import com.so.dal.model.season.Season;
import com.so.dal.model.season.SeasonTournament;

import java.io.Serializable;

/**
 * Created by janpolacek on 1/23/17.
 */
public class SeasonTournamentDTO{
    private Integer id;
    private Integer seasonId;
    private Integer tournamentId;
    private String name;

    public SeasonTournamentDTO ( SeasonTournament st) {
        this.id = st.getId();
        this.seasonId = st.getSeason().getId();
        this.tournamentId = st.getTournament().getId();
        this.name = st.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
