/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.core.repository;

import com.so.dal.core.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author peter
 */
@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer>{
    public List<Tournament> findByNameContaining(String name);
    public Tournament findByName(String name);
}
