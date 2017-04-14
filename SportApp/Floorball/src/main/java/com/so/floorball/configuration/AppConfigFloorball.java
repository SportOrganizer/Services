package com.so.floorball.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages ={"com.so.floorball",
    "com.so.dal", "com.so.core.exception",  "com.so.core.controller.dto",
      "com.so.core.services","com.so.core.controller.converter"})
public class AppConfigFloorball  {


}