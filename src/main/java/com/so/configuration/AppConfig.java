package com.so.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.so.controller", "com.so.dal","com.so.main", "com.so.dal.model", "com.so.dal.repository","com.so.services"})
public class AppConfig  {


}