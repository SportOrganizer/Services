package com.so.dal.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPerson is a Querydsl query type for Person
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPerson extends EntityPathBase<Person> {

    private static final long serialVersionUID = 286080830L;

    public static final QPerson person = new QPerson("person");

    public final DatePath<java.util.Date> birthDate = createDate("birthDate", java.util.Date.class);

    public final SetPath<com.so.dal.model.game.CompetitorTeamPlayer, com.so.dal.model.game.QCompetitorTeamPlayer> competitorTeamPlayers = this.<com.so.dal.model.game.CompetitorTeamPlayer, com.so.dal.model.game.QCompetitorTeamPlayer>createSet("competitorTeamPlayers", com.so.dal.model.game.CompetitorTeamPlayer.class, com.so.dal.model.game.QCompetitorTeamPlayer.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath isStudent = createBoolean("isStudent");

    public final StringPath mail = createString("mail");

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final BooleanPath sex = createBoolean("sex");

    public final StringPath surname = createString("surname");

    public QPerson(String variable) {
        super(Person.class, forVariable(variable));
    }

    public QPerson(Path<? extends Person> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPerson(PathMetadata<?> metadata) {
        super(Person.class, metadata);
    }

}

