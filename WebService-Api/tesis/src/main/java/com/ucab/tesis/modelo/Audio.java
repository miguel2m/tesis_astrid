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
public class Audio {
    private int au_id;
    private String au_nombre;
    private byte[] au_bytes;
    private String au_categoria;
    private String au_respuesta_correcta;

    public int getAu_id() {
        return au_id;
    }

    public void setAu_id(int au_id) {
        this.au_id = au_id;
    }

    public String getAu_nombre() {
        return au_nombre;
    }

    public void setAu_nombre(String au_nombre) {
        this.au_nombre = au_nombre;
    }

    public byte[] getAu_bytes() {
        return au_bytes;
    }

    public void setAu_bytes(byte[] au_bytes) {
        this.au_bytes = au_bytes;
    }

    public String getAu_categoria() {
        return au_categoria;
    }

    public void setAu_categoria(String au_categoria) {
        this.au_categoria = au_categoria;
    }

    public String getAu_respuesta_correcta() {
        return au_respuesta_correcta;
    }

    public void setAu_respuesta_correcta(String au_respuesta_correcta) {
        this.au_respuesta_correcta = au_respuesta_correcta;
    }
    
    
}
