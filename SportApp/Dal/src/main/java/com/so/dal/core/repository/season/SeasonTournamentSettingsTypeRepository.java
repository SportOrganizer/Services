/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.core.repository.season;

import com.so.dal.core.model.season.SeasonTournamentSettingsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author peter
 */
@Repository
public interface SeasonTournamentSettingsTypeRepository extends JpaRepository<SeasonTournamentSettingsType, Integer>{
            
}
