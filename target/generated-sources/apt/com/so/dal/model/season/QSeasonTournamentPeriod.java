package com.so.dal.model.season;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSeasonTournamentPeriod is a Querydsl query type for SeasonTournamentPeriod
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSeasonTournamentPeriod extends EntityPathBase<SeasonTournamentPeriod> {

    private static final long serialVersionUID = 410207151L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSeasonTournamentPeriod seasonTournamentPeriod = new QSeasonTournamentPeriod("seasonTournamentPeriod");

    public final SetPath<com.so.dal.model.game.GameActivity, com.so.dal.model.game.QGameActivity> gameActivities = this.<com.so.dal.model.game.GameActivity, com.so.dal.model.game.QGameActivity>createSet("gameActivities", com.so.dal.model.game.GameActivity.class, com.so.dal.model.game.QGameActivity.class, PathInits.DIRECT2);

    public final SetPath<com.so.dal.model.game.GameShots, com.so.dal.model.game.QGameShots> gameShotses = this.<com.so.dal.model.game.GameShots, com.so.dal.model.game.QGameShots>createSet("gameShotses", com.so.dal.model.game.GameShots.class, com.so.dal.model.game.QGameShots.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final TimePath<java.util.Date> length = createTime("length", java.util.Date.class);

    public final StringPath name = createString("name");

    public final QSeasonTournament seasonTournament;

    public QSeasonTournamentPeriod(String variable) {
        this(SeasonTournamentPeriod.class, forVariable(variable), INITS);
    }

    public QSeasonTournamentPeriod(Path<? extends SeasonTournamentPeriod> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSeasonTournamentPeriod(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSeasonTournamentPeriod(PathMetadata<?> metadata, PathInits inits) {
        this(SeasonTournamentPeriod.class, metadata, inits);
    }

    public QSeasonTournamentPeriod(Class<? extends SeasonTournamentPeriod> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.seasonTournament = inits.isInitialized("seasonTournament") ? new QSeasonTournament(forProperty("seasonTournament"), inits.get("seasonTournament")) : null;
    }

}

