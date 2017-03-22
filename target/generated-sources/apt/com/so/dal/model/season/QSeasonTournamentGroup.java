package com.so.dal.model.season;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSeasonTournamentGroup is a Querydsl query type for SeasonTournamentGroup
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSeasonTournamentGroup extends EntityPathBase<SeasonTournamentGroup> {

    private static final long serialVersionUID = -548883759L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSeasonTournamentGroup seasonTournamentGroup = new QSeasonTournamentGroup("seasonTournamentGroup");

    public final SetPath<com.so.dal.model.game.CompetitorTeam, com.so.dal.model.game.QCompetitorTeam> competitorTeams = this.<com.so.dal.model.game.CompetitorTeam, com.so.dal.model.game.QCompetitorTeam>createSet("competitorTeams", com.so.dal.model.game.CompetitorTeam.class, com.so.dal.model.game.QCompetitorTeam.class, PathInits.DIRECT2);

    public final SetPath<com.so.dal.model.game.Game, com.so.dal.model.game.QGame> games = this.<com.so.dal.model.game.Game, com.so.dal.model.game.QGame>createSet("games", com.so.dal.model.game.Game.class, com.so.dal.model.game.QGame.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final QSeasonTournament seasonTournament;

    public QSeasonTournamentGroup(String variable) {
        this(SeasonTournamentGroup.class, forVariable(variable), INITS);
    }

    public QSeasonTournamentGroup(Path<? extends SeasonTournamentGroup> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSeasonTournamentGroup(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSeasonTournamentGroup(PathMetadata<?> metadata, PathInits inits) {
        this(SeasonTournamentGroup.class, metadata, inits);
    }

    public QSeasonTournamentGroup(Class<? extends SeasonTournamentGroup> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.seasonTournament = inits.isInitialized("seasonTournament") ? new QSeasonTournament(forProperty("seasonTournament"), inits.get("seasonTournament")) : null;
    }

}

