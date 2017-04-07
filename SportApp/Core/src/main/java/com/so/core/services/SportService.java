/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services;

import com.so.core.controller.dto.SportsDto;
import com.so.core.exception.AppException;
import com.so.dal.core.model.Sports;
import com.so.dal.core.repository.SportsRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author peter
 */
@Service
public class SportService {

    private final static Logger LOG = LoggerFactory.getLogger(SportService.class);

    @Autowired
    private SportsRepository sportRepo;

    @Transactional
    public SportsDto addSport(SportsDto request) throws AppException {
        if (request.getName() == null) {
            LOG.error("nevyplnene povinne parametre");
            throw new AppException(HttpStatus.BAD_REQUEST, "nevyplnene povinne parametre");
        }

        Sports s = sportRepo.saveAndFlush(new Sports(request.getName()));

        if (s == null) {
            LOG.error("sport sa nepodarilo ulozit do databazy");
            throw new AppException(HttpStatus.BAD_REQUEST, "sport sa nepodarilo ulozit do databazy");
        }
        return new SportsDto(s.getId(), s.getName());
    }

    public List<SportsDto> getAll() {
        List<SportsDto> ldto = new ArrayList<>();

        List<Sports> l = sportRepo.findAll();

        for (Sports s : l) {
            ldto.add(new SportsDto(s.getId(), s.getName()));
        }
        return ldto;

    }

    public void deleteSport(Integer id) {
        LOG.info("delete sport s id {}",id);
        sportRepo.delete(id);
    }

}
