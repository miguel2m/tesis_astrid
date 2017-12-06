/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucab.tesis.dao;

import com.ucab.tesis.modelo.Usuario;
import java.sql.Connection;
import java.sql.CallableStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguelangel
 */
public class UsuarioDao {

    private ConexionBaseDeDatos conexion = new ConexionBaseDeDatos();
    private Usuario usuario = new Usuario();
    private Connection conn = conexion.conexionBaseDeDatos();
    private CallableStatement postgresProcedure;

    public int crearUsuario(
            String _nombreUsuario,
            String _nombre,
            String _apellido,
            String _fechaNacimiento,
            char _genero,
            String _correo,
            String _clave) {
        conn = conexion.conexionBaseDeDatos();
        int resultado = -3;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsed = format.parse(_fechaNacimiento);
            java.sql.Date fechaNacimiento = new java.sql.Date(parsed.getTime());
            postgresProcedure = conn.prepareCall("{call insertarusuario(?,?,?,?,?,?,?)}");
            postgresProcedure.setString(1, _nombreUsuario);
            postgresProcedure.setString(2, _nombre);
            postgresProcedure.setString(3, _apellido);
            postgresProcedure.setDate(4, fechaNacimiento);
            postgresProcedure.setString(5, String.valueOf(_genero));
            postgresProcedure.setString(6, _correo);
            postgresProcedure.setString(7, _clave);
            postgresProcedure.registerOutParameter(1, Types.INTEGER);
            postgresProcedure.execute();

            resultado = postgresProcedure.getInt(1);

            postgresProcedure.close();

        } catch (SQLException ex) {
            System.out.println("ERROR: PROCEDURE CREAR " + ex.getMessage().toString());;
            resultado= -5;
        } catch (ParseException ex) {
            System.out.println("ERROR: CONVERTIDOR FECHA " + ex.getMessage().toString());;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return resultado;
    }

    public int eliminarUsuario(int id_user) {
        conn = conexion.conexionBaseDeDatos();
        int resultado = -1;
        try {
            postgresProcedure = conn.prepareCall("{call eliminarusuarioporid(?)}");
            postgresProcedure.setInt(1, id_user);
            postgresProcedure.executeUpdate();
            postgresProcedure.close();
            resultado = 1;
        } catch (SQLException ex) {
            System.out.println("ERROR: PROCEDURE ELIMINAR " + ex.getMessage().toString());;

        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return resultado;

    }

    public int actualizarUsuario(
            int _id,
            String _nombreUsuario,
            String _nombre,
            String _apellido,
            String _fechaNacimiento,
            String _correo,
            String _clave) {
        conn = conexion.conexionBaseDeDatos();
        int resultado = -3;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsed = format.parse(_fechaNacimiento);
            java.sql.Date fechaNacimiento = new java.sql.Date(parsed.getTime());
            postgresProcedure = conn.prepareCall("{call actualizarusuario(?,?,?,?,?,?,?)}");
            postgresProcedure.setInt(1, _id);
            postgresProcedure.setString(2, _nombreUsuario);
            postgresProcedure.setString(3, _nombre);
            postgresProcedure.setString(4, _apellido);
            postgresProcedure.setDate(5, fechaNacimiento);

            postgresProcedure.setString(6, _correo);
            postgresProcedure.setString(7, _clave);

            postgresProcedure.executeUpdate();

            resultado = 1;

            postgresProcedure.close();

        } catch (SQLException ex) {
            System.out.println("ERROR: PROCEDURE CREAR " + ex.getMessage().toString());;

        } catch (ParseException ex) {
            System.out.println("ERROR: CONVERTIDOR FECHA " + ex.getMessage().toString());;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return resultado;
    }

    public Usuario loginUsuario(
            String _nombreUsuario,
            String _clave) {
        conn = conexion.conexionBaseDeDatos();
        Usuario user;
        try {
            user = new Usuario();
            postgresProcedure = conn.prepareCall("{call loginusuario(?,?)}");

            postgresProcedure.setString(1, _nombreUsuario);
            postgresProcedure.setString(2, _clave);

            
            boolean flag = postgresProcedure.execute();

            if (flag) {
                ResultSet rs = postgresProcedure.getResultSet();

                while (rs.next()) {
                    user.setUs_id(rs.getInt(1));
                    user.setUs_nombre_usuario(rs.getString(2));
                    user.setUs_nombre(rs.getString(3));
                    user.setUs_apellido(rs.getString(4));

                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    String fecha_nacimiento = format.format(rs.getDate(5));
                    user.setUs_fecha_nacimiento(fecha_nacimiento);

                    String genero = rs.getString(6);
                    char _genero = genero.charAt(0);
                    user.setUs_genero(_genero);

                    user.setUs_email(rs.getString(7));
                    user.setUs_password(rs.getString(8));
                    user.setUs_nivel_juego(rs.getInt(9));
                }
                
                rs.close();
            }

            postgresProcedure.close();

        } catch (SQLException ex) {
            System.out.println("ERROR: PROCEDURE LOGIN " + ex.getMessage().toString());;
            user = null;

        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return user;
    }
    
    public Usuario recuperarContrasenaUsuario(
            String _nombreUsuario,
            String _correo) {
        conn = conexion.conexionBaseDeDatos();
         Usuario _password = null;
        try {
            _password = new Usuario();
            postgresProcedure = conn.prepareCall("{call recuperarcontrasena(?,?)}");
            
            postgresProcedure.setString(1, _nombreUsuario);
            postgresProcedure.setString(2, _correo);

            
            boolean flag = postgresProcedure.execute();

            
            if (flag) {
                ResultSet rs = postgresProcedure.getResultSet();

                while (rs.next()) {

                    _password.setUs_password(rs.getString(1));

                }

                rs.close();
            }
        

            postgresProcedure.close();

        } catch (SQLException ex) {
            System.out.println("ERROR: PROCEDURE RECUPERAR CONTRASENA " + ex.getMessage().toString());;
            _password = null;

        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return _password;
    }
    
    
    public void aumentarNivelUsuario(
            int _id) {
        conn = conexion.conexionBaseDeDatos();
      
        try {
            
            postgresProcedure = conn.prepareCall("{call aumentarnivelusuario(?)}");
            
            postgresProcedure.setInt(1, _id);
            

            
            postgresProcedure.executeUpdate();

            
            
        

            postgresProcedure.close();

        } catch (SQLException ex) {
            System.out.println("ERROR: PROCEDURE RECUPERAR CONTRASENA " + ex.getMessage().toString());;
            

        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
}
