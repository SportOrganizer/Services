/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller;

import com.google.gson.Gson;
import com.so.core.controller.dto.ResourceDto;
import com.so.core.services.document.DocumentService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author peter
 */
@RestController
@CrossOrigin
@RequestMapping("/document")
public class DocumentController {
    
    @Autowired
    DocumentService service;
    
      @RequestMapping(path = "/{name:.+}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable(value="name")String name){
     Gson gson = new Gson();
        
        ResourceDto r = new ResourceDto();
        try {
            r = service.getImage(name);
        } catch (IOException ex) {
            Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
      return r.getData();
    }
    
}
