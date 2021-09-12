/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.logica;

import co.edu.ucundinamarca.db.UsuarioDB;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel
 */
public class UsuarioLogica {
    
    private List<UsuarioDB> usuarios;
    private final String DIRECTORIO = "Usuarios.save";
    private static UsuarioLogica usuarioLogica;
    
    
    private UsuarioLogica() {
        
        this.usuarios = new ArrayList();
        
    }
    
    public static UsuarioLogica obtenerUsuarioLogica(){
    
        if(usuarioLogica == null)
            usuarioLogica = new UsuarioLogica();

        return usuarioLogica;
    
    }
    
    public List<UsuarioDB> obtenerUsuarios(){
        
        this.usuarios = (ArrayList)ArchivosUtilidad.obtenerObjeto(DIRECTORIO);
        return usuarios;
        
    }
    
  
    public UsuarioDB obtener(String documentoIdentidad){
        
        for(UsuarioDB usuario: usuarios){
        
            if(usuario.getDocumentoIdentidad().equals(documentoIdentidad))
                return usuario;
        
        }
        
        return null;
        
    }
    
 
    public void editar(UsuarioDB usuario){
        
        for(UsuarioDB usuarioIteracion: usuarios){
        
            if(usuarioIteracion.getDocumentoIdentidad().equals(usuario.getDocumentoIdentidad())){
                
                usuarios.set(usuarios.indexOf(usuarioIteracion), usuario);
                break;
                
            }
        
        }
        
        this.actualizarLista();
        
    }
    
 
    public void guardar(UsuarioDB usuario){
        
        if(this.verificarValidezUsuario(usuario)){
        
            usuarios.add(usuario);
            this.actualizarLista();
            
        }
        
    }
    
   
    public void eliminar(String documentoIdentidad){
        
        for(UsuarioDB usuario: usuarios){
        
            if(usuario.getDocumentoIdentidad().equals(documentoIdentidad)){
                usuarios.remove(usuario);
                break;
            }
        
        }
        
        this.actualizarLista();
        
    }
    
    private boolean verificarValidezUsuario(UsuarioDB usuario){
    
        if(usuario.getNombre().isEmpty() || usuario.getNombre() == null ||
            usuario.getApellido().isEmpty() || usuario.getApellido() == null ||
            usuario.getDocumentoIdentidad().isEmpty() || usuario.getDocumentoIdentidad() == null)
            return false;
        
        if(this.verificarExistenciaUsuario(usuario))
            return false;
        
        return true;
        
    }
    
    private boolean verificarExistenciaUsuario(UsuarioDB usuario){
    
        for(UsuarioDB usuarioIteracion: usuarios){
        
            if(usuarioIteracion.getDocumentoIdentidad().equals(usuario.getDocumentoIdentidad()))
                return true;
        
        }
        
        return false;
        
    }
    
    public void actualizarLista(){
    
        ArchivosUtilidad.crearArchivo(usuarios, DIRECTORIO);
    
    }
    
    
}
