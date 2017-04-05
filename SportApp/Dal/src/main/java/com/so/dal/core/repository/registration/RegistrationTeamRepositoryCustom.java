/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.core.repository.registration;

/**
 *
 * @author peter
 */
public interface RegistrationTeamRepositoryCustom {
    Boolean ifContainName(Integer stId, String regTeamName);
    Boolean ifUniqeName(Integer stId,Integer regTeamId,String regTeamName);
    
}
