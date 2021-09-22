/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.logica;

import co.edu.ucundinamarca.db.UsuarioDB;
import co.edu.ucundinamarca.logica.excepciones.CreacionException;
import co.edu.ucundinamarca.logica.excepciones.EdicionException;
import co.edu.ucundinamarca.logica.excepciones.EliminacionException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que posee la lógica de gestión de usuarios
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
public class UsuarioLogica {
    
    /**
     * Es la lista de todos los usuarios
     */
    private List<UsuarioDB> usuarios;
    
    /**
     * Es la dirección de guardado del archivo que posee la información de todos los usuarios
     */
    private final String DIRECTORIO = "Usuarios.save";
    
    /**
     * Es el objeto que se brinda para manejar la lógica de los usuarios
     */
    private static UsuarioLogica usuarioLogica;
   
    
    /**
     * Es el constructor de la clase
     */
    private UsuarioLogica() {
        
        this.usuarios = new ArrayList();
        
    }
    
    /**
     * Es el método que permite a occeder al objeto único de esta clase
     * @return usuarioLogica
     */
    public static UsuarioLogica obtenerUsuarioLogica(){
    
        if(usuarioLogica == null)
            usuarioLogica = new UsuarioLogica();

        return usuarioLogica;
    
    }
    
    /**
     * Permite obtener a todos los usuarios
     * @return usuarios
     */
    public List<UsuarioDB> obtenerLista(){
        
        this.usuarios = (ArrayList)ArchivosUtilidad.obtenerObjeto(DIRECTORIO);
        return usuarios;
        
    }
    
  
    /**
     * Permite obtener al usuario según su documento de identidad
     * @param documentoIdentidad es el documento de identidad del usuario a obtener
     * @return usuario
     */
    public UsuarioDB obtener(String documentoIdentidad){
        
        UsuarioDB usuarioAObtener = null;
        
        for(UsuarioDB usuario: usuarios){
        
            if(usuario.getDocumentoIdentidad().equals(documentoIdentidad))
                usuarioAObtener = usuario;
        
            break;
            
        }
        
        return usuarioAObtener;
        
    }
    
    /**
     * Permite editar la información de un usuario
     * @param usuario es el objeto que posee el documento del usuario a editar, y los nuevos datos de ese usuario
     */
    public void editar(UsuarioDB usuario) throws EdicionException{
        
        UsuarioDB usuarioAEditar = null;
        
        for(UsuarioDB usuarioIteracion: usuarios){
        
            if(usuarioIteracion.getDocumentoIdentidad().equals(usuario.getDocumentoIdentidad())){
                
                usuarioAEditar = usuarioIteracion;
                break;
           
            }
        
        }
        
        if(usuarioAEditar == null) throw new EdicionException("No se ha encontrado el usuario que se desea editar.");
            
        usuarios.set(usuarios.indexOf(usuarioAEditar), usuario);
        this.actualizarLista();
        
    }
    
    /**
     * Permite insertar un nuevo usuario
     * @param usuario es el objeto que posee los datos del usuario a insertar
     */
    public void guardar(UsuarioDB usuario) throws CreacionException{
        
        if(this.verificarExistenciaUsuario(usuario)){
        
            throw new CreacionException("Ya existe un usuario con el número de identificación enviado");
            
        }
        
        usuarios.add(usuario);
        this.actualizarLista();
        
    }
    
    /**
     * Permite eliminar a un usuario
     * @param documentoIdentidad es el documento de identidad del usuario a eliminar
     */
    public void eliminar(String documentoIdentidad) throws EliminacionException{
        
        UsuarioDB usuarioAEliminar = null;
        
        for(UsuarioDB usuario: usuarios){
        
            if(usuario.getDocumentoIdentidad().equals(documentoIdentidad)){
                usuarioAEliminar = usuario;
                break;
            }
        
        }
        
        if(usuarioAEliminar == null) throw new EliminacionException("No se ha encontrado el usuario que se desea eliminar.");
        
        usuarios.remove(usuarioAEliminar);
        this.actualizarLista();

    }
    
    /**
     * Permite comprobar si existe un usuario con el mismo documento
     * @param usuario es el objeto que posee el documento que debe verificarse si ya existe
     * @return respuesta
     */
    private boolean verificarExistenciaUsuario(UsuarioDB usuario){
    
        for(UsuarioDB usuarioIteracion: usuarios){
        
            if(usuarioIteracion.getDocumentoIdentidad().equals(usuario.getDocumentoIdentidad()))
                return true;
        
        }
        
        return false;
        
    }
    
    /**
     * Permite crear o sobrescribir el archivo a partir de la lista de usuarios
     */
    private void actualizarLista(){
    
        ArchivosUtilidad.crearArchivo(usuarios, DIRECTORIO);
    
    }
    
    
}
