/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.document;

import com.so.core.controller.dto.ResourceDto;
import com.so.core.exception.AppException;
import com.so.dal.core.model.Resource;
import com.so.dal.core.repository.ResourceRepository;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author peter
 */
@Service
public class DocumentService {

    private final static Logger LOG = LoggerFactory.getLogger(DocumentService.class);
    //private final String PATH = "/opt/glassfish4/glassfish/domains/domain1/applications/resources/logos/";
     private final String PATH = "C:\\Users\\peter\\Documents\\foto\\";
    @Autowired
    private ResourceRepository resourceRepo;

    @Transactional
    public Resource createFile(byte[] data, String mimeType) throws AppException {
        try {
            LOG.debug("createFile(data,mimeType:{})", mimeType);
            String nameOfFile = UUID.randomUUID().toString() + "." + mimeType;
            Path destinationFile = Paths.get(PATH, nameOfFile);
            Files.write(destinationFile, data);
            Resource r = resourceRepo.saveAndFlush(new Resource(nameOfFile));

            if (r == null) {
                LOG.error("Nepodarilo sa ulozit zaznam resourcu={} do databazy", destinationFile.toString());
                throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "do databazy sa neulozil zaznam resourcu");
            }
            return r;
        } catch (FileNotFoundException ex) {
            throw new AppException(HttpStatus.NOT_FOUND, "nepodarilo sa vytvorit subor:" + ex);
        } catch (IOException ex) {
            throw new AppException(HttpStatus.NOT_FOUND, "nepodarilo sa vytvorit subor:" + ex);
        }
    }

    public ResourceDto uploadImage(ResourceDto r) throws AppException {
        Resource image = createFile(r.getData(), r.getMimeType());
        return new ResourceDto(image.getId(), image.getPath());
    }

    @Transactional
    public void deleteFile(Resource r) throws AppException {

        if (r != null) {
            File file = new File(PATH + r.getPath());
            if (file.exists()) {
                if (file.delete()) {
                    resourceRepo.delete(r);
                    LOG.info(file.getName() + " subor je vymazany!");
                } else {
                    LOG.error("Delete operation is failed.");
                    throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "nepodarilo sa vymazat resource");
                }
            } else{
                LOG.info("subor {} neexistuje, ale resource bol vymazany",r.getPath());
            }
        } else {
            LOG.info("resource je null");
        }
    }

    public void deleteFile(String name) throws AppException {
        deleteFile(resourceRepo.findByPath(name));
    }

    public ResourceDto getImage(String name) throws AppException {

        ResourceDto r = new ResourceDto();
        String filePath = PATH + name;
        try {
            File file = new File(filePath);
            if(!file.exists()){
                throw new AppException(HttpStatus.NOT_FOUND,"image:"+name+" nebol najdeny");
            }
            
            FileInputStream fis = new FileInputStream(file);
            byte[] fileBytes;
            try (BufferedInputStream inputStream = new BufferedInputStream(fis)) {
                fileBytes = new byte[(int) file.length()];
                inputStream.read(fileBytes);
            }
            r.setData(fileBytes);
            r.setPath(name);
            return r;
        } catch (FileNotFoundException ex) {
            LOG.error("nepodarilo sa nacitat subor={}", name);
            throw new AppException(HttpStatus.NOT_FOUND, "nepodarilo sa nacitat subor:" + "name " + ex.getMessage());
        } catch (IOException e) {
            LOG.error("nepodarilo sa nacitat subor={}", name);
            throw new AppException(HttpStatus.NOT_FOUND, "nepodarilo sa nacitat subor:" + "name " + e.getMessage());

        }

    }
}
