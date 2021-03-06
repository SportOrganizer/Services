/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.dal.core.repository.registration;


import com.so.dal.core.model.registration.RegistrationPlayer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author peter
 */
@Repository
public interface RegistrationPlayerRepository extends RegistrationPlayerRepositoryCustom, JpaRepository<RegistrationPlayer, Integer>{
    List<RegistrationPlayer> findByMailAndSurname(String mail, String Surname);
}
