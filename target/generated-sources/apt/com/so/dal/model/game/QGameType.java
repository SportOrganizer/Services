package com.so.dal.model.game;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGameType is a Querydsl query type for GameType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGameType extends EntityPathBase<GameType> {

    private static final long serialVersionUID = 95421695L;

    public static final QGameType gameType = new QGameType("gameType");

    public final SetPath<GameActivity, QGameActivity> gameActivities = this.<GameActivity, QGameActivity>createSet("gameActivities", GameActivity.class, QGameActivity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QGameType(String variable) {
        super(GameType.class, forVariable(variable));
    }

    public QGameType(Path<? extends GameType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGameType(PathMetadata<?> metadata) {
        super(GameType.class, metadata);
    }

}

