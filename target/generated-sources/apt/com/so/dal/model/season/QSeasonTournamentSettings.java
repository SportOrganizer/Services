package com.so.dal.model.season;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSeasonTournamentSettings is a Querydsl query type for SeasonTournamentSettings
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSeasonTournamentSettings extends EntityPathBase<SeasonTournamentSettings> {

    private static final long serialVersionUID = 72795793L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSeasonTournamentSettings seasonTournamentSettings = new QSeasonTournamentSettings("seasonTournamentSettings");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final QSeasonTournament seasonTournament;

    public final StringPath value = createString("value");

    public QSeasonTournamentSettings(String variable) {
        this(SeasonTournamentSettings.class, forVariable(variable), INITS);
    }

    public QSeasonTournamentSettings(Path<? extends SeasonTournamentSettings> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSeasonTournamentSettings(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSeasonTournamentSettings(PathMetadata<?> metadata, PathInits inits) {
        this(SeasonTournamentSettings.class, metadata, inits);
    }

    public QSeasonTournamentSettings(Class<? extends SeasonTournamentSettings> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.seasonTournament = inits.isInitialized("seasonTournament") ? new QSeasonTournament(forProperty("seasonTournament"), inits.get("seasonTournament")) : null;
    }

}

