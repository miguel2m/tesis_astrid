/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucab.tesis.modelo;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguelangel
 */
public class Usuario {
    private int us_id =-1;
    private String us_nombre_usuario;
    private String us_nombre;
    private String us_apellido;
    private String us_fecha_nacimiento;
    private char us_genero;
    private String us_email;
    private String us_password;
    private int us_nivel_juego = 0;
    
    //COnstructor Vacio
    public Usuario (){
    
    }
    
    // Constructor Para instanciar con todos los Atributos
    public Usuario(int us_id, String us_nombre_usuario, String us_nombre, String us_apellido, String us_fecha_nacimiento, char us_genero, String us_email, String us_password, int us_nivel_juego) {
        this.us_id = us_id;
        this.us_nombre_usuario = us_nombre_usuario;
        this.us_nombre = us_nombre;
        this.us_apellido = us_apellido;
        this.us_fecha_nacimiento = us_fecha_nacimiento;
        this.us_genero = us_genero;
        this.us_email = us_email;
        this.us_password = us_password;
        this.us_nivel_juego = us_nivel_juego;
    }
    
    //Constructor para instanciar con todos los atributos sin el US_ID
    public Usuario(String us_nombre_usuario, String us_nombre, String us_apellido, String us_fecha_nacimiento, char us_genero, String us_email, String us_password, int us_nivel_juego) {
        this.us_nombre_usuario = us_nombre_usuario;
        this.us_nombre = us_nombre;
        this.us_apellido = us_apellido;
        this.us_fecha_nacimiento = us_fecha_nacimiento;
        this.us_genero = us_genero;
        this.us_email = us_email;
        this.us_password = us_password;
        this.us_nivel_juego = us_nivel_juego;
    }

    public int getUs_id() {
        return us_id;
    }

    public void setUs_id(int us_id) {
        this.us_id = us_id;
    }

    public String getUs_nombre_usuario() {
        return us_nombre_usuario;
    }

    public void setUs_nombre_usuario(String us_nombre_usuario) {
        this.us_nombre_usuario = us_nombre_usuario;
    }

    public String getUs_nombre() {
        return us_nombre;
    }

    public void setUs_nombre(String us_nombre) {
        this.us_nombre = us_nombre;
    }

    public String getUs_apellido() {
        return us_apellido;
    }

    public void setUs_apellido(String us_apellido) {
        
        this.us_apellido = us_apellido;
    }

    public String getUs_fecha_nacimiento() {
        return us_fecha_nacimiento;
    }

    public void setUs_fecha_nacimiento(String us_fecha_nacimiento) {
        
         this.us_fecha_nacimiento = us_fecha_nacimiento;
        
    }

    public char getUs_genero() {
        return us_genero;
    }

    public void setUs_genero(char us_genero) {
        this.us_genero = us_genero;
    }

    public String getUs_email() {
        return us_email;
    }

    public void setUs_email(String us_email) {
        this.us_email = us_email;
    }

    public String getUs_password() {
        return us_password;
    }

    public void setUs_password(String us_password) {
        this.us_password = us_password;
    }

    public int getUs_nivel_juego() {
        return us_nivel_juego;
    }

    public void setUs_nivel_juego(int us_nivel_juego) {
        this.us_nivel_juego = us_nivel_juego;
    }
    
    
    
    
}

