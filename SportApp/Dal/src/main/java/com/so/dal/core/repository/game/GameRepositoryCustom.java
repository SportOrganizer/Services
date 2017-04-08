/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.core.repository.game;

import com.so.dal.core.model.game.Game;
import java.util.List;

/**
 *
 * @author peter
 */
public interface GameRepositoryCustom {
  public List<Game> filterByGroupROundLocation(Integer group,Integer round, Integer location, Integer stId);  
}
