package com.so.dal.model.game;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGameActivity is a Querydsl query type for GameActivity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGameActivity extends EntityPathBase<GameActivity> {

    private static final long serialVersionUID = -1176830380L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGameActivity gameActivity = new QGameActivity("gameActivity");

    public final QCompetitorTeam competitorTeam;

    public final QGame game;

    public final QGamePlayer gamePlayerByIdAssist2player;

    public final QGamePlayer gamePlayerByIdAssistPlayer;

    public final QGamePlayer gamePlayerByIdGoalPlayer;

    public final QGamePlayer gamePlayerByIdPenaltyPlayer;

    public final DateTimePath<java.util.Date> gameTime = createDateTime("gameTime", java.util.Date.class);

    public final QGameType gameType;

    public final QGoalType goalType;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> newScoreAway = createNumber("newScoreAway", Integer.class);

    public final NumberPath<Integer> newScoreHome = createNumber("newScoreHome", Integer.class);

    public final DateTimePath<java.util.Date> penaltySeconds = createDateTime("penaltySeconds", java.util.Date.class);

    public final DateTimePath<java.util.Date> realTime = createDateTime("realTime", java.util.Date.class);

    public final com.so.dal.model.season.QSeasonTournamentPenalty seasonTournamentPenalty;

    public final com.so.dal.model.season.QSeasonTournamentPeriod seasonTournamentPeriod;

    public QGameActivity(String variable) {
        this(GameActivity.class, forVariable(variable), INITS);
    }

    public QGameActivity(Path<? extends GameActivity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGameActivity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGameActivity(PathMetadata<?> metadata, PathInits inits) {
        this(GameActivity.class, metadata, inits);
    }

    public QGameActivity(Class<? extends GameActivity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.competitorTeam = inits.isInitialized("competitorTeam") ? new QCompetitorTeam(forProperty("competitorTeam"), inits.get("competitorTeam")) : null;
        this.game = inits.isInitialized("game") ? new QGame(forProperty("game"), inits.get("game")) : null;
        this.gamePlayerByIdAssist2player = inits.isInitialized("gamePlayerByIdAssist2player") ? new QGamePlayer(forProperty("gamePlayerByIdAssist2player"), inits.get("gamePlayerByIdAssist2player")) : null;
        this.gamePlayerByIdAssistPlayer = inits.isInitialized("gamePlayerByIdAssistPlayer") ? new QGamePlayer(forProperty("gamePlayerByIdAssistPlayer"), inits.get("gamePlayerByIdAssistPlayer")) : null;
        this.gamePlayerByIdGoalPlayer = inits.isInitialized("gamePlayerByIdGoalPlayer") ? new QGamePlayer(forProperty("gamePlayerByIdGoalPlayer"), inits.get("gamePlayerByIdGoalPlayer")) : null;
        this.gamePlayerByIdPenaltyPlayer = inits.isInitialized("gamePlayerByIdPenaltyPlayer") ? new QGamePlayer(forProperty("gamePlayerByIdPenaltyPlayer"), inits.get("gamePlayerByIdPenaltyPlayer")) : null;
        this.gameType = inits.isInitialized("gameType") ? new QGameType(forProperty("gameType")) : null;
        this.goalType = inits.isInitialized("goalType") ? new QGoalType(forProperty("goalType")) : null;
        this.seasonTournamentPenalty = inits.isInitialized("seasonTournamentPenalty") ? new com.so.dal.model.season.QSeasonTournamentPenalty(forProperty("seasonTournamentPenalty"), inits.get("seasonTournamentPenalty")) : null;
        this.seasonTournamentPeriod = inits.isInitialized("seasonTournamentPeriod") ? new com.so.dal.model.season.QSeasonTournamentPeriod(forProperty("seasonTournamentPeriod"), inits.get("seasonTournamentPeriod")) : null;
    }

}

