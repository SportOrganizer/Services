package com.so.dal.model.game;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGoalType is a Querydsl query type for GoalType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGoalType extends EntityPathBase<GoalType> {

    private static final long serialVersionUID = -701513824L;

    public static final QGoalType goalType = new QGoalType("goalType");

    public final SetPath<GameActivity, QGameActivity> gameActivities = this.<GameActivity, QGameActivity>createSet("gameActivities", GameActivity.class, QGameActivity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QGoalType(String variable) {
        super(GoalType.class, forVariable(variable));
    }

    public QGoalType(Path<? extends GoalType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoalType(PathMetadata<?> metadata) {
        super(GoalType.class, metadata);
    }

}

