/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.document;

import com.so.dal.core.model.Resource;
import com.so.dal.core.repository.ResourceRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;
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

    @Autowired
    private ResourceRepository resourceRepo;

    @Transactional
    public Resource createFile(String data, String mimeType) throws IOException {
        LOG.debug("data={}", data);
        String  path =  "/opt/glassfish4/glassfish/domains/domain1/applications/resources/logos";
        //String path = "C:\\Users\\peter\\Documents\\Timak_master\\target\\resources";
        byte[] decodedImg = Base64.getDecoder().decode(data.getBytes());
        Path destinationFile = Paths.get(path, UUID.randomUUID().toString() + "." + mimeType);
        Files.write(destinationFile, decodedImg);
        Resource r = resourceRepo.saveAndFlush(new Resource(destinationFile.toString()));

        if (r == null) {
            LOG.error(" zaznam resourcu={} sa nepodarilo ulozit do dbs ", destinationFile.toString());
            throw new IllegalStateException("do databazy sa neulozil zaznam resourcu");
        }
        return r;
    }

    public void deleteFile(Resource r) {

        if (r != null) {
            File file = new File(r.getPath());

            if (file.delete()) {
                LOG.info(file.getName() + " is deleted!");
            } else {
                LOG.error("Delete operation is failed.");
                throw new IllegalStateException("nepodarilo sa vymazat resource");
            }
        }

    }
    
    
}
