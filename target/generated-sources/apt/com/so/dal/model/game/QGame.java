package com.so.dal.model.game;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGame is a Querydsl query type for Game
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGame extends EntityPathBase<Game> {

    private static final long serialVersionUID = -483615707L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGame game = new QGame("game");

    public final QCompetitorTeam competitorTeamByIdAwayTeam;

    public final QCompetitorTeam competitorTeamByIdHomeTeam;

    public final BooleanPath contumated = createBoolean("contumated");

    public final BooleanPath finished = createBoolean("finished");

    public final SetPath<GameActivity, QGameActivity> gameActivities = this.<GameActivity, QGameActivity>createSet("gameActivities", GameActivity.class, QGameActivity.class, PathInits.DIRECT2);

    public final SetPath<GamePlayer, QGamePlayer> gamePlayers = this.<GamePlayer, QGamePlayer>createSet("gamePlayers", GamePlayer.class, QGamePlayer.class, PathInits.DIRECT2);

    public final SetPath<GameShots, QGameShots> gameShotses = this.<GameShots, QGameShots>createSet("gameShotses", GameShots.class, QGameShots.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final BooleanPath overtime = createBoolean("overtime");

    public final DateTimePath<java.util.Date> realStart = createDateTime("realStart", java.util.Date.class);

    public final com.so.dal.model.season.QSeasonTournamentGroup seasonTournamentGroup;

    public final com.so.dal.model.season.QSeasonTournamentLocation seasonTournamentLocation;

    public final com.so.dal.model.season.QSeasonTournamentRound seasonTournamentRound;

    public final DateTimePath<java.util.Date> startTime = createDateTime("startTime", java.util.Date.class);

    public QGame(String variable) {
        this(Game.class, forVariable(variable), INITS);
    }

    public QGame(Path<? extends Game> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGame(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGame(PathMetadata<?> metadata, PathInits inits) {
        this(Game.class, metadata, inits);
    }

    public QGame(Class<? extends Game> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.competitorTeamByIdAwayTeam = inits.isInitialized("competitorTeamByIdAwayTeam") ? new QCompetitorTeam(forProperty("competitorTeamByIdAwayTeam"), inits.get("competitorTeamByIdAwayTeam")) : null;
        this.competitorTeamByIdHomeTeam = inits.isInitialized("competitorTeamByIdHomeTeam") ? new QCompetitorTeam(forProperty("competitorTeamByIdHomeTeam"), inits.get("competitorTeamByIdHomeTeam")) : null;
        this.seasonTournamentGroup = inits.isInitialized("seasonTournamentGroup") ? new com.so.dal.model.season.QSeasonTournamentGroup(forProperty("seasonTournamentGroup"), inits.get("seasonTournamentGroup")) : null;
        this.seasonTournamentLocation = inits.isInitialized("seasonTournamentLocation") ? new com.so.dal.model.season.QSeasonTournamentLocation(forProperty("seasonTournamentLocation"), inits.get("seasonTournamentLocation")) : null;
        this.seasonTournamentRound = inits.isInitialized("seasonTournamentRound") ? new com.so.dal.model.season.QSeasonTournamentRound(forProperty("seasonTournamentRound"), inits.get("seasonTournamentRound")) : null;
    }

}

