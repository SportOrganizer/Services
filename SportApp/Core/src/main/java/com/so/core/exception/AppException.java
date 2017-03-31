/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.exception;

import org.springframework.http.HttpStatus;

/**
 *
 * @author peter
 */
public class AppException extends Exception {

    private static final long serialVersionUID = -8999932578270387947L;

    private HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }

    public AppException() {

    }

    public AppException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    
    

}
