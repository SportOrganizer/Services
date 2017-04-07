/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.converter;

import com.so.core.controller.dto.TeamDTO;
import com.so.core.exception.AppException;
import com.so.dal.core.model.Team;
import com.so.dal.core.model.game.CompetitorTeamPlayer;
import com.so.dal.core.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kristián Kačinetz
 */
@Service
public class TeamConverter {

    private final static Logger LOG = LoggerFactory.getLogger(Team.class);

    @Autowired
    private TeamRepository teamRepo;

    public TeamDTO teamEntityToDto(Team entity) {

        TeamDTO dto = new TeamDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setShortName(entity.getShortName());
        dto.setColor(entity.getColor());
        return dto;
    }
    
    public Team teamDtoToEntity(TeamDTO dto) throws AppException{
        Team entity;
        if (dto.getId() != null) {
            entity = teamRepo.findOne(dto.getId());
            if (entity == null) {
                LOG.error("nenajdeny Team podla id: {}", dto.getId());
                throw new AppException(HttpStatus.BAD_REQUEST, "bolo zadane nespravne id: " + dto.getId());
            }
        } else {
            entity = new Team();
        }
        
        entity.setColor(dto.getColor());
        entity.setName(dto.getName());
        entity.setShortName(dto.getShortName());
        
        return entity;
    }

}
