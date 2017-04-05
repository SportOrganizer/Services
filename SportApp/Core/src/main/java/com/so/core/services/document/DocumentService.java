/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.document;

import com.so.core.controller.dto.ResourceDto;
import com.so.dal.core.model.Resource;
import com.so.dal.core.repository.ResourceRepository;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import javax.xml.ws.WebServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author peter
 */
@Service
public class DocumentService {

    private final static Logger LOG = LoggerFactory.getLogger(DocumentService.class);
    private final String PATH = "/opt/glassfish4/glassfish/domains/domain1/applications/resources/logos/";
 //   private final String PATH = "C:\\Users\\Kristián Kačinetz\\resourcesTimak";
    @Autowired
    private ResourceRepository resourceRepo;

    @Transactional
    public Resource createFile(byte[] data, String mimeType) throws IOException {
        LOG.debug("createFile(data,mimeType:{})", mimeType);
        String nameOfFile = UUID.randomUUID().toString() + "." + mimeType;
        Path destinationFile = Paths.get(PATH, nameOfFile);
        Files.write(destinationFile, data);
        Resource r = resourceRepo.saveAndFlush(new Resource(nameOfFile));

        if (r == null) {
            LOG.error(" zaznam resourcu={} sa nepodarilo ulozit do dbs ", destinationFile.toString());
            throw new IllegalStateException("do databazy sa neulozil zaznam resourcu");
        }
        return r;
    }

    @Transactional
    public void deleteFile(Resource r) {

        if (r != null) {
            File file = new File(PATH +"\\"+ r.getPath());

            if (file.delete()) {
                resourceRepo.delete(r);
                LOG.info(file.getName() + " is deleted!");
            } else {
                LOG.error("Delete operation is failed.");
                //throw new IllegalStateException("nepodarilo sa vymazat resource");
            }
        }
    }
    
    public ResourceDto getImage(String name) throws IOException{
        
        
     //  File f = new File(PATH+"\\"+name);
       ResourceDto r =  new ResourceDto();
         String filePath = PATH + name;
   //     System.out.println("Sending file: " + filePath);
         
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream inputStream = new BufferedInputStream(fis);
            byte[] fileBytes = new byte[(int) file.length()];
            inputStream.read(fileBytes);
            inputStream.close();
            r.setData(fileBytes);
            r.setPath(name);
            return r;
        } catch (IOException ex) {
            System.err.println(ex);
            throw new WebServiceException(ex);
        }       

      
    }
}
