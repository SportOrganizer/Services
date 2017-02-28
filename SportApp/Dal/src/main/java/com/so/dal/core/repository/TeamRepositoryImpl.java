/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.core.repository;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Projections;
import com.so.dal.core.model.QResource;
import com.so.dal.core.model.QTeam;
import com.so.dal.core.repository.customDto.PathsToLogos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author peter
 */
public class TeamRepositoryImpl implements TeamRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

//////pomocou queryDsl.... o technologii http://www.querydsl.com/index.html
//funkcia vrati list timov s cestou k ich logom
    public List<PathsToLogos> getPathsToLogos() {
        QTeam qTeam = QTeam.team;
        QResource qLogo = QResource.resource;
        List<PathsToLogos> l = new JPAQuery(em).from(qTeam, qLogo)
                .where(qTeam.resource.eq(qLogo))
                .list(Projections.bean(PathsToLogos.class, qTeam.name, qLogo.path));
        return l;
    }

    public List<PathsToLogos> getPathsToLogos2() {
        List<Object[]> l = em.createQuery("SELECT t.name, r.path FROM Team t, Resource r WHERE t.resource.id = r.id").getResultList();
        return convertObjectToDto(l);
    }

    private List<PathsToLogos> convertObjectToDto(List<Object[]> object) {
        List<PathsToLogos> l = new ArrayList<PathsToLogos>();
        for (Object[] o : object) {
            PathsToLogos pt = new PathsToLogos((String) o[0], (String) o[1]);
            l.add(pt);
        }
        return l;
    }

}
