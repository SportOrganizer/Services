/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.core.repository;

import com.so.dal.core.repository.customDto.PathsToLogos;
import java.util.List;

/**
 *
 * @author peter
 */
public interface TeamRepositoryCustom {
    public List<PathsToLogos> getPathsToLogos();
    public List<PathsToLogos> getPathsToLogos2();

    
}
