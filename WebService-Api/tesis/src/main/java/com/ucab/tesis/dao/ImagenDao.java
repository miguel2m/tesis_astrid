/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucab.tesis.dao;

import com.ucab.tesis.dao.ConexionBaseDeDatos;
import com.ucab.tesis.modelo.Imagen;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguelangel
 */
public class ImagenDao {
    private ConexionBaseDeDatos conexion = new ConexionBaseDeDatos();
   
    private Connection conn = conexion.conexionBaseDeDatos();
    private CallableStatement postgresProcedure;
    
    
    public Imagen retornaImagen(
            int _id) {
        conn = conexion.conexionBaseDeDatos();
        Imagen imagen ;
        try {
            imagen = new Imagen();
            postgresProcedure = conn.prepareCall("{call retornaimagen(?)}");

            postgresProcedure.setInt(1, _id);

            
            boolean flag = postgresProcedure.execute();

            if (flag) {
                ResultSet rs = postgresProcedure.getResultSet();

                while (rs.next()) {
                    imagen.setIma_id(rs.getInt(1));
                    imagen.setIma_nombre(rs.getString(2));
                    imagen.setBytes(rs.getBytes(3));
                    imagen.setIma_categoria(rs.getString(4));
                    imagen.setIma_respuesta_correcta(rs.getString(5));
                }
                
                rs.close();
            }

            postgresProcedure.close();

        } catch (SQLException ex) {
            System.out.println("ERROR: PROCEDURE IMAGEN " + ex.getMessage().toString());;
            imagen = null;

        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImagenDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return imagen;
    }
}
