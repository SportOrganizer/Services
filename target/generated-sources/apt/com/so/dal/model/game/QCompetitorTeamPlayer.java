package com.so.dal.model.game;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCompetitorTeamPlayer is a Querydsl query type for CompetitorTeamPlayer
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCompetitorTeamPlayer extends EntityPathBase<CompetitorTeamPlayer> {

    private static final long serialVersionUID = 2019264573L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCompetitorTeamPlayer competitorTeamPlayer = new QCompetitorTeamPlayer("competitorTeamPlayer");

    public final QCompetitorTeam competitorTeam;

    public final SetPath<GamePlayer, QGamePlayer> gamePlayers = this.<GamePlayer, QGamePlayer>createSet("gamePlayers", GamePlayer.class, QGamePlayer.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath isCapitan = createBoolean("isCapitan");

    public final NumberPath<Integer> number = createNumber("number", Integer.class);

    public final com.so.dal.model.QPerson person;

    public final com.so.dal.model.QResource resource;

    public QCompetitorTeamPlayer(String variable) {
        this(CompetitorTeamPlayer.class, forVariable(variable), INITS);
    }

    public QCompetitorTeamPlayer(Path<? extends CompetitorTeamPlayer> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCompetitorTeamPlayer(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCompetitorTeamPlayer(PathMetadata<?> metadata, PathInits inits) {
        this(CompetitorTeamPlayer.class, metadata, inits);
    }

    public QCompetitorTeamPlayer(Class<? extends CompetitorTeamPlayer> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.competitorTeam = inits.isInitialized("competitorTeam") ? new QCompetitorTeam(forProperty("competitorTeam"), inits.get("competitorTeam")) : null;
        this.person = inits.isInitialized("person") ? new com.so.dal.model.QPerson(forProperty("person")) : null;
        this.resource = inits.isInitialized("resource") ? new com.so.dal.model.QResource(forProperty("resource")) : null;
    }

}

