package com.so.dal.model.game;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCompetitorTeam is a Querydsl query type for CompetitorTeam
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCompetitorTeam extends EntityPathBase<CompetitorTeam> {

    private static final long serialVersionUID = -1892639172L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCompetitorTeam competitorTeam = new QCompetitorTeam("competitorTeam");

    public final SetPath<CompetitorTeamPlayer, QCompetitorTeamPlayer> competitorTeamPlayers = this.<CompetitorTeamPlayer, QCompetitorTeamPlayer>createSet("competitorTeamPlayers", CompetitorTeamPlayer.class, QCompetitorTeamPlayer.class, PathInits.DIRECT2);

    public final SetPath<GameActivity, QGameActivity> gameActivities = this.<GameActivity, QGameActivity>createSet("gameActivities", GameActivity.class, QGameActivity.class, PathInits.DIRECT2);

    public final SetPath<Game, QGame> gamesForIdAwayTeam = this.<Game, QGame>createSet("gamesForIdAwayTeam", Game.class, QGame.class, PathInits.DIRECT2);

    public final SetPath<Game, QGame> gamesForIdHomeTeam = this.<Game, QGame>createSet("gamesForIdHomeTeam", Game.class, QGame.class, PathInits.DIRECT2);

    public final SetPath<GameShots, QGameShots> gameShotses = this.<GameShots, QGameShots>createSet("gameShotses", GameShots.class, QGameShots.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final com.so.dal.model.QResource resource;

    public final com.so.dal.model.season.QSeasonTournamentGroup seasonTournamentGroup;

    public final com.so.dal.model.QTeam team;

    public QCompetitorTeam(String variable) {
        this(CompetitorTeam.class, forVariable(variable), INITS);
    }

    public QCompetitorTeam(Path<? extends CompetitorTeam> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCompetitorTeam(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCompetitorTeam(PathMetadata<?> metadata, PathInits inits) {
        this(CompetitorTeam.class, metadata, inits);
    }

    public QCompetitorTeam(Class<? extends CompetitorTeam> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.resource = inits.isInitialized("resource") ? new com.so.dal.model.QResource(forProperty("resource")) : null;
        this.seasonTournamentGroup = inits.isInitialized("seasonTournamentGroup") ? new com.so.dal.model.season.QSeasonTournamentGroup(forProperty("seasonTournamentGroup"), inits.get("seasonTournamentGroup")) : null;
        this.team = inits.isInitialized("team") ? new com.so.dal.model.QTeam(forProperty("team"), inits.get("team")) : null;
    }

}

