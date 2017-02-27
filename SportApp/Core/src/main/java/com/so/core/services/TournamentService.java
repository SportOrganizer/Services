/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services;

import com.so.dal.core.model.Tournament;
import com.so.dal.core.repository.TournamentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TournamentService {

    private final static Logger LOG = LoggerFactory.getLogger(TournamentService.class); /// logovanie..

    @Autowired
    TournamentRepository tournamentRepo;

    public Tournament findById(Integer id){
        Tournament s = tournamentRepo.findOne(id);
        return s;
    }

    public List<Tournament> findByNameContaining(String name){
        List<Tournament> ls= tournamentRepo.findByNameContaining(name);
        return ls;
    }

    public Tournament findByName(String name){
        Tournament s = tournamentRepo.findByName(name);
        return  s;
    }

    public List<Tournament> findAll(){
        List<Tournament> ls= tournamentRepo.findAll();
        return ls;
    }

    @Transactional
    public Boolean createTournament(String name) {

        if(tournamentRepo.findByName(name) != null){
            return false;
        }

        Tournament s = new Tournament(name);
        s  = tournamentRepo.saveAndFlush(s);

        if (s == null) {
            return false;
        }

        return true;
    }





}
