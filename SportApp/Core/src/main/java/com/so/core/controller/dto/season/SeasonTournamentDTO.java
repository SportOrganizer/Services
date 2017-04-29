package com.so.core.controller.dto.season;

import com.so.core.controller.dto.ResourceDto;
import com.so.core.controller.dto.game.GameDto;
import com.so.dal.core.model.season.SeasonTournament;
import java.util.List;

public class SeasonTournamentDTO {

    private Integer id;
    private Integer seasonId;
    private Integer tournamentId;
    private ResourceDto logo;
    private List<SeasonTournamentLocationDTO> seasonTournamentLocation;
    private List<SeasonTournamentPeriodDTO> seasonTournamentPeriods;
    private List<SeasonTournamentGroupDTO> seasonTournamentGroups;
    private List<SeasonTournamentRoundDTO> seasonTournamentRounds;
    private List<GameDto> seasonTournamentGames;
    private String name;

    public SeasonTournamentDTO() {
    }

    public SeasonTournamentDTO(SeasonTournament st) {
        this.id = st.getId();
        this.seasonId = st.getSeason().getId();
        this.tournamentId = st.getTournament().getId();
        this.name = st.getName();
    }

    public SeasonTournamentDTO(Integer id, Integer seasonId, Integer tournamentId, ResourceDto resource, String name) {
        this.id = id;
        this.seasonId = seasonId;
        this.tournamentId = tournamentId;
        this.logo = resource;
        this.name = name;
    }

    public ResourceDto getLogo() {
        return logo;
    }

    public void setLogo(ResourceDto logo) {
        this.logo = logo;
    }

    public SeasonTournamentDTO(String seasonId, String tournamentId, String name) {
        this.seasonId = Integer.parseInt(seasonId);
        this.tournamentId = Integer.parseInt(tournamentId);
        this.name = name;
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

    public List<SeasonTournamentLocationDTO> getSeasonTournamentLocation() {
        return seasonTournamentLocation;
    }

    public void setSeasonTournamentLocation(List<SeasonTournamentLocationDTO> seasonTournamentLocation) {
        this.seasonTournamentLocation = seasonTournamentLocation;
    }
   

    public List<SeasonTournamentPeriodDTO> getPeriods() {
        return seasonTournamentPeriods;
    }

    public void setPeriods(List<SeasonTournamentPeriodDTO> periods) {
        this.seasonTournamentPeriods = periods;
    }

    public List<SeasonTournamentGroupDTO> getSeasonTournamentGroups() {
        return seasonTournamentGroups;
    }

    public void setSeasonTournamentGroups(List<SeasonTournamentGroupDTO> seasonTournamentGroups) {
        this.seasonTournamentGroups = seasonTournamentGroups;
    }

    public List<SeasonTournamentRoundDTO> getSeasonTournamentRounds() {
        return seasonTournamentRounds;
    }

    public void setSeasonTournamentRounds(List<SeasonTournamentRoundDTO> seasonTournamentRounds) {
        this.seasonTournamentRounds = seasonTournamentRounds;
    }

    public List<GameDto> getSeasonTournamentGames() {
        return seasonTournamentGames;
    }

    public void setSeasonTournamentGames(List<GameDto> seasonTournamentGames) {
        this.seasonTournamentGames = seasonTournamentGames;
    }


    

}
