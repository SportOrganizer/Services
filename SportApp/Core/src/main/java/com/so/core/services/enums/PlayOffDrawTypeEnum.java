/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peter
 */
public enum PlayOffDrawTypeEnum {

    NORMAL("v prípade 1 skupiny"),
    DYNAMIC("tabuľka postupujúcich"),
    CROSS_GROUP("spárované skupiny");

    private final String info;

    private PlayOffDrawTypeEnum(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public static List<String> stringValues() {
        List<String> l = new ArrayList<>();
        PlayOffDrawTypeEnum[] types = PlayOffDrawTypeEnum.values();
        for (PlayOffDrawTypeEnum t : types) {
            l.add(t.name());
        }

        return l;

    }

}
