package com.so.core.controller.dto.season;

import com.so.dal.core.model.season.Season;
import java.util.List;

/**
 * Created by janpolacek on 1/23/17.
 */
public class SeasonDTO {

    private Integer id;
    private String name;
    private List<SeasonTournamentDTO> seasonTournaments;

    public SeasonDTO() {
    }

    public SeasonDTO(Season season) {
        this.id = season.getId();
        this.name = season.getName();
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

    public List<SeasonTournamentDTO> getSeasonTournaments() {
        return seasonTournaments;
    }

    public void setSeasonTournaments(List<SeasonTournamentDTO> seasonTournament) {
        this.seasonTournaments = seasonTournament;
    }
    
    
}
