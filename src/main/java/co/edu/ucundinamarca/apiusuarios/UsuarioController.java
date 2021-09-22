/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apiusuarios;

import co.edu.ucundinamarca.db.UsuarioDB;
import co.edu.ucundinamarca.logica.UsuarioLogica;
import co.edu.ucundinamarca.logica.excepciones.CreacionException;
import co.edu.ucundinamarca.logica.excepciones.EdicionException;
import co.edu.ucundinamarca.logica.excepciones.EliminacionException;
import java.util.List;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.validation.constraints.Size;
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
 * Clase que posee los servicios de la API encargados de administrar a los usuarios
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
@Stateless
@Path("/usuarios")
public class UsuarioController {
    
    /**
     * Permite obtener a todos los usuarios
     * @return usuarios
     */
    @GET
    @Path("/obtenerLista")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerLista(){
        
        List<UsuarioDB> usuarios = UsuarioLogica.obtenerUsuarioLogica().obtenerLista();
        
        if(usuarios.isEmpty()) return Response.status(Response.Status.NO_CONTENT).build();
                
        return Response.status(Response.Status.OK).entity(usuarios).build();
        
    }
    
    /**
     * Permite obtener al usuario según su documento de identidad
     * @param documentoIdentidad es el documento de identidad del usuario a obtener
     * @return usuario
     */
    @GET
    @Path("/obtener/{documentoIdentidad}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtener(@PathParam("documentoIdentidad") @Size(min=8, max=10) String documentoIdentidad){
        
        UsuarioDB usuario = null;
        
        try{
        
            usuario = UsuarioLogica.obtenerUsuarioLogica().obtener(documentoIdentidad);
        
        }catch(NumberFormatException ex){
        
            return Response.status(Response.Status.BAD_REQUEST).build();
        
        }
        
        
        if(usuario == null) return Response.status(Response.Status.NO_CONTENT).build();
        
        return Response.status(Response.Status.OK).entity(usuario).build();
        
    }
    
    /**
     * Permite editar la información de un usuario
     * @param usuario es el objeto que posee el documento del usuario a editar, y los nuevos datos de ese usuario
     */
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editar(@Valid UsuarioDB usuario){
        
        try {
            UsuarioLogica.obtenerUsuarioLogica().editar(usuario);
            return Response.status(Response.Status.OK).build();
        } catch (EdicionException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
    }
    
    /**
     * Permite insertar un nuevo usuario
     * @param usuario es el objeto que posee los datos del usuario a insertar
     */
    @POST
    @Path("/guardar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardar(@Valid UsuarioDB usuario){
       
        try {
            UsuarioLogica.obtenerUsuarioLogica().guardar(usuario);
            return Response.status(Response.Status.CREATED).build();
        } catch (CreacionException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        
    }
    
    /**
     * Permite eliminar a un usuario
     * @param documentoIdentidad es el documento de identidad del usuario a eliminar
     */
    @DELETE
    @Path("/eliminar/{documentoIdentidad}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("documentoIdentidad") @Size(min=8, max=10) String documentoIdentidad){
        
        try {
            UsuarioLogica.obtenerUsuarioLogica().eliminar(documentoIdentidad);
            return Response.status(Response.Status.OK).build();
        } catch (EliminacionException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
    }
    
}
