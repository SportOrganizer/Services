/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.floorball.controller.webservices;

import com.google.gson.Gson;
import com.so.floorball.controller.floorballGame.dto.RequestSportFloorballGameActivityDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kristián Kačinetz
 */
@Controller
public class WebSocketController {
    
    @MessageMapping("/hello")
    @SendTo("/topic/messages")
    public String send(RequestSportFloorballGameActivityDto request) throws Exception {
        Gson gson = new Gson();
        return gson.toJson(request);
    }
    
}