package com.so.dal.model.registration;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRegistrationPlayer is a Querydsl query type for RegistrationPlayer
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRegistrationPlayer extends EntityPathBase<RegistrationPlayer> {

    private static final long serialVersionUID = 1945894086L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRegistrationPlayer registrationPlayer = new QRegistrationPlayer("registrationPlayer");

    public final DatePath<java.util.Date> birthDate = createDate("birthDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath isProfessional = createBoolean("isProfessional");

    public final BooleanPath isStudent = createBoolean("isStudent");

    public final BooleanPath isVerified = createBoolean("isVerified");

    public final StringPath mail = createString("mail");

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public final NumberPath<Integer> number = createNumber("number", Integer.class);

    public final StringPath phone = createString("phone");

    public final QRegistrationTeam registrationTeam;

    public final BooleanPath sex = createBoolean("sex");

    public final StringPath surname = createString("surname");

    public QRegistrationPlayer(String variable) {
        this(RegistrationPlayer.class, forVariable(variable), INITS);
    }

    public QRegistrationPlayer(Path<? extends RegistrationPlayer> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRegistrationPlayer(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRegistrationPlayer(PathMetadata<?> metadata, PathInits inits) {
        this(RegistrationPlayer.class, metadata, inits);
    }

    public QRegistrationPlayer(Class<? extends RegistrationPlayer> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.registrationTeam = inits.isInitialized("registrationTeam") ? new QRegistrationTeam(forProperty("registrationTeam"), inits.get("registrationTeam")) : null;
    }

}

