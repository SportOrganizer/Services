/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.floorball.repository.game;

import com.so.dal.floorball.model.game.SportFlorbalGameShots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author peter
 */
@Repository
public interface SportFlorbalGameShotsRepository extends JpaRepository<SportFlorbalGameShots, Integer> {
    
}
