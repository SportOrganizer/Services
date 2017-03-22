package com.so.dal.model.game;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGameShots is a Querydsl query type for GameShots
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGameShots extends EntityPathBase<GameShots> {

    private static final long serialVersionUID = -1338325100L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGameShots gameShots = new QGameShots("gameShots");

    public final QCompetitorTeam competitorTeam;

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final QGame game;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final com.so.dal.model.season.QSeasonTournamentPeriod seasonTournamentPeriod;

    public QGameShots(String variable) {
        this(GameShots.class, forVariable(variable), INITS);
    }

    public QGameShots(Path<? extends GameShots> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGameShots(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGameShots(PathMetadata<?> metadata, PathInits inits) {
        this(GameShots.class, metadata, inits);
    }

    public QGameShots(Class<? extends GameShots> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.competitorTeam = inits.isInitialized("competitorTeam") ? new QCompetitorTeam(forProperty("competitorTeam"), inits.get("competitorTeam")) : null;
        this.game = inits.isInitialized("game") ? new QGame(forProperty("game"), inits.get("game")) : null;
        this.seasonTournamentPeriod = inits.isInitialized("seasonTournamentPeriod") ? new com.so.dal.model.season.QSeasonTournamentPeriod(forProperty("seasonTournamentPeriod"), inits.get("seasonTournamentPeriod")) : null;
    }

}

