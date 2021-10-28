/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apiusuarios;



import co.edu.ucundinamarca.ejb.dao.IUsuarioDao;
import co.edu.ucundinamarca.ejb.entity.Usuario;
import co.edu.ucundinamarca.ejb.excepciones.CreacionException;
import co.edu.ucundinamarca.ejb.excepciones.EdicionConflictoException;
import co.edu.ucundinamarca.ejb.excepciones.EdicionException;
import co.edu.ucundinamarca.ejb.excepciones.EliminacionException;
import co.edu.ucundinamarca.ejb.excepciones.ObtencionException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
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
    

    @EJB
    private IUsuarioDao service;
    
    /**
     * Permite obtener a todos los usuarios
     * @return usuarios
     */
    @GET
    @Path("/obtenerLista")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerLista(){
        
        List<Usuario> usuarios = this.service.listarTodos();
        
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
    public Response obtener(@PathParam("documentoIdentidad") @Size(min=8, max=10) @Pattern(regexp="^[0-9]*$") String documentoIdentidad) throws ObtencionException{
        
        Usuario usuario = this.service.get(documentoIdentidad);
        return Response.status(Response.Status.OK).entity(usuario).build();
        
    }
    
    /**
     * Permite obtener al usuario según su id
     * @param id es el id del usuario a obtener
     * @return usuario
     */
    @GET
    @Path("/obtenerPorId/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") int id) throws ObtencionException{
        
        Usuario usuario = this.service.get(id);
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
    public Response editar(@Valid Usuario usuario) throws EdicionException, EdicionConflictoException{
        
        this.service.editar(usuario);
        return Response.status(Response.Status.OK).build();
        
    }
    
    /**
     * Permite insertar un nuevo usuario
     * @param usuario es el objeto que posee los datos del usuario a insertar
     */
    @POST
    @Path("/guardar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardar(@Valid Usuario usuario) throws CreacionException{

        this.service.guardar(usuario);
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    /**
     * Permite eliminar a un usuario
     * @param id es el id del usuario a eliminar
     */
    @DELETE
    @Path("/eliminar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("id") int id) throws EliminacionException{
        
        
        this.service.eliminar(id);
        return Response.status(Response.Status.OK).build();

    }
    
    /**
     * Permite eliminar a un usuario
     * @param id es el id del usuario a eliminar
     */
    @DELETE
    @Path("/eliminarNativo/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarNativo(@PathParam("id") int id) throws EliminacionException{
        
        
        this.service.eliminarNativo(id);
        return Response.status(Response.Status.OK).build();

    }
    
}

