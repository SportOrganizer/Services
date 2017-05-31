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
public enum OrderRules {

    P_M_S("body vzajomny zapas skore"),
    P_S_M("body skore vzajomny zapas");

    private final String info;

    private OrderRules(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
    
        public static List<String> stringValues() {
        List<String> l = new ArrayList<>();
        OrderRules[] types = OrderRules.values();
        for (OrderRules t : types) {
            l.add(t.name());
        }

        return l;

    }

}
