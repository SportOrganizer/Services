package com.so.core.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.so.core.controller", "com.so.core.main","com.so.core.services"})
//@ComponentScan("com.so.core")
public class AppConfig  {


}