/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.season;

import com.so.core.controller.converter.SeasonTournamentSettingsConverter;
import com.so.core.controller.dto.season.SettingDto;
import com.so.core.controller.dto.season.SettingsDto;
import com.so.core.exception.AppException;
import com.so.core.services.enums.Settings;
import com.so.dal.core.model.season.SeasonTournament;
import com.so.dal.core.model.season.SeasonTournamentSettings;
import com.so.dal.core.model.season.SeasonTournamentSettingsType;
import com.so.dal.core.repository.season.SeasonTournamentRepository;
import com.so.dal.core.repository.season.SeasonTournamentSettingsRepository;
import com.so.dal.core.repository.season.SeasonTournamentSettingsTypeRepository;
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
 * @author Kristian
 */
@Service
public class SeasonTournamentSettingsService {

    private final static Logger LOG = LoggerFactory.getLogger(SeasonTournamentSettingsService.class); /// logovanie..

    @Autowired
    SeasonTournamentSettingsRepository stSettingsRepo;

    @Autowired
    SeasonTournamentSettingsTypeRepository stSettingsTypeRepo;

    @Autowired
    SeasonTournamentSettingsConverter seasonTournamentConv;

    @Autowired
    SeasonTournamentRepository seasonTournamentRep;

    public SettingsDto getAllSettingOptions() {
        SettingsDto response = new SettingsDto();
        List<SettingDto> l = new ArrayList<>();

        Settings[] settings = Settings.values();
        for (Settings set : settings) {
            SettingDto s = new SettingDto();
            s.setName(set.name());
            //    s.setId(set.getId());
            if (set.getOptions() != null) {
                s.setOptions(set.getOptions());
            }
            l.add(s);
        }
        response.setSettings(l);
        return response;
    }

    @Transactional
    public SettingsDto saveSettings(SettingsDto settings) throws AppException {
        LOG.info("saveSettings()");
        SeasonTournament st = seasonTournamentRep.getOne(settings.getSeasonTournamentId());

        if (settings.getSettings() != null) {
            for (SettingDto s : settings.getSettings()) {
                if ( s.getName() == null || s.getValue() == null) {
                    throw new AppException(HttpStatus.BAD_REQUEST, "nevyplnene povinne parametre");
                }
                SeasonTournamentSettingsType type = stSettingsTypeRepo.findByName(s.getName());
                if (type == null) {
                    throw new AppException(HttpStatus.NOT_FOUND, "nenajdeny st settings type s id" + s.getId());
                }
                SeasonTournamentSettings set = new SeasonTournamentSettings(st, type, s.getName(), s.getValue());
                set = stSettingsRepo.saveAndFlush(set);
                if (set == null) {
                    throw new AppException(HttpStatus.BAD_REQUEST, "nepodarilo sa ulozit settings do databazy");
                }
            }
        }

        return settings;
    }

    @Transactional
    public SettingsDto getSettingsBySt(Integer stId) throws AppException {
        LOG.info("getSettingsBySt({})", stId);
        SeasonTournament st = seasonTournamentRep.findOne(stId);
        if (st == null) {
            throw new AppException(HttpStatus.NOT_FOUND, "nenasiel sa season tournament s id:" + stId);
        }
        List<SeasonTournamentSettings> settings = stSettingsRepo.findBySeasonTournament(st);
        SettingsDto response = seasonTournamentConv.entityToDto(settings);
        response.setSeasonTournamentId(stId);

        return response;
    }

    @Transactional
    public String getSettingBySt(Settings setting, Integer stId) throws AppException {
        LOG.info("getSettingBySt({},{})", setting.name(), stId);
        SeasonTournament st = seasonTournamentRep.findOne(stId);
        if (st == null) {
            throw new AppException(HttpStatus.NOT_FOUND, "nenasiel sa season tournament s id:" + stId);
        }

        SeasonTournamentSettings set = stSettingsRepo.findBySeasonTournamentAndName(st, setting.name());
        if (set == null) {
            throw new AppException(HttpStatus.NOT_FOUND, "nenajdeny settings pre stId a name ->" + stId + " " + setting.name());
        }

        return set.getValue();
    }

    @Transactional
    public SettingDto updateSetting(SettingDto updatedSetting) throws AppException {

        SeasonTournamentSettings set = seasonTournamentConv.dtoToEntity(updatedSetting);

        set = stSettingsRepo.saveAndFlush(set);

        if (set == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "nepodarilo sa ulozit settings do databazy");
        }

        return seasonTournamentConv.entityToDto(set);
    }

    public Settings getSettingByName(String name) throws AppException {
        Settings[] settings = Settings.values();
        name = name.toUpperCase();
        for (Settings set : settings) {
            if (set.name().equals(name)) {
                return set;
            }
        }
        throw new AppException(HttpStatus.NOT_FOUND, "nenajdene settings " + name);
    }
    
        public Settings getSetting(Settings setting) throws AppException {
        Settings[] settings = Settings.values();
        String name = setting.name();
        name = name.toUpperCase();
        for (Settings set : settings) {
            if (set.name().equals(name)) {
                return set;
            }
        }
        throw new AppException(HttpStatus.NOT_FOUND, "nenajdene settings " + name);
    }
}
