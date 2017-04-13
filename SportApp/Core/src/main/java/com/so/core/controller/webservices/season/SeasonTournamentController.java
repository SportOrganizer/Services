package com.so.core.controller.webservices.season;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.so.core.controller.dto.game.GameDto;
import com.so.core.controller.dto.season.SeasonTournamentDTO;
import com.so.core.controller.dto.season.SeasonTournamentGroupDTO;
import com.so.core.controller.dto.season.SeasonTournamentLocationDTO;
import com.so.core.controller.dto.season.SeasonTournamentRoundDTO;
import com.so.core.controller.dto.season.penalty.SeasonTournamentPenaltySettingsDto;
import com.so.core.controller.dto.season.penalty.SeasonTournamentPenaltyTypeDto;
import com.so.core.exception.AppException;
import com.so.core.services.game.GameService;
import com.so.core.services.game.PenaltyService;
import com.so.core.services.season.SeasonTournamentGroupService;
import com.so.core.services.season.SeasonTournamentLocationService;
import com.so.core.services.season.SeasonTournamentRoundService;
import com.so.core.services.season.SeasonTournamentService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin
@RequestMapping("/seasontournament")
public class SeasonTournamentController {

    @Autowired
    SeasonTournamentService seasonTournamentService;

    @Autowired
    SeasonTournamentLocationService locationService;

    @Autowired
    SeasonTournamentRoundService roundService;

    @Autowired
    SeasonTournamentGroupService groupService;

    @Autowired
    private GameService gameService;

    @Autowired
    private PenaltyService penaltyService;

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeasonTournaments(@RequestParam(value = "q", required = false) String query) throws AppException {
        List<SeasonTournamentDTO> seasonTournaments;
        JsonObject jo = new JsonObject();
        Gson gson = new Gson();

        if (query != null) {
            seasonTournaments = seasonTournamentService.findByNameContaining(query);
            jo.addProperty("query", query);

        } else {
            seasonTournaments = seasonTournamentService.findAll();
        }

        jo.addProperty("length", seasonTournaments.size());
        jo.add("results", gson.toJsonTree(seasonTournaments));

        return jo.toString();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSeasonTournament(@PathVariable(value = "id") Integer id) throws AppException {
        SeasonTournamentDTO seasonTournamentDTO;
        Gson gson = new Gson();

        seasonTournamentDTO = seasonTournamentService.findById(id);
        return gson.toJson(seasonTournamentDTO);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String createSeasonTournament(@RequestBody SeasonTournamentDTO st) throws IOException, AppException {
        Gson gson = new Gson();

        SeasonTournamentDTO response = seasonTournamentService.createSeasonTournament(st);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteSeasonTournament(@PathVariable(value = "id") Integer i) throws AppException {
        Gson gson = new Gson();

        seasonTournamentService.deleteSeasonTournament(i);
        List< SeasonTournamentDTO> response = seasonTournamentService.findAll();
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String editSeasonTOurnament(@PathVariable(value = "id") Integer id, @RequestBody SeasonTournamentDTO st) throws AppException {
        Gson gson = new Gson();
        if (!Objects.equals(id, st.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id url sa nerovna s id v dto");
        }
        SeasonTournamentDTO edited = seasonTournamentService.update(st);
        return gson.toJson(edited);
    }

    @RequestMapping(path = "/location/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addLocation(@RequestBody SeasonTournamentLocationDTO request) throws AppException {
        Gson gson = new Gson();
        SeasonTournamentLocationDTO response = locationService.createSeasonTournamentLocation(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/location/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllLocation() throws AppException {
        Gson gson = new Gson();
        List<SeasonTournamentLocationDTO> response = locationService.findAll();
        return gson.toJson(response);
    }

    @RequestMapping(path = "/location/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getLocation(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        SeasonTournamentLocationDTO response = locationService.findById(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{stId}/location/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getLocationBySt(@PathVariable(value = "stId") Integer id) throws AppException {
        Gson gson = new Gson();
        List<SeasonTournamentLocationDTO> response = locationService.findAllBySeasonTournament(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/location/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String editLocation(@PathVariable(value = "id") Integer id, @RequestBody SeasonTournamentLocationDTO st) throws AppException {
        Gson gson = new Gson();
        if (!Objects.equals(id, st.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id url sa nerovna s id v dto");
        }
        SeasonTournamentLocationDTO edited = locationService.update(st);
        return gson.toJson(edited);
    }

    @RequestMapping(path = "/round/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addRound(@RequestBody SeasonTournamentRoundDTO request) throws AppException {
        Gson gson = new Gson();
        SeasonTournamentRoundDTO response = roundService.createSeasonTournamentRound(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/round/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllRound() throws AppException {
        Gson gson = new Gson();
        List<SeasonTournamentRoundDTO> response = roundService.findAll();
        return gson.toJson(response);
    }

    @RequestMapping(path = "/round/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getRound(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        SeasonTournamentRoundDTO response = roundService.findById(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{stId}/round/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getRoundBySt(@PathVariable(value = "stId") Integer id) throws AppException {
        Gson gson = new Gson();
        List<SeasonTournamentRoundDTO> response = roundService.findAllBySeasonTournament(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/round/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String editRound(@PathVariable(value = "id") Integer id, @RequestBody SeasonTournamentRoundDTO st) throws AppException {
        Gson gson = new Gson();
        if (!Objects.equals(id, st.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id url sa nerovna s id v dto");
        }
        SeasonTournamentRoundDTO edited = roundService.update(st);
        return gson.toJson(edited);
    }

    @RequestMapping(path = "/group/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addGroup(@RequestBody SeasonTournamentGroupDTO request) throws AppException {
        Gson gson = new Gson();
        SeasonTournamentGroupDTO response = groupService.createSeasonTournamentGroup(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/group/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllGroup() throws AppException {
        Gson gson = new Gson();
        List<SeasonTournamentGroupDTO> response = groupService.findAll();
        return gson.toJson(response);
    }

    @RequestMapping(path = "/group/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getGroup(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        SeasonTournamentGroupDTO response = groupService.findById(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{stId}/group/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getGroupBySt(@PathVariable(value = "stId") Integer id) throws AppException {
        Gson gson = new Gson();
        List<SeasonTournamentGroupDTO> response = groupService.findAllBySeasonTournament(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/group/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String editGroup(@PathVariable(value = "id") Integer id, @RequestBody SeasonTournamentGroupDTO st) throws AppException {
        Gson gson = new Gson();
        if (!Objects.equals(id, st.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id url sa nerovna s id v dto");
        }
        SeasonTournamentGroupDTO edited = groupService.update(st);
        return gson.toJson(edited);
    }

    @RequestMapping(path = "/game/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addGame(@RequestBody GameDto request) throws AppException {
        Gson gson = new Gson();
        GameDto response = gameService.createGame(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/game/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getGame(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        GameDto response = gameService.findGame(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/game/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllGame() throws AppException {
        Gson gson = new Gson();
        List<GameDto> response = gameService.findAllGames();
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{stId}/game/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getGameBySt(@RequestParam(value = "round", required = false) Integer idRound,
            @RequestParam(value = "location", required = false) Integer idLocation,
            @RequestParam(value = "group", required = false) Integer idGroup,
            @PathVariable(value = "stId") Integer id) throws AppException {
        Gson gson = new Gson();
        List<GameDto> response = gameService.findBySeasonTournament(id,idGroup,idRound, idLocation);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/game/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String editGame(@PathVariable(value = "id") Integer id, @RequestBody GameDto st) throws AppException {
        Gson gson = new Gson();
        if (!Objects.equals(id, st.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id url sa nerovna s id v dto");
        }
        GameDto edited = gameService.editGame(st);
        return gson.toJson(edited);
    }

    @RequestMapping(path = "/game/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteGame(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        gameService.deleteGame(id);
        return gson.toJson(gameService.findAllGames());
    }

    ///////////////////////////// Season Tournament PenaltyType /////////////////////////////////////
    @RequestMapping(path = "/stpenaltytype/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addStPenaltyType(@RequestBody SeasonTournamentPenaltyTypeDto request) throws AppException {
        Gson gson = new Gson();
        SeasonTournamentPenaltyTypeDto response = penaltyService.addStPenaltyType(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/stpenaltytype/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateStPenaltyType(@PathVariable(value = "id") Integer id, @RequestBody SeasonTournamentPenaltyTypeDto request) throws AppException {

        if (!Objects.equals(id, request.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id v url sa nerovna id v dto");
        }
        Gson gson = new Gson();
        SeasonTournamentPenaltyTypeDto response = penaltyService.editStPenaltyType(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/stpenaltytype/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllStPenaltyType() {
        Gson gson = new Gson();
        List<SeasonTournamentPenaltyTypeDto> response = penaltyService.getAllStPenaltyType();
        return gson.toJson(response);
    }

    @RequestMapping(path = "/stpenaltytype/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getOneStPenaltyType(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        SeasonTournamentPenaltyTypeDto response = penaltyService.getOneSeasonTournamentPenaltyType(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "{id}/stpenaltytype/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllStPenaltyType(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        List<SeasonTournamentPenaltyTypeDto> response = penaltyService.getAllStPenaltyTypeBySt(id);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/stpenaltytype/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteStPenaltyType(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        penaltyService.deleteStPenaltyType(id);
        return gson.toJson(penaltyService.getAllStPenaltyType());
    }
/////////////////////// SEASON TOURNAMENT PENALTY SETTINGS ////////////////////////

    @RequestMapping(path = "/{stId}/penaltySettings/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addStPenaltySettings(@PathVariable(value = "stId") Integer stId, @RequestBody SeasonTournamentPenaltySettingsDto request) throws AppException {
        if (!stId.equals(request.getSeasonTournamentId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id seasonTournament v URL sa nerovna id SeasonTournament v requeste");
        }
        Gson gson = new Gson();
        SeasonTournamentPenaltySettingsDto response = penaltyService.addStPenaltySettings(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{stId}/stpenaltysetting/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String updateStPenaltySettings(@PathVariable(value = "id") Integer id, @PathVariable(value = "stId") Integer stId, @RequestBody SeasonTournamentPenaltySettingsDto request) throws AppException {
        if (!stId.equals(request.getSeasonTournamentId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id seasonTournament v URL sa nerovna id SeasonTournament v requeste");
        }
        if (!Objects.equals(id, request.getId())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "id v url sa nerovna id v dto");
        }
        Gson gson = new Gson();
        SeasonTournamentPenaltySettingsDto response = penaltyService.editStPenaltySettings(request);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/{stId}/stpenaltysettings/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllStPenaltySettings(@PathVariable(value = "stId") Integer stId) throws AppException {
        Gson gson = new Gson();
        List<SeasonTournamentPenaltySettingsDto> response = penaltyService.getAllStPenaltySettingsBySt(stId);
        return gson.toJson(response);
    }

    @RequestMapping(path = "{stId}/stpenaltysettings/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getOneStPenaltySettings(@PathVariable(value = "id") Integer id) throws AppException {
        Gson gson = new Gson();
        SeasonTournamentPenaltySettingsDto response = penaltyService.getOneSeasonTournamentPenaltySettings(id);
        return gson.toJson(response);
    }

//    @RequestMapping(path = "/stpenaltysettings/byst/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public String getAllStPenaltySettings(@PathVariable(value = "id") Integer id) throws AppException {
//        Gson gson = new Gson();
//        List<SeasonTournamentPenaltySettingsDto> response = penaltyService.getAllStPenaltySettingsBySt(id);
//        return gson.toJson(response);
//    }
    @RequestMapping(path = "{stId}/stpenaltysettings/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteStPenaltySettings(@PathVariable(value = "id") Integer id, @PathVariable(value = "stId") Integer stId) throws AppException {
        Gson gson = new Gson();
        penaltyService.deleteStPenaltySettings(id);
        return gson.toJson(penaltyService.getAllStPenaltySettingsBySt(stId));
    }

}
