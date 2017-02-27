package com.so.controller.dto;

import com.so.dal.model.Tournament;

/**
 * Created by janpolacek on 1/23/17.
 */
public class TournamentDTO {
    private Integer id;
    private String name;

    public TournamentDTO() {
    }

    public TournamentDTO(Tournament tournament) {
        this.id = tournament.getId();
        this.name = tournament.getName();
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
}
