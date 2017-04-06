/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.so.core.services.season.enums;

/**
 *
 * @author peter
 */
public enum PeriodType {

    PRVY_POLCAS("1. polčas"),
    DRUHY_POLCAS("2. polčas"),
    PRVA_TRETINA("1. tretina"),
    DRUHA_TRETINA("2. tretina"),
    TRETIA_TRETINA("š. tretina"),
    CAST("časť");

    private final String nazov;

    PeriodType(String nazov) {
        this.nazov = nazov;
    }

    public String getNazov() {
        return this.nazov;
    }

}
