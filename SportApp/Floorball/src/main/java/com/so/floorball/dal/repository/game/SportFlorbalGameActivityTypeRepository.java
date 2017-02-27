/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.floorball.dal.repository.game;

import com.so.floorball.dal.model.game.SportFlorbalGameActivityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author peter
 */
@Repository
public interface SportFlorbalGameActivityTypeRepository extends JpaRepository<SportFlorbalGameActivityType, Integer> {
    
}
