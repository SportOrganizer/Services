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

    private String name;
    private String surname;
    private Date birthDate;
    private String mail;
    private String phone;
    private Boolean isStudent;
    private String sex;
    private Boolean isProfessional;
    private String note;
    private Integer number;

    public RegistrationPlayerDto() {
    }

    public RegistrationPlayerDto(String name, String surname, Date birthDate, String mail, String phone, Boolean isStudent, String sex, Boolean isProfessional, String note, int number) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.mail = mail;
        this.phone = phone;
        this.isStudent = isStudent;
        this.sex = sex;
        this.isProfessional = isProfessional;
        this.note = note;
        this.number = number;
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
