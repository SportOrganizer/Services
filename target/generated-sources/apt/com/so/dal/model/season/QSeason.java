package com.so.dal.model.season;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSeason is a Querydsl query type for Season
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSeason extends EntityPathBase<Season> {

    private static final long serialVersionUID = 1985921157L;

    public static final QSeason season = new QSeason("season");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final SetPath<SeasonTournament, QSeasonTournament> seasonTournaments = this.<SeasonTournament, QSeasonTournament>createSet("seasonTournaments", SeasonTournament.class, QSeasonTournament.class, PathInits.DIRECT2);

    public QSeason(String variable) {
        super(Season.class, forVariable(variable));
    }

    public QSeason(Path<? extends Season> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSeason(PathMetadata<?> metadata) {
        super(Season.class, metadata);
    }

}

