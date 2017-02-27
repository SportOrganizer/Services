package com.so.controller.dto.season;

import com.so.dal.model.season.SeasonTournament;
import com.so.dal.model.season.SeasonTournamentRound;

/**
 * Created by janpolacek on 1/28/17.
 */
public class SeasonTournamentRoundDTO {
    private Integer id;
    private Integer seasonTournamentId;
    private String name;

    public SeasonTournamentRoundDTO() {
    }

    public SeasonTournamentRoundDTO(Integer seasonTournamentId, String name) {
        this.seasonTournamentId = seasonTournamentId;
        this.name = name;
    }

    public SeasonTournamentRoundDTO(SeasonTournamentRound st) {
        this.id = st.getId();
        this.seasonTournamentId = st.getId();
        this.name = st.getName();
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
}
