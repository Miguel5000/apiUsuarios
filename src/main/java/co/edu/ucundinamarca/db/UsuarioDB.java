/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.db;

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * Clase que posee los datos del usuario que se recibe y retorna, y permita aplicar operaciones sobre él
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
public class UsuarioDB implements Serializable{
    
    /**
     * Es el nombre del usuario
     */
    @NotNull
    @Size(min=1)
    private String nombre;
    
    /**
     * Es el apellido del usuario
     */
    @NotNull
    @Size(min=1)
    private String apellido;
    
    /**
     * Es el número de documento de identidad del usuario
     */
    @NotNull
    @Size(min=8, max=10)
    private String documentoIdentidad;
    
    /**
     * Es la edad del usuario
     */
    @NotNull
    @Min(10)
    @Max(130)
    private int edad;

    /**
     * Es el constructor por defecto del usuario
     */
    public UsuarioDB() {
    }

    /**
     * Es el constructor que permite darle un estado inicial a todos los atributos del usuario
     * @param nombre es el nombre del usuario a crear
     * @param apellido es el apellido del usuario a crear
     * @param documentoIdentidad es el número de documento de identidad del usuario a crear
     * @param edad es la edad del usuario a crear
     */
    public UsuarioDB(String nombre, String apellido, String documentoIdentidad, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documentoIdentidad = documentoIdentidad;
        this.edad = edad;
    }

    /**
     * Permite obtener el nombre del usuario
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Permite obtener el apellido del usuario
     * @return apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Permite obtener el documento de identidad del usuario
     * @return documentoIdentidad
     */
    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    /**
     * Permite obtener la edad del usuario
     * @return edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Permite asignarle un nombre al usuario
     * @param nombre es el nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Permite asignarle un apellido al usuario
     * @param apellido es el apellido a asignar
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Permite asignarle un documento de identidad al usuario
     * @param documentoIdentidad es el documento de indentidad a asignar
     */
    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    /**
     * Permite asignarle la edad al usuario
     * @param edad es la edad a asignar
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    
    
}
