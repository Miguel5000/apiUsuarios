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
 *
 * @author Miguel
 */

@Stateless
@Path("/usuarios")
public class UsuarioController {
    
    @GET
    @Path("/obtenerUsuarios")
    public List<UsuarioDB> obtenerUsuarios(){
        
        return UsuarioLogica.obtenerUsuarioLogica().obtenerUsuarios();
        
    }
    
    @GET
    @Path("/obtener/{documentoIdentidad}")
    public UsuarioDB obtener(@PathParam("documentoIdentidad") String documentoIdentidad){
        
        return UsuarioLogica.obtenerUsuarioLogica().obtener(documentoIdentidad);
        
    }
    
    @PUT
    @Path("/editar")
    public void editar(UsuarioDB usuario){
        
        UsuarioLogica.obtenerUsuarioLogica().editar(usuario);
        
    }
    
    @POST
    @Path("/guardar")
    public void guardar(UsuarioDB usuario){
       
        UsuarioLogica.obtenerUsuarioLogica().guardar(usuario);
        
    }
    
    @DELETE
    @Path("/eliminar/{documentoIdentidad}")
    public void eliminar(@PathParam("documentoIdentidad") String documentoIdentidad){
        
        UsuarioLogica.obtenerUsuarioLogica().eliminar(documentoIdentidad);
        
    }
    
}
