/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.converter;

import com.so.core.controller.dto.TeamDTO;
import com.so.dal.core.model.Team;
import com.so.dal.core.model.game.CompetitorTeamPlayer;
import com.so.dal.core.repository.game.CompetitorTeamPlayerRepository;
import com.so.dal.core.repository.game.CompetitorTeamRepository;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kristián Kačinetz
 */
@Service
public class TeamConverter {

    private final static Logger LOG = LoggerFactory.getLogger(RegistrationConverter.class);

    @Autowired
    private CompetitorTeamRepository competitorTeamRepo;

    @Autowired
    private CompetitorTeamPlayerRepository competitorTeamPlayerRepo;

    public TeamDTO teamEntityToDto(Team entity, boolean ifCopyCompetitorTeamPlayer) {
        Set<CompetitorTeamPlayer> set = new HashSet<>();
        TeamDTO dto = new TeamDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setShortName(entity.getShortName());
        dto.setColor(entity.getColor());
        if (ifCopyCompetitorTeamPlayer) {
            for (CompetitorTeamPlayer player : competitorTeamPlayerRepo.findByCompetitorTeam(competitorTeamRepo.findOne(entity.getId()))) {
                set.add(player);
            }
            dto.setCompetitorTeamPlayer(set);
        }
        return dto;
    }

}
