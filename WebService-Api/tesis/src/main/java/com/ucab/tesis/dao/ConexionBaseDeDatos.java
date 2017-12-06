/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucab.tesis.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguelangel
 */
public class ConexionBaseDeDatos {
    private Connection conexion =null;
    
    public Connection conexionBaseDeDatos() 
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/tesis_astrid";
            this.conexion = DriverManager.getConnection(url,"postgres","1234"); 
           // conexion.setAutoCommit(true);
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Error Class NOT FOUND "+e.getMessage().toString() );
        } 
        catch (SQLException e) 
        {
            System.out.println("ERROR Conectar Base de datos"+e.getMessage().toString());
        }
        
        return  conexion;
    }
}
