/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.document;

import com.so.dal.core.model.Resource;
import com.so.dal.core.repository.ResourceRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
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
    public Resource createFile(String data, String mimeType, String path, String name) throws IOException {
        LOG.debug("data={}", data);
        byte[] decodedImg = Base64.getDecoder().decode(data.getBytes());
        Path destinationFile = Paths.get(path, name + "_logo." + mimeType);
        Files.write(destinationFile, decodedImg);
        Resource r = resourceRepo.saveAndFlush(new Resource(destinationFile.toString()));

        if (r == null) {
            LOG.error(" zaznam resourcu={} sa nepodarilo ulozit do dbs ", destinationFile.toString());
            throw new IllegalStateException("do databazy sa neulozil zaznam resourcu");
        }
        return r;
    }
}
