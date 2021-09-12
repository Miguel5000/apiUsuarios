/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apiusuarios;

import co.edu.ucundinamarca.db.UsuarioDB;
import co.edu.ucundinamarca.logica.UsuarioLogica;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
    @Path("/obtenerUsuarios")
    public List<UsuarioDB> obtenerUsuarios(){
        
        return UsuarioLogica.obtenerUsuarioLogica().obtenerUsuarios();
        
    }
    
    /**
     * Permite obtener al usuario según su documento de identidad
     * @param documentoIdentidad es el documento de identidad del usuario a obtener
     * @return usuario
     */
    @GET
    @Path("/obtener/{documentoIdentidad}")
    public UsuarioDB obtener(@PathParam("documentoIdentidad") String documentoIdentidad){
        
        return UsuarioLogica.obtenerUsuarioLogica().obtener(documentoIdentidad);
        
    }
    
    /**
     * Permite editar la información de un usuario
     * @param usuario es el objeto que posee el documento del usuario a editar, y los nuevos datos de ese usuario
     */
    @PUT
    @Path("/editar")
    public void editar(UsuarioDB usuario){
        
        UsuarioLogica.obtenerUsuarioLogica().editar(usuario);
        
    }
    
    /**
     * Permite insertar un nuevo usuario
     * @param usuario es el objeto que posee los datos del usuario a insertar
     */
    @POST
    @Path("/guardar")
    public void guardar(UsuarioDB usuario){
       
        UsuarioLogica.obtenerUsuarioLogica().guardar(usuario);
        
    }
    
    /**
     * Permite eliminar a un usuario
     * @param documentoIdentidad es el documento de identidad del usuario a eliminar
     */
    @DELETE
    @Path("/eliminar/{documentoIdentidad}")
    public void eliminar(@PathParam("documentoIdentidad") String documentoIdentidad){
        
        UsuarioLogica.obtenerUsuarioLogica().eliminar(documentoIdentidad);
        
    }
    
}
