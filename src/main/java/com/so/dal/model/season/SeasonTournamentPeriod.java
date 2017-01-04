package com.so.dal.model.season;
// Generated 3.1.2017 21:20:18 by Hibernate Tools 4.3.1


import com.so.dal.model.game.GameActivity;
import com.so.dal.model.game.GameShots;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SeasonTournamentPeriod generated by hbm2java
 */
@Entity
@Table(name="seasonTournamentPeriod"
    ,catalog="sport_app_dev"
)
public class SeasonTournamentPeriod  implements java.io.Serializable {


     private Integer id;
     private SeasonTournament seasonTournament;
     private String name;
     private Date length;
     private Set<GameShots> gameShotses = new HashSet<GameShots>(0);
     private Set<GameActivity> gameActivities = new HashSet<GameActivity>(0);

    public SeasonTournamentPeriod() {
    }

	
    public SeasonTournamentPeriod(SeasonTournament seasonTournament, String name, Date length) {
        this.seasonTournament = seasonTournament;
        this.name = name;
        this.length = length;
    }
    public SeasonTournamentPeriod(SeasonTournament seasonTournament, String name, Date length, Set<GameShots> gameShotses, Set<GameActivity> gameActivities) {
       this.seasonTournament = seasonTournament;
       this.name = name;
       this.length = length;
       this.gameShotses = gameShotses;
       this.gameActivities = gameActivities;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idSeasonTournament", nullable=false)
    public SeasonTournament getSeasonTournament() {
        return this.seasonTournament;
    }
    
    public void setSeasonTournament(SeasonTournament seasonTournament) {
        this.seasonTournament = seasonTournament;
    }

    
    @Column(name="name", nullable=false, length=1500)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.TIME)
    @Column(name="length", nullable=false, length=8)
    public Date getLength() {
        return this.length;
    }
    
    public void setLength(Date length) {
        this.length = length;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="seasonTournamentPeriod")
    public Set<GameShots> getGameShotses() {
        return this.gameShotses;
    }
    
    public void setGameShotses(Set<GameShots> gameShotses) {
        this.gameShotses = gameShotses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="seasonTournamentPeriod")
    public Set<GameActivity> getGameActivities() {
        return this.gameActivities;
    }
    
    public void setGameActivities(Set<GameActivity> gameActivities) {
        this.gameActivities = gameActivities;
    }




}

