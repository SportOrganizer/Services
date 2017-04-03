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
public interface RegistrationPlayerRepositoryCustom {
    Boolean ifUniqueNumber(Integer regTeamId, Integer regPlayerId, Integer number);
    Boolean ifUniqueCaptain(Integer regTeamId, Integer regPlayerId, Boolean ifCaptain);
    Boolean ifUniqueEmail(Integer regTeamId, Integer regPlayerId, String email);
}
