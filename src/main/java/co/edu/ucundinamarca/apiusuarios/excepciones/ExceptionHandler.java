/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.apiusuarios.excepciones;

import co.edu.ucundinamarca.ejb.excepciones.CreacionException;
import co.edu.ucundinamarca.ejb.excepciones.EdicionException;
import co.edu.ucundinamarca.ejb.excepciones.EliminacionException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Clase que captura las excepciones de la API
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
@Provider
public class ExceptionHandler implements ExceptionMapper<Exception>{

    /**
     * Es la variable que permite obtener la URL del método de la API que generó la excepción
     */
    @Context private UriInfo uriInfo;

    /**
     * Permite responder de acuerdo con la excepción capturada
     * @param exception es la excepción capturada
     * @return respuesta
     */
    @Override
    public Response toResponse(Exception exception) {
        
        ExceptionWrapper wrapper;

        String enlace = uriInfo.getPath();
        
        Status estado = Response.Status.INTERNAL_SERVER_ERROR;

        if(exception instanceof CreacionException){
        
            estado = Response.Status.CONFLICT;
        
        }if(exception instanceof EliminacionException || exception instanceof EdicionException){
        
            estado = Response.Status.NOT_FOUND;
        
        }if(exception instanceof NotAllowedException){
        
            estado = Response.Status.METHOD_NOT_ALLOWED;
        
        }if(exception instanceof NotSupportedException){
        
            estado = Response.Status.UNSUPPORTED_MEDIA_TYPE;
            
        }
        
        wrapper = new ExceptionWrapper(Integer.toString(estado.getStatusCode()), estado.name(), exception.getMessage(), enlace);
        return Response.status(Response.Status.CONFLICT).entity(wrapper).build(); 

    }
    
}
