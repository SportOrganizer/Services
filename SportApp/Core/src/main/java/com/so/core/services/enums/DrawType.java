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
public enum DrawType {

    GROUPS("skupiny"),
    SINGLE_ELIMINATION("vyradovacka na 1 zapas");
//TODO
// Double , triple, n elimnation

    private final String info;

    private DrawType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public static List<String> stringValues() {
        List<String> l = new ArrayList<>();
        DrawType[] types = DrawType.values();
        for (DrawType t : types) {
            l.add(t.name());
        }

        return l;

    }

}
