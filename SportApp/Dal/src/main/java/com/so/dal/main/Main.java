/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.main;

import com.so.dal.configuration.AppConfigDal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author peter
 */
public class Main {
    
    public static void main(String[] args) {
        ApplicationContext ctx
                = new AnnotationConfigApplicationContext(AppConfigDal.class);

    

    }

    
}
