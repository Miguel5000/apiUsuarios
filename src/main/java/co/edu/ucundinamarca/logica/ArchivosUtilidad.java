/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucundinamarca.logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Clase que permite crear un archivo a partir de un objeto, y recuperar dicho objeto
 * @author Miguel Ángel Manrique Téllez
 * @since 1.0.0
 * @version 1.0.0
 */
public class ArchivosUtilidad {
 
    /**
     * Permite crear un archivo que posea la información del objeto
     * @param usuarios es el objeto a guardar
     * @param direccion es la dirección del archivo a crear
     */
    public static void crearArchivo(Object usuarios, String direccion){
    
        ObjectOutputStream objStream = null;

        try {
          objStream = new ObjectOutputStream(new FileOutputStream(new File(direccion)));

          objStream.writeObject(usuarios);
          objStream.close();

        } catch (Exception e) {
          System.out.println("Error Writing Pattern");
          System.exit(1);
        }
    
    }
    
    /**
     * Permite obtener el objeto almacenado en un archivo
     * @param direccion es la dirección del archivo que posee el objeto
     * @return objeto
     */
    public static Object obtenerObjeto(String direccion){
    
        ObjectInputStream objStream = null;
        Object obj = null;

        File archivo = new File(direccion);
        
        if (archivo.exists()) {
          //read the object from the file
          try {
            objStream = new ObjectInputStream(new FileInputStream(new File(direccion)));

            obj = objStream.readObject();
            objStream.close();

          } catch (Exception e) {
            System.out.println("Error Reading File");
            System.exit(1);
          }

        }
        return obj;
        
    }
    
}
