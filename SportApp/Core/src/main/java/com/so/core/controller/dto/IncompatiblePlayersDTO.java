/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.so.core.controller.dto;

import com.so.core.controller.dto.registration.RegistrationPlayerDto;
import com.so.dal.core.model.Person;
import com.so.dal.core.model.registration.RegistrationPlayer;

/**
 *
 * @author Kristián Kačinetz
 */
public class IncompatiblePlayersDTO {
    
    RegistrationPlayerDto rp;
    PersonDTO p;
    Integer idCT;

    public IncompatiblePlayersDTO() {
    }

    public IncompatiblePlayersDTO(RegistrationPlayerDto rp, PersonDTO p, Integer idCT) {
        this.rp = rp;
        this.p = p;
        this.idCT = idCT;
    }
   
    

    public RegistrationPlayerDto getRp() {
        return rp;
    }

    public void setRp(RegistrationPlayerDto rp) {
        this.rp = rp;
    }

    public PersonDTO getP() {
        return p;
    }

    public void setP(PersonDTO p) {
        this.p = p;
    }

    public Integer getIdCT() {
        return idCT;
    }

    public void setIdCT(Integer idCT) {
        this.idCT = idCT;
    }



    
    
    
}
