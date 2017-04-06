/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.so.core.controller.converter;

import com.so.core.controller.dto.PersonDTO;
import com.so.core.exception.AppException;
import com.so.dal.core.model.Person;
import com.so.dal.core.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kristián Kačinetz
 */
@Service
public class PersonConverter {
    
    @Autowired
    PersonRepository personRepo;
    
        @Autowired
    private DateConverter dateConverter;
    
        public PersonDTO personEntityToDto(Person entity) {
        PersonDTO dto = new PersonDTO();

        dto.setBirthDate(dateConverter.dateToString(entity.getBirthDate()));
        dto.setId(entity.getId());
        dto.setIsStudent(entity.isIsStudent());
        dto.setMail(entity.getMail());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        dto.setSex(entity.getSex());
        dto.setSurname(entity.getSurname());

        return dto;
    }
        
        public Person dtoToEntity(PersonDTO dto) throws AppException {
        Person entity;

        if (dto.getId() != null) {
            entity = personRepo.findOne(dto.getId());
            if(entity==null){
                throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR,"nenajdena person podla id:"+dto.getId());
            }
        } else {
            entity = new Person();
        }
        entity.setBirthDate(dateConverter.stringToDate(dto.getBirthDate()));
        entity.setMail(dto.getMail());
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setSex(dto.getSex());
        entity.setSurname(dto.getSurname());
        entity.setIsStudent(dto.isIsStudent());

        return entity;
    }
    
}
