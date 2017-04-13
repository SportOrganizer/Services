/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.converter;

import com.so.core.exception.AppException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author peter
 */
@Service
public class DateConverter {

    public Date stringToDateTime(String stringDate) throws AppException {
        if (stringDate == null) {
            return null;
        }

        DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        try {
            date = format.parse(stringDate);
        } catch (ParseException ex) {
            throw new AppException(HttpStatus.BAD_REQUEST, "chyba parsovania datumu");
        }
        return date;
    }

    public String dateTimeToString(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String stringDate = format.format(date);
        return stringDate;
    }

    public Date stringToDate(String stringDate) throws AppException {
        if (stringDate == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        try {
            date = format.parse(stringDate);
        } catch (ParseException ex) {
            throw new AppException(HttpStatus.BAD_REQUEST, "chyba parsovania datumu");
        }
        return date;
    }

    public String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String stringDate = format.format(date);
        return stringDate;
    }
    
    public String timeToString(Date time){
         if (time == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        String stringDate = format.format(time);
        return stringDate;
    }
    
    public Date stringToTime(String timeString) throws AppException{
           if (timeString == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        try {
            date = format.parse(timeString);
        } catch (ParseException ex) {
            throw new AppException(HttpStatus.BAD_REQUEST, "chyba parsovania datumu");
        }
        return date;
    }

}
