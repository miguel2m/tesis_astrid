/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucab.tesis.dao;

import com.ucab.tesis.modelo.Audio;
import com.ucab.tesis.modelo.Imagen;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguelangel
 */
public class AudioDao {
    private ConexionBaseDeDatos conexion = new ConexionBaseDeDatos();
   
    private Connection conn = conexion.conexionBaseDeDatos();
    private CallableStatement postgresProcedure;
    
    
    public Audio retornaAudio(
            int _id) {
        conn = conexion.conexionBaseDeDatos();
        Audio audio ;
        try {
            audio = new Audio();
            postgresProcedure = conn.prepareCall("{call retornaaudio(?)}");

            postgresProcedure.setInt(1, _id);

            
            boolean flag = postgresProcedure.execute();

            if (flag) {
                ResultSet rs = postgresProcedure.getResultSet();

                while (rs.next()) {
                    audio.setAu_id(rs.getInt(1));
                    audio.setAu_nombre(rs.getString(2));
                    audio.setAu_bytes(rs.getBytes(3));
                    audio.setAu_categoria(rs.getString(4));
                    audio.setAu_respuesta_correcta(rs.getString(5));
                }
                
                rs.close();
            }

            postgresProcedure.close();

        } catch (SQLException ex) {
            System.out.println("ERROR: PROCEDURE IMAGEN " + ex.getMessage().toString());;
            audio = null;

        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImagenDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return audio;
    }
}
