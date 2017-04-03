/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.core.repository.registration;

import com.mysema.query.jpa.impl.JPAQuery;
import com.so.dal.core.model.registration.QRegistrationTeam;
import com.so.dal.core.model.season.QSeasonTournament;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author peter
 */
public class RegistrationTeamRepositoryImpl implements RegistrationTeamRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Boolean ifContainName(Integer stId, String regTeamName) {
        QRegistrationTeam qRegTeam = QRegistrationTeam.registrationTeam;
        QSeasonTournament qst = QSeasonTournament.seasonTournament;

        return new JPAQuery(em).from(qRegTeam, qst)
                .where(qRegTeam.seasonTournament.id.eq(stId)
                        .and(qRegTeam.name.eq(regTeamName)))
                .exists();
    }

    @Override
    public Boolean ifUniqeName(Integer stId, Integer regTeamId, String regTeamName) {
        QRegistrationTeam qRegTeam = QRegistrationTeam.registrationTeam;
        QSeasonTournament qst = QSeasonTournament.seasonTournament;

        return new JPAQuery(em).from(qRegTeam, qst)
                .where(qRegTeam.seasonTournament.id.eq(stId)
                        .and(qRegTeam.name.eq(regTeamName))
                        .and(qRegTeam.id.ne(regTeamId)))
                .exists();
    }

}
