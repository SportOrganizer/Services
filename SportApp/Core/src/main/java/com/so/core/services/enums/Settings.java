/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.enums;

/**
 *
 * @author peter
 */
public enum Settings {

    TEAM_COUNT("počet tímov"),
    GROUP_COUNT("počet skupín"),
    TEAM_IN_GROUP_COUNT("počet tímov v skupine"),
    DRAW_TYPE("typ rozžrebovania"),
    ORDER_RULES("postupový kľúč"),
    GAME_DATES("dátumy zápasov"),
    IF_PLAY_OFF("play off"),
    PROMOTION_TEAM_COUNT("počet tímov postupujúcich zo skupiny"),
    PERIOD_COUNT("počet častí zápasu"),
    PERIOD_TIME("dĺžka jednej časti"),
    PLAY_OFF_DRAW_TYPE("typ žrebovania play off");

    private final String skName;

    private Settings(String skName) {
        this.skName = skName;
    }

    public String getSkName() {
        return skName;
    }

}
