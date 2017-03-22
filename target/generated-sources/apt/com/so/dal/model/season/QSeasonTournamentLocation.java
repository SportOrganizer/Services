package com.so.dal.model.season;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSeasonTournamentLocation is a Querydsl query type for SeasonTournamentLocation
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSeasonTournamentLocation extends EntityPathBase<SeasonTournamentLocation> {

    private static final long serialVersionUID = 539208227L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSeasonTournamentLocation seasonTournamentLocation = new QSeasonTournamentLocation("seasonTournamentLocation");

    public final SetPath<com.so.dal.model.game.Game, com.so.dal.model.game.QGame> games = this.<com.so.dal.model.game.Game, com.so.dal.model.game.QGame>createSet("games", com.so.dal.model.game.Game.class, com.so.dal.model.game.QGame.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final QSeasonTournament seasonTournament;

    public QSeasonTournamentLocation(String variable) {
        this(SeasonTournamentLocation.class, forVariable(variable), INITS);
    }

    public QSeasonTournamentLocation(Path<? extends SeasonTournamentLocation> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSeasonTournamentLocation(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSeasonTournamentLocation(PathMetadata<?> metadata, PathInits inits) {
        this(SeasonTournamentLocation.class, metadata, inits);
    }

    public QSeasonTournamentLocation(Class<? extends SeasonTournamentLocation> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.seasonTournament = inits.isInitialized("seasonTournament") ? new QSeasonTournament(forProperty("seasonTournament"), inits.get("seasonTournament")) : null;
    }

}

