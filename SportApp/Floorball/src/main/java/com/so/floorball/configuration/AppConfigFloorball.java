package com.so.floorball.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages ={"com.so.floorball.main","com.so.floorball.controller","com.so.floorball.services"})
public class AppConfigFloorball  {


}