package com.so.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Created by janpolacek on 12/26/16.
 */

@RestController

@RequestMapping("/team")
public class TeamController {

    @GetMapping
    public String get() {
        return "Get teams";
    }

    //com
    @GetMapping("/{teamId}")
    public String getForDay(@PathVariable  String teamId) {
        return "Get team" +  teamId;
    }
}
