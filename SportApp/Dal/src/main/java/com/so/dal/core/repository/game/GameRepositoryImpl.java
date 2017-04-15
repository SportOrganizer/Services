/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.core.repository.game;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;
import com.so.dal.core.model.game.Game;
import com.so.dal.core.model.game.QGame;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author peter
 */
public class GameRepositoryImpl implements GameRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Game> filterByGroupRoundLocationFinished(Integer group, Integer round, Integer location,Boolean finished, Integer stId) {
        QGame qGame = QGame.game;

        Predicate pg;
        Predicate pr;
        Predicate pl;
        Predicate pf;
        Predicate pSt =qGame.seasonTournament.id.eq(stId);
        
       

        if (group == null) {
            pg = null;
        } else {
            pg = qGame.seasonTournamentGroup.id.eq(group);
        }

        if (round == null) {
            pr = null;
        } else {
            pr = qGame.seasonTournamentRound.id.eq(round);
        }

        if (location == null) {
            pl = null;
        } else {
            pl = qGame.seasonTournamentLocation.id.eq(location);
        }
        
        if(finished==null){
            pf = null;
        }else{
            pf = qGame.finished.eq(finished);
        }

        return new JPAQuery(em).from(qGame)
                .where(pg, pr, pl,pf, pSt).orderBy(qGame.startTime.asc())
                .list(qGame);
    }

}
