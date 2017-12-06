/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucab.tesis.servicio;

import com.ucab.tesis.dao.ImagenDao;
import com.ucab.tesis.modelo.Usuario;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Miguelangel
 */
@Path("imagenes")
public class ImagenServicio {
    private ImagenDao imagenDao = new ImagenDao();
    
    /**
     * Metodo que envia imagenes atraves de json
     * @param user
     * 
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response retornaImagenJSON(@PathParam ("id") int id)
    {
       return Response.status(Response.Status.ACCEPTED).entity(
        imagenDao.retornaImagen(id)
        ).build();
    }
    
    /**
     * Metodo que prueba si envia imagenes
     * 
     * @param id
     * 
     */
    @GET
    @Path("/imagen/{id}")
    @Produces({"image/png"})
    public Response retornaImagen(@PathParam ("id") int id)
    {
       return Response.status(Response.Status.ACCEPTED).entity(
        imagenDao.retornaImagen(id).getBytes()
        ).build();
    }
}
