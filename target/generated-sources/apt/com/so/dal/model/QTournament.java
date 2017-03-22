package com.so.dal.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTournament is a Querydsl query type for Tournament
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTournament extends EntityPathBase<Tournament> {

    private static final long serialVersionUID = -636100270L;

    public static final QTournament tournament = new QTournament("tournament");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final SetPath<com.so.dal.model.season.SeasonTournament, com.so.dal.model.season.QSeasonTournament> seasonTournaments = this.<com.so.dal.model.season.SeasonTournament, com.so.dal.model.season.QSeasonTournament>createSet("seasonTournaments", com.so.dal.model.season.SeasonTournament.class, com.so.dal.model.season.QSeasonTournament.class, PathInits.DIRECT2);

    public QTournament(String variable) {
        super(Tournament.class, forVariable(variable));
    }

    public QTournament(Path<? extends Tournament> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTournament(PathMetadata<?> metadata) {
        super(Tournament.class, metadata);
    }

}

