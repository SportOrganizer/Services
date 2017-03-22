package com.so.dal.model.season;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSeasonTournamentPenalty is a Querydsl query type for SeasonTournamentPenalty
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSeasonTournamentPenalty extends EntityPathBase<SeasonTournamentPenalty> {

    private static final long serialVersionUID = -172414885L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSeasonTournamentPenalty seasonTournamentPenalty = new QSeasonTournamentPenalty("seasonTournamentPenalty");

    public final SetPath<com.so.dal.model.game.GameActivity, com.so.dal.model.game.QGameActivity> gameActivities = this.<com.so.dal.model.game.GameActivity, com.so.dal.model.game.QGameActivity>createSet("gameActivities", com.so.dal.model.game.GameActivity.class, com.so.dal.model.game.QGameActivity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final com.so.dal.model.game.QPenalty penalty;

    public final TimePath<java.util.Date> penaltyTime = createTime("penaltyTime", java.util.Date.class);

    public final QSeasonTournament seasonTournament;

    public QSeasonTournamentPenalty(String variable) {
        this(SeasonTournamentPenalty.class, forVariable(variable), INITS);
    }

    public QSeasonTournamentPenalty(Path<? extends SeasonTournamentPenalty> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSeasonTournamentPenalty(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSeasonTournamentPenalty(PathMetadata<?> metadata, PathInits inits) {
        this(SeasonTournamentPenalty.class, metadata, inits);
    }

    public QSeasonTournamentPenalty(Class<? extends SeasonTournamentPenalty> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.penalty = inits.isInitialized("penalty") ? new com.so.dal.model.game.QPenalty(forProperty("penalty")) : null;
        this.seasonTournament = inits.isInitialized("seasonTournament") ? new QSeasonTournament(forProperty("seasonTournament"), inits.get("seasonTournament")) : null;
    }

}

