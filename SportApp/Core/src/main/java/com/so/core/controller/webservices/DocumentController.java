/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.webservices;

import com.google.gson.Gson;
import com.so.core.controller.dto.ResourceDto;
import com.so.core.exception.AppException;
import com.so.core.services.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(path = "/{name:.+}", method = RequestMethod.GET, produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] getImage(@PathVariable(value = "name") String name) throws AppException {
        ResourceDto r = service.getImage(name);
        return r.getData();
    }

    @RequestMapping(path = "/{name:.+}", method = RequestMethod.DELETE)
    public void deleteImage(@PathVariable(value = "name") String name) throws AppException {
        service.deleteFile(name);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String uploadImage(@RequestBody ResourceDto dto) throws AppException {
        Gson gson = new Gson();
        ResourceDto response = service.uploadImage(dto);
        return gson.toJson(response);
    }

    @RequestMapping(path = "/", method = RequestMethod.DELETE,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String deleteNotUsedResources() throws AppException {
        Gson gson = new Gson();
        service.deleteNotUsedResources();
        return gson.toJson("OK");
    }

}
