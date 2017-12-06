/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucab.tesis.servicio;


import com.ucab.tesis.dao.UsuarioDao;
import com.ucab.tesis.modelo.Usuario;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Miguelangel
 */
@Path("usuarios")
public class UsuarioServicio {
    private UsuarioDao dao = new UsuarioDao();
            
    
    /**
     * Metodo que se encarga de iniciar sesion
     * @param _user
     * @param _clave
     * @return Datos Usuarios
     * Si la persona no existe entonces devulve el usuario
     * con datos vacios (null)
     * 
     * Si existe un problema retorna un objeto null
     */ 
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUsuario(Usuario user)
    {
        return Response.status(Response.Status.FOUND).entity(
                dao.loginUsuario(
                        user.getUs_nombre_usuario(), 
                        user.getUs_password())
        ).build();
    }
    
   
    
    /**
     * Metodo que inserta un usuario a la Base  de datos
     * @param user
     * @return Usuario si el id es negativo es porque hubo problema
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearUsuario(Usuario user)
    {
        user.setUs_id(dao.crearUsuario(
                        user.getUs_nombre_usuario(),
                        user.getUs_nombre(),
                        user.getUs_apellido(),
                        user.getUs_fecha_nacimiento(),
                        user.getUs_genero(),
                        user.getUs_email(),          
                        user.getUs_password()
                ));
        
        return Response.status(Response.Status.CREATED).build();
    }
    
    /**
     * Metodo que elimina a un usuario en la base de datos
     * @param id
     * @return int que dice: Si se encuentra lo elimina, si hubo un plobema de
     * comunicacion con la base de datos
     * devuelve un numero negativo
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarUsuario(@PathParam("id") int id)
    {
        int flag = dao.eliminarUsuario(id);
        
         return Response.ok(1).build();
    }
    
    /**
     * Metodo que Actualiza el perfil del Usuario
     * Actualiza los usuarios, pero no el id,el genero ni el nivel de juego
     * @param user
     * @return Usuario si el id es negativo es porque hubo problema
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarUsuario(Usuario user)
    {
       int resultado = dao.actualizarUsuario(
                        user.getUs_id(),
                        user.getUs_nombre_usuario(),
                        user.getUs_nombre(),
                        user.getUs_apellido(),
                        user.getUs_fecha_nacimiento(),
                        
                        user.getUs_email(),          
                        user.getUs_password()
        );
        if (resultado < 0)  
            return Response.status(Response.Status.NOT_FOUND).build();
        
        return Response.status(Response.Status.OK).entity(user).build();
        
    }
    
    /**
     * Metodo que recupera la contraseña de un usuario de la Base  de datos
     * @param user
     * @return String con la contraseña
     */
    @POST
    @Path("/recuperar-contrasena")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response recuperarContrasenaUsuario(Usuario user)
    {
       
        return Response.status(Response.Status.ACCEPTED).entity(
        dao.recuperarContrasenaUsuario(
                user.getUs_nombre_usuario(),
                user.getUs_email()
        )).build();
    }
    
    /**
     * Metodo que aumenta en 1 
     * el nivel de un usuario de la Base  de datos
     * @param user
     * 
     */
    @POST
    @Path("/aumentar-nivel")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response aumentarNivelUsuario(Usuario user)
    {
       dao.aumentarNivelUsuario(
                user.getUs_id()
        );
        return Response.status(Response.Status.OK).build();
    }
    
}
