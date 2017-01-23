/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.services.season;

import com.so.dal.model.season.Season;
import com.so.dal.repository.season.SeasonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class SeasonService {

    private final static Logger LOG = LoggerFactory.getLogger(SeasonService.class); /// logovanie..

    @Autowired
    SeasonRepository seasonRepo;

    public Season findById(Integer id){
        Season s = seasonRepo.findOne(id);
        return s;
    }

    public List<Season> findByNameContaining(String name){
        List<Season> ls= seasonRepo.findByNameContaining(name);
        return ls;
    }

    public Season findByName(String name){
        Season s = seasonRepo.findByName(name);
        return  s;
    }

    public List<Season> findAll(){
        List<Season> ls= seasonRepo.findAll();
        return ls;
    }

    @Transactional
    public Boolean createSeason(String name) {

        if(seasonRepo.findByName(name) != null){
            return false;
        }

        Season s = new Season(name);
        s  = seasonRepo.saveAndFlush(s);

        if (s == null) {
            return false;
        }

        return true;
    }





}
