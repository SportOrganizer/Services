package com.so.dal.model.season;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSeasonTournamentRound is a Querydsl query type for SeasonTournamentRound
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSeasonTournamentRound extends EntityPathBase<SeasonTournamentRound> {

    private static final long serialVersionUID = -538808864L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSeasonTournamentRound seasonTournamentRound = new QSeasonTournamentRound("seasonTournamentRound");

    public final SetPath<com.so.dal.model.game.Game, com.so.dal.model.game.QGame> games = this.<com.so.dal.model.game.Game, com.so.dal.model.game.QGame>createSet("games", com.so.dal.model.game.Game.class, com.so.dal.model.game.QGame.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final QSeasonTournament seasonTournament;

    public QSeasonTournamentRound(String variable) {
        this(SeasonTournamentRound.class, forVariable(variable), INITS);
    }

    public QSeasonTournamentRound(Path<? extends SeasonTournamentRound> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSeasonTournamentRound(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSeasonTournamentRound(PathMetadata<?> metadata, PathInits inits) {
        this(SeasonTournamentRound.class, metadata, inits);
    }

    public QSeasonTournamentRound(Class<? extends SeasonTournamentRound> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.seasonTournament = inits.isInitialized("seasonTournament") ? new QSeasonTournament(forProperty("seasonTournament"), inits.get("seasonTournament")) : null;
    }

}

