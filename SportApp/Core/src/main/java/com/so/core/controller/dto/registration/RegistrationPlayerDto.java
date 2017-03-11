/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.dto.registration;

import java.util.Date;

/**
 *
 * @author peter
 */
public class RegistrationPlayerDto {

    private Integer id;
    private Integer registrationTeam;
    private String name;
    private String surname;
    private Date birthDate;
    private String mail;
    private String phone;
    private Boolean isStudent;
    private Boolean isVerified;
    private String sex;
    private Boolean isProfessional;
    private String note;
    private Integer number;

    public RegistrationPlayerDto() {
    }

    public RegistrationPlayerDto(Integer id, Integer registrationTeam, String name, String surname, Date birthDate, String mail, String phone, Boolean isStudent, Boolean isVerified, String sex, Boolean isProfessional, String note, Integer number) {
        this.id = id;
        this.registrationTeam = registrationTeam;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.mail = mail;
        this.phone = phone;
        this.isStudent = isStudent;
        this.isVerified = isVerified;
        this.sex = sex;
        this.isProfessional = isProfessional;
        this.note = note;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRegistrationTeam() {
        return registrationTeam;
    }

    public void setRegistrationTeam(Integer registrationTeam) {
        this.registrationTeam = registrationTeam;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(Boolean isStudent) {
        this.isStudent = isStudent;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Boolean getIsProfessional() {
        return isProfessional;
    }

    public void setIsProfessional(Boolean isProfessional) {
        this.isProfessional = isProfessional;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
