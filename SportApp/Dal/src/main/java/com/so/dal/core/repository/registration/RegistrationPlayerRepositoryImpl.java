/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.core.repository.registration;

import com.mysema.query.jpa.impl.JPAQuery;
import com.so.dal.core.model.registration.QRegistrationPlayer;
import com.so.dal.core.model.registration.QRegistrationTeam;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author peter
 */
public class RegistrationPlayerRepositoryImpl implements RegistrationPlayerRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Boolean ifUniqueNumber(Integer regTeamId, Integer regPlayerId, Integer number) {
        QRegistrationTeam qrt = QRegistrationTeam.registrationTeam;
        QRegistrationPlayer qrp = QRegistrationPlayer.registrationPlayer;

        return new JPAQuery(em).from(qrp, qrt)
                .where(qrp.number.eq(number)
                        .and(qrp.registrationTeam.id.eq(regTeamId))
                        .and(qrp.id.ne(regPlayerId)))
                .exists();

    }

    @Override
    public Boolean ifUniqueCaptain(Integer regTeamId, Integer regPlayerId, Boolean ifCaptain) {
        QRegistrationTeam qrt = QRegistrationTeam.registrationTeam;
        QRegistrationPlayer qrp = QRegistrationPlayer.registrationPlayer;
        if(ifCaptain){
          return new JPAQuery(em).from(qrp, qrt)
                .where(qrp.isCaptain.eq(ifCaptain)
                        .and(qrp.registrationTeam.id.eq(regTeamId))
                        .and(qrp.id.ne(regPlayerId)))
                .exists();
        }else {
         return !new JPAQuery(em).from(qrp, qrt)
                .where(qrp.isCaptain.eq(Boolean.TRUE)
                        .and(qrt.id.eq(regTeamId))
                        .and(qrp.id.ne(regPlayerId)))
                .exists();
        
    }
    }

    @Override
    public Boolean ifUniqueEmail(Integer regTeamId, Integer regPlayerId, String email) {
        QRegistrationTeam qrt = QRegistrationTeam.registrationTeam;
        QRegistrationPlayer qrp = QRegistrationPlayer.registrationPlayer;

        return new JPAQuery(em).from(qrp, qrt)
                .where(qrp.mail.eq(email)
                        .and(qrp.registrationTeam.id.eq(regTeamId))
                        .and(qrp.id.ne(regPlayerId)))
                .exists();
    }

}
