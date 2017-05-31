/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.dto;

import com.so.dal.core.model.game.CompetitorTeam;

/**
 *
 * @author peter
 */
public class MatchDto {
    CompetitorTeam ct1;
    CompetitorTeam ct2;

    public MatchDto(CompetitorTeam ct1, CompetitorTeam ct2) {
        this.ct1 = ct1;
        this.ct2 = ct2;
    }

    public CompetitorTeam getCt1() {
        return ct1;
    }

    public void setCt1(CompetitorTeam ct1) {
        this.ct1 = ct1;
    }

    public CompetitorTeam getCt2() {
        return ct2;
    }

    public void setCt2(CompetitorTeam ct2) {
        this.ct2 = ct2;
    }
    
    
}
