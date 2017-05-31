/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.floorball.controller.webservices;

import com.google.gson.Gson;
import com.so.floorball.controller.floorballGame.dto.RequestSportFloorballGameActivityDto;
import com.so.floorball.controller.helloo.Greeting;
import com.so.floorball.controller.helloo.HelloMessage;
import com.so.floorball.services.SportFloorballGameActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kristián Kačinetz
 */
@Controller
public class WebSocketController {
    
    @Autowired
    SportFloorballGameActivityService activityService;
    
    @MessageMapping("/hello")
    @SendTo("/topic/messages")
    public String send(@RequestBody RequestSportFloorballGameActivityDto request) throws Exception {
        Gson gson = new Gson();
        activityService.addSportFloorballGameActivity(request);
        return gson.toJson(request);
    }
//        public Greeting greeting(HelloMessage message) throws Exception {
//        Thread.sleep(1000); // simulated delay
//        return new Greeting("Hello, " + message.getName() + "!");
//}
        
//        

    
}
