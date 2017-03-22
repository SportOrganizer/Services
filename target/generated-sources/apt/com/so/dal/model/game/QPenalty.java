package com.so.dal.model.game;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPenalty is a Querydsl query type for Penalty
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPenalty extends EntityPathBase<Penalty> {

    private static final long serialVersionUID = 1732777846L;

    public static final QPenalty penalty = new QPenalty("penalty");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath isPlayerDown = createBoolean("isPlayerDown");

    public final BooleanPath isPlayerOut = createBoolean("isPlayerOut");

    public final StringPath name = createString("name");

    public final SetPath<com.so.dal.model.season.SeasonTournamentPenalty, com.so.dal.model.season.QSeasonTournamentPenalty> seasonTournamentPenalties = this.<com.so.dal.model.season.SeasonTournamentPenalty, com.so.dal.model.season.QSeasonTournamentPenalty>createSet("seasonTournamentPenalties", com.so.dal.model.season.SeasonTournamentPenalty.class, com.so.dal.model.season.QSeasonTournamentPenalty.class, PathInits.DIRECT2);

    public QPenalty(String variable) {
        super(Penalty.class, forVariable(variable));
    }

    public QPenalty(Path<? extends Penalty> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPenalty(PathMetadata<?> metadata) {
        super(Penalty.class, metadata);
    }

}

