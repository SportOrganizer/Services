/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.enums;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author peter
 */
public enum Settings {

    TEAM_COUNT("počet tímov",null,1),
    GROUP_COUNT("počet skupín",null,2),
    DRAW_TYPE("typ rozžrebovania",DrawType.stringValues(),3), 
    ORDER_RULES("priorita pri tabulke",OrderRules.stringValues(),4),
    GAME_DATES("dátumy zápasov",null,5),
    IF_PLAY_OFF("play off",Arrays.asList("TRUE","FALSE"),6),
    PROMOTION_TEAM_COUNT("počet tímov postupujúcich zo skupiny",null,7),
    PLAY_OFF_DRAW_TYPE("typ žrebovania play off",PlayOffDrawTypeEnum.stringValues(),8),
    TIME_OUT_DURATION("dĺžka oddychového času",null,9),
    NM_OF_MUTUAL_MATCHES("pocet vzajomnych zapasov v skupine",null,10);


    private final String skName;
    private final List<String> options;
    private final Integer id;

    private Settings(String skName, List<String> options,Integer id) {
        this.skName = skName;
        this.options = options;
        this.id = id;
    }

    public List<String> getOptions() {
        return options;
    }


    public String getSkName() {
        return skName;
    }

    public Integer getId() {
        return id;
    }
    
    

}
