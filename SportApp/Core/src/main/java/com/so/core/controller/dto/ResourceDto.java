/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.controller.dto;

/**
 *
 * @author peter
 */
public class ResourceDto {

    private Integer id;
    private byte[] data;
    private String mimeType;
    private String path;

    public ResourceDto() {
    }

    public ResourceDto(Integer id, String path) {
        this.id = id;
        this.path = path;
    }

    
    public ResourceDto(Integer id, byte[] data, String mimeType, String path) {
        this.data = data;
        this.mimeType = mimeType;
        this.path = path;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
