package com.so.dal.model.game;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGamePlayer is a Querydsl query type for GamePlayer
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGamePlayer extends EntityPathBase<GamePlayer> {

    private static final long serialVersionUID = 1378988902L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGamePlayer gamePlayer = new QGamePlayer("gamePlayer");

    public final QCompetitorTeamPlayer competitorTeamPlayer;

    public final QGame game;

    public final SetPath<GameActivity, QGameActivity> gameActivitiesForIdAssist2player = this.<GameActivity, QGameActivity>createSet("gameActivitiesForIdAssist2player", GameActivity.class, QGameActivity.class, PathInits.DIRECT2);

    public final SetPath<GameActivity, QGameActivity> gameActivitiesForIdAssistPlayer = this.<GameActivity, QGameActivity>createSet("gameActivitiesForIdAssistPlayer", GameActivity.class, QGameActivity.class, PathInits.DIRECT2);

    public final SetPath<GameActivity, QGameActivity> gameActivitiesForIdGoalPlayer = this.<GameActivity, QGameActivity>createSet("gameActivitiesForIdGoalPlayer", GameActivity.class, QGameActivity.class, PathInits.DIRECT2);

    public final SetPath<GameActivity, QGameActivity> gameActivitiesForIdPenaltyPlayer = this.<GameActivity, QGameActivity>createSet("gameActivitiesForIdPenaltyPlayer", GameActivity.class, QGameActivity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QGamePlayer(String variable) {
        this(GamePlayer.class, forVariable(variable), INITS);
    }

    public QGamePlayer(Path<? extends GamePlayer> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGamePlayer(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGamePlayer(PathMetadata<?> metadata, PathInits inits) {
        this(GamePlayer.class, metadata, inits);
    }

    public QGamePlayer(Class<? extends GamePlayer> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.competitorTeamPlayer = inits.isInitialized("competitorTeamPlayer") ? new QCompetitorTeamPlayer(forProperty("competitorTeamPlayer"), inits.get("competitorTeamPlayer")) : null;
        this.game = inits.isInitialized("game") ? new QGame(forProperty("game"), inits.get("game")) : null;
    }

}

