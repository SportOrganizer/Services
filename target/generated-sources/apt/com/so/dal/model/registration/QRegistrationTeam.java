package com.so.dal.model.registration;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRegistrationTeam is a Querydsl query type for RegistrationTeam
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRegistrationTeam extends EntityPathBase<RegistrationTeam> {

    private static final long serialVersionUID = 373086594L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRegistrationTeam registrationTeam = new QRegistrationTeam("registrationTeam");

    public final StringPath color = createString("color");

    public final DateTimePath<java.util.Date> createdTime = createDateTime("createdTime", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath isCancelled = createBoolean("isCancelled");

    public final BooleanPath isVerify = createBoolean("isVerify");

    public final StringPath name = createString("name");

    public final SetPath<RegistrationPlayer, QRegistrationPlayer> registrationPlayers = this.<RegistrationPlayer, QRegistrationPlayer>createSet("registrationPlayers", RegistrationPlayer.class, QRegistrationPlayer.class, PathInits.DIRECT2);

    public final com.so.dal.model.QResource resource;

    public final com.so.dal.model.season.QSeasonTournament seasonTournament;

    public final StringPath shortName = createString("shortName");

    public QRegistrationTeam(String variable) {
        this(RegistrationTeam.class, forVariable(variable), INITS);
    }

    public QRegistrationTeam(Path<? extends RegistrationTeam> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRegistrationTeam(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRegistrationTeam(PathMetadata<?> metadata, PathInits inits) {
        this(RegistrationTeam.class, metadata, inits);
    }

    public QRegistrationTeam(Class<? extends RegistrationTeam> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.resource = inits.isInitialized("resource") ? new com.so.dal.model.QResource(forProperty("resource")) : null;
        this.seasonTournament = inits.isInitialized("seasonTournament") ? new com.so.dal.model.season.QSeasonTournament(forProperty("seasonTournament"), inits.get("seasonTournament")) : null;
    }

}

