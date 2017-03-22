/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.so.core.controller.dto;

import com.so.dal.core.model.Person;
import com.so.dal.core.model.registration.RegistrationPlayer;

/**
 *
 * @author Kristián Kačinetz
 */
public class IncompatiblePlayersDTO {
    
    RegistrationPlayer rp;
    Person p;

    public IncompatiblePlayersDTO(RegistrationPlayer rp, Person p) {
        this.rp = rp;
        this.p = p;
    }

    public RegistrationPlayer getRp() {
        return rp;
    }

    public Person getP() {
        return p;
    }

    public void setRp(RegistrationPlayer rp) {
        this.rp = rp;
    }

    public void setP(Person p) {
        this.p = p;
    }
    
    
    
}
