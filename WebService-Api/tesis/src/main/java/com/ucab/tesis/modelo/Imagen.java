/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucab.tesis.modelo;

/**
 *
 * @author Miguelangel
 */
public class Imagen {
    private int ima_id;
    private String ima_nombre;
    private byte[] bytes;
    private String ima_categoria;
    private String ima_respuesta_correcta;
    
    public int getIma_id() {
        return ima_id;
    }

    public void setIma_id(int ima_id) {
        this.ima_id = ima_id;
    }

    public String getIma_nombre() {
        return ima_nombre;
    }

    public void setIma_nombre(String ima_nombre) {
        this.ima_nombre = ima_nombre;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getIma_categoria() {
        return ima_categoria;
    }

    public void setIma_categoria(String ima_categoria) {
        this.ima_categoria = ima_categoria;
    }

    public String getIma_respuesta_correcta() {
        return ima_respuesta_correcta;
    }

    public void setIma_respuesta_correcta(String ima_respuesta_correcta) {
        this.ima_respuesta_correcta = ima_respuesta_correcta;
    }
    
}
