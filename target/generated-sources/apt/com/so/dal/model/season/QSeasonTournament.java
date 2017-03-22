package com.so.dal.model.season;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSeasonTournament is a Querydsl query type for SeasonTournament
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSeasonTournament extends EntityPathBase<SeasonTournament> {

    private static final long serialVersionUID = -1254894514L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSeasonTournament seasonTournament = new QSeasonTournament("seasonTournament");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final SetPath<com.so.dal.model.registration.RegistrationTeam, com.so.dal.model.registration.QRegistrationTeam> registrationTeams = this.<com.so.dal.model.registration.RegistrationTeam, com.so.dal.model.registration.QRegistrationTeam>createSet("registrationTeams", com.so.dal.model.registration.RegistrationTeam.class, com.so.dal.model.registration.QRegistrationTeam.class, PathInits.DIRECT2);

    public final com.so.dal.model.QResource resource;

    public final QSeason season;

    public final SetPath<SeasonTournamentGroup, QSeasonTournamentGroup> seasonTournamentGroups = this.<SeasonTournamentGroup, QSeasonTournamentGroup>createSet("seasonTournamentGroups", SeasonTournamentGroup.class, QSeasonTournamentGroup.class, PathInits.DIRECT2);

    public final SetPath<SeasonTournamentLocation, QSeasonTournamentLocation> seasonTournamentLocations = this.<SeasonTournamentLocation, QSeasonTournamentLocation>createSet("seasonTournamentLocations", SeasonTournamentLocation.class, QSeasonTournamentLocation.class, PathInits.DIRECT2);

    public final SetPath<SeasonTournamentPenalty, QSeasonTournamentPenalty> seasonTournamentPenalties = this.<SeasonTournamentPenalty, QSeasonTournamentPenalty>createSet("seasonTournamentPenalties", SeasonTournamentPenalty.class, QSeasonTournamentPenalty.class, PathInits.DIRECT2);

    public final SetPath<SeasonTournamentPeriod, QSeasonTournamentPeriod> seasonTournamentPeriods = this.<SeasonTournamentPeriod, QSeasonTournamentPeriod>createSet("seasonTournamentPeriods", SeasonTournamentPeriod.class, QSeasonTournamentPeriod.class, PathInits.DIRECT2);

    public final SetPath<SeasonTournamentRound, QSeasonTournamentRound> seasonTournamentRounds = this.<SeasonTournamentRound, QSeasonTournamentRound>createSet("seasonTournamentRounds", SeasonTournamentRound.class, QSeasonTournamentRound.class, PathInits.DIRECT2);

    public final SetPath<SeasonTournamentSettings, QSeasonTournamentSettings> seasonTournamentSettingses = this.<SeasonTournamentSettings, QSeasonTournamentSettings>createSet("seasonTournamentSettingses", SeasonTournamentSettings.class, QSeasonTournamentSettings.class, PathInits.DIRECT2);

    public final com.so.dal.model.QTournament tournament;

    public QSeasonTournament(String variable) {
        this(SeasonTournament.class, forVariable(variable), INITS);
    }

    public QSeasonTournament(Path<? extends SeasonTournament> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSeasonTournament(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSeasonTournament(PathMetadata<?> metadata, PathInits inits) {
        this(SeasonTournament.class, metadata, inits);
    }

    public QSeasonTournament(Class<? extends SeasonTournament> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.resource = inits.isInitialized("resource") ? new com.so.dal.model.QResource(forProperty("resource")) : null;
        this.season = inits.isInitialized("season") ? new QSeason(forProperty("season")) : null;
        this.tournament = inits.isInitialized("tournament") ? new com.so.dal.model.QTournament(forProperty("tournament")) : null;
    }

}

