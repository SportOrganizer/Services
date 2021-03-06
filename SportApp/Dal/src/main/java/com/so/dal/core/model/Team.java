package com.so.dal.core.model;
// Generated 27.2.2017 18:41:18 by Hibernate Tools 4.3.1


import com.so.dal.core.model.game.CompetitorTeam;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Team generated by hbm2java
 */
@Entity
@Table(name="team"
    ,catalog="sport_app_dev"
)
public class Team  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String shortName;
     private String color;
     private Set<CompetitorTeam> competitorTeams = new HashSet<CompetitorTeam>(0);

    public Team() {
    }

	
    public Team(String name, String shortName, String color) {
        this.name = name;
        this.shortName = shortName;
        this.color = color;
    }
    public Team(String name, String shortName, String color, Set<CompetitorTeam> competitorTeams) {
       this.name = name;
       this.shortName = shortName;
       this.color = color;
       this.competitorTeams = competitorTeams;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }


    
    @Column(name="name", nullable=false, length=500)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="shortName", nullable=false, length=500)
    public String getShortName() {
        return this.shortName;
    }
    
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    
    @Column(name="color", nullable=false, length=550)
    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="team", orphanRemoval = true)
    public Set<CompetitorTeam> getCompetitorTeams() {
        return this.competitorTeams;
    }
    
    public void setCompetitorTeams(Set<CompetitorTeam> competitorTeams) {
        this.competitorTeams = competitorTeams;
    }




}


