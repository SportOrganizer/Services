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

import java.security.InvalidParameterException;
import java.util.List;


@Service
public class SeasonService {

    private final static Logger LOG = LoggerFactory.getLogger(SeasonService.class); /// logovanie..

    @Autowired
    SeasonRepository seasonRepo;

    public Season findById(Integer id){
        LOG.info("findById({})", id);
        if(id == null){
            LOG.error("id can't be null: {}", id);
            throw new InvalidParameterException("required parameter null");
        }

        Season s = seasonRepo.findOne(id);
        return s;
    }

    public List<Season> findByNameContaining(String name){
        LOG.info("findByNameContaining({})", name);

        if(name == null){
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }
        List<Season> ls= seasonRepo.findByNameContaining(name);
        return ls;
    }

    public Season findByName(String name){
        LOG.info("findByNameContaining({})", name);
        if(name == null){
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }

        Season s = seasonRepo.findByName(name);
        return  s;
    }

    public List<Season> findAll(){
        LOG.info("findAll()");

        List<Season> ls= seasonRepo.findAll();
        return ls;
    }

    @Transactional
    public Boolean createSeason(String name) {
        LOG.info("createSeason({})",name);

        if(name == null){
            LOG.error("name can't be null: {}", name);
            throw new InvalidParameterException("required parameter null");
        }

        if(seasonRepo.findByName(name) != null){
            LOG.error("duplicate name: {}", name);
            return false;
        }

        Season s = new Season(name);
        s  = seasonRepo.saveAndFlush(s);

        if (s == null) {
            LOG.error("save has failed: {}", name);
            return false;
        }

        return true;
    }





}
