package com.so.core.controller.dto;

import com.so.core.controller.dto.season.SeasonTournamentDTO;
import com.so.dal.core.model.Tournament;
import java.util.List;

/**
 * Created by janpolacek on 1/23/17.
 */
public class TournamentDTO {

    private Integer id;
    private String name;
    private List<SeasonTournamentDTO> seasonTournaments;
    private Integer length;

    public TournamentDTO() {
    }

    public TournamentDTO(Tournament tournament) {
        this.id = tournament.getId();
        this.name = tournament.getName();
    }

    public TournamentDTO(Integer id, String name, List<SeasonTournamentDTO> seasonTournaments, Integer length) {
        this.id = id;
        this.name = name;
        this.seasonTournaments = seasonTournaments;
        this.length = length;
    }

    public Integer getLength() {
        return length;
    }

    public List<SeasonTournamentDTO> getSeasonTournaments() {
        return seasonTournaments;
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

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeasonTournaments(List<SeasonTournamentDTO> seasonTournaments) {
        this.seasonTournaments = seasonTournaments;
    }
}
