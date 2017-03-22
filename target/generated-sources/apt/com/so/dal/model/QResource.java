package com.so.dal.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QResource is a Querydsl query type for Resource
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QResource extends EntityPathBase<Resource> {

    private static final long serialVersionUID = -738455113L;

    public static final QResource resource = new QResource("resource");

    public final SetPath<com.so.dal.model.game.CompetitorTeamPlayer, com.so.dal.model.game.QCompetitorTeamPlayer> competitorTeamPlayers = this.<com.so.dal.model.game.CompetitorTeamPlayer, com.so.dal.model.game.QCompetitorTeamPlayer>createSet("competitorTeamPlayers", com.so.dal.model.game.CompetitorTeamPlayer.class, com.so.dal.model.game.QCompetitorTeamPlayer.class, PathInits.DIRECT2);

    public final SetPath<com.so.dal.model.game.CompetitorTeam, com.so.dal.model.game.QCompetitorTeam> competitorTeams = this.<com.so.dal.model.game.CompetitorTeam, com.so.dal.model.game.QCompetitorTeam>createSet("competitorTeams", com.so.dal.model.game.CompetitorTeam.class, com.so.dal.model.game.QCompetitorTeam.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath path = createString("path");

    public final SetPath<com.so.dal.model.registration.RegistrationTeam, com.so.dal.model.registration.QRegistrationTeam> registrationTeams = this.<com.so.dal.model.registration.RegistrationTeam, com.so.dal.model.registration.QRegistrationTeam>createSet("registrationTeams", com.so.dal.model.registration.RegistrationTeam.class, com.so.dal.model.registration.QRegistrationTeam.class, PathInits.DIRECT2);

    public final SetPath<com.so.dal.model.season.SeasonTournament, com.so.dal.model.season.QSeasonTournament> seasonTournaments = this.<com.so.dal.model.season.SeasonTournament, com.so.dal.model.season.QSeasonTournament>createSet("seasonTournaments", com.so.dal.model.season.SeasonTournament.class, com.so.dal.model.season.QSeasonTournament.class, PathInits.DIRECT2);

    public final SetPath<Team, QTeam> teams = this.<Team, QTeam>createSet("teams", Team.class, QTeam.class, PathInits.DIRECT2);

    public QResource(String variable) {
        super(Resource.class, forVariable(variable));
    }

    public QResource(Path<? extends Resource> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResource(PathMetadata<?> metadata) {
        super(Resource.class, metadata);
    }

}

