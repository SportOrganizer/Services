package com.so.controller.webservices;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndex() {
        return "It works!";
    }

}
