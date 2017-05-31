/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.season;

import com.so.core.exception.AppException;
import com.so.core.services.dto.MatchDto;
import com.so.core.services.enums.DrawType;
import com.so.core.services.enums.Settings;
import com.so.dal.core.model.game.CompetitorTeam;
import com.so.dal.core.model.game.Game;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.model.season.SeasonTournamentGroup;
import com.so.dal.core.model.season.SeasonTournamentLocation;
import com.so.dal.core.model.season.SeasonTournamentRound;
import com.so.dal.core.repository.game.CompetitorTeamRepository;
import com.so.dal.core.repository.game.GameRepository;
import com.so.dal.core.repository.season.SeasonTournamentGroupRepository;
import com.so.dal.core.repository.season.SeasonTournamentLocationRepository;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
import com.so.dal.core.repository.season.SeasonTournamentRoundRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author peter
 */
@Service
public class SeasonTournamentFlow {

    @Autowired
    private SeasonTournamentSettingsService settingService;

    @Autowired
    private SeasonTournamentRepository stRepo;

    @Autowired
    private SeasonTournamentRoundRepository stRoundRepo;

    @Autowired
    private SeasonTournamentGroupRepository stGroupRepo;

    @Autowired
    private CompetitorTeamRepository competitorTeamRepo;
    @Autowired
    private SeasonTournamentLocationRepository stLocationRepo;

    @Autowired
    private GameRepository gameRepo;

    @Transactional
    public void startTurnament(Integer stId) throws AppException {

        
        String drawType = settingService.getSettingBySt(Settings.DRAW_TYPE, stId);
        if (drawType.equals(DrawType.GROUPS.name())) {
     //       makeGroups(stId);
            makeMatches(stId);

        } else if (drawType.equals(DrawType.SINGLE_ELIMINATION.name())) {

        }

        //create match
    }

    @Transactional
    private void makeGroups(Integer stId) throws AppException {
        Integer nmOfTeams = Integer.parseInt(settingService.getSettingBySt(Settings.TEAM_COUNT, stId));
        Integer nmOfGroups = Integer.parseInt(settingService.getSettingBySt(Settings.GROUP_COUNT, stId));
        SeasonTournament st = stRepo.findOne(stId);
        List<CompetitorTeam> teams = competitorTeamRepo.findBySeasonTournament(st);
        List<SeasonTournamentGroup> groups = new ArrayList<>();

        for (int i = 0; i < nmOfGroups; i++) {
            SeasonTournamentGroup g = new SeasonTournamentGroup(st, "group " + (i+1));
            groups.add(stGroupRepo.saveAndFlush(g));
        }

        int i = 0;
        for (CompetitorTeam t : teams) {
            t.setSeasonTournamentGroup(groups.get(i % nmOfGroups));
            CompetitorTeam ct = competitorTeamRepo.saveAndFlush(t);
            if (ct == null) {
                throw new AppException(HttpStatus.NOT_FOUND, "chyba");
            }
            i++;
        }

    }

    @Transactional
    public void makeMatches(Integer stId) throws AppException {
        
         Integer nmOfMutualMatch = Integer.parseInt(settingService.getSettingBySt(Settings.NM_OF_MUTUAL_MATCHES, stId));
        SeasonTournament st = stRepo.findOne(stId);
        List<SeasonTournamentGroup> groups = stGroupRepo.findBySeasonTournament(st);
        List<SeasonTournamentLocation> locations = stLocationRepo.findBySeasonTournament(st);
        int nmOfOlcations = locations.size();
        List<SeasonTournamentRound> rounds = new ArrayList<>();
        List<List<MatchDto>> matches;
        int nmOfTeamsInGroup = competitorTeamRepo.countBySeasonTournamentGroup(groups.get(0));

        int nmOfRound = nmOfTeamsInGroup % 2 == 0 ? nmOfTeamsInGroup - 1 : nmOfTeamsInGroup;

        //vytvorenie kol///////
        for (int i = 0; i < nmOfRound*nmOfMutualMatch; i++) {
            SeasonTournamentRound round = new SeasonTournamentRound(st, (i + 1) + ". round");
            round = stRoundRepo.saveAndFlush(round);
            rounds.add(round);
        }
        /// prechadzam skupiny////////
        for (SeasonTournamentGroup group : groups) {
            // vytvorim zapasy pre skupinu ///////////

            matches = getMatches(group.getCompetitorTeams());
            int i = 0;
            ////// priradim zapasy do kol /////////
            for(int j =0;j<nmOfMutualMatch;j++){
            for (List<MatchDto> matchesList : matches) {

                for (MatchDto m : matchesList) {
                    Game game = createGame(rounds.get(i), st, m, group);
                    rounds.get(i).getGames().add(gameRepo.saveAndFlush(game));
                }
                i++;
            }
            }
            matches.clear();
        }

        // pridanie location
        for (SeasonTournamentRound round : rounds) {
            int i = 0;
            for (Game game : round.getGames()) {
                game.setSeasonTournamentLocation(locations.get(i % nmOfOlcations));
                gameRepo.save(game);
                i++;
            }

        }

    }

   
    private Game createGame(SeasonTournamentRound round, SeasonTournament st, MatchDto teams, SeasonTournamentGroup group) {
        Game game = new Game();

        game.setCompetitorTeamByIdAwayTeam(teams.getCt1());
        game.setCompetitorTeamByIdHomeTeam(teams.getCt2());
        game.setName(teams.getCt1().getTeam().getName() + " vs " + teams.getCt2().getTeam().getName());
        game.setSeasonTournament(st);
        game.setSeasonTournamentGroup(group);
        game.setSeasonTournamentRound(round);
        game.setStartTime(new Date());
        game.setFinished(Boolean.FALSE);

        return game;
    }

@Transactional
    private List<List<MatchDto>> getMatches(Set<CompetitorTeam> lt) {
        List<CompetitorTeam> ListTeam = new ArrayList<>();
        ListTeam.addAll(lt);
        List<List<MatchDto>> l = new ArrayList<>();

        if (ListTeam.size() % 2 != 0) {
            ListTeam.add(null); // If odd number of teams add a dummy
        }
        int numDays = (ListTeam.size() - 1);
        int halfSize = ListTeam.size() / 2;
        List<CompetitorTeam> teams = new ArrayList<>();
        teams.addAll(ListTeam);
        teams.remove(0);

        int teamsSize = teams.size();

        for (int day = 0; day < numDays; day++) {
            List<MatchDto> matches = new ArrayList<>();
            int teamIdx = day % teamsSize;
            CompetitorTeam firstTeam1 = teams.get(teamIdx);
            CompetitorTeam secondTeam1 = ListTeam.get(0);
            if (firstTeam1 != null && secondTeam1 != null) {
                matches.add(new MatchDto(firstTeam1, secondTeam1));
                System.out.println(firstTeam1.getTeam().getName() + " " + secondTeam1.getTeam().getName());
            }

            for (int idx = 1; idx < halfSize; idx++) {
                CompetitorTeam firstTeam = teams.get((day + idx) % teamsSize);
                CompetitorTeam secondTeam = teams.get((day + teamsSize - idx) % teamsSize);
                if (firstTeam != null && secondTeam != null) {
                    matches.add(new MatchDto(firstTeam, secondTeam));
                    System.out.println(firstTeam.getTeam().getName() + " " + secondTeam.getTeam().getName());
                }

            }
            l.add(matches);
        }

        return l;
    }
}
