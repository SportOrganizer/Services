package com.so.core.controller.dto.season;

import com.so.core.controller.dto.game.GameDto;
import com.so.dal.core.model.season.SeasonTournamentRound;
import java.util.List;

/**
 * Created by janpolacek on 1/28/17.
 */
public class SeasonTournamentRoundDTO {
    private Integer id;
    private Integer seasonTournamentId;
    private String name;
    private List<GameDto> games;

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

    public List<GameDto> getGames() {
        return games;
    }

    public void setGames(List<GameDto> games) {
        this.games = games;
    }
}
