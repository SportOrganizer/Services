/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.webservices;

import com.google.gson.Gson;
import com.so.core.controller.dto.registration.RegistrationTeamDto;
import com.so.core.services.RegistrationService;
import com.so.dal.core.model.registration.RegistrationTeam;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author peter
 */
@RestController
@CrossOrigin
@RequestMapping("/registration")
public class RegistrationController {
    
    @Autowired
    RegistrationService service;
    
     @RequestMapping(path = "/", method = RequestMethod.POST)
    public String createSeason(@RequestBody RegistrationTeamDto regTeam ){
         Gson gson = new Gson();
          RegistrationTeam response = new RegistrationTeam();
        try {
             response = service.registrationTeam(regTeam);
        } catch (Exception ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
            // vyriesit co robit ak exception
        }
      
    return gson.toJson(response);
    }
    
}
