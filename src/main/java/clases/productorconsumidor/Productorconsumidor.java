/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package clases.productorconsumidor;

import Modelo.Productor;
import Modelo.Consumidor;
import Modelo.Buffer;
import java.io.*;
import Controlador.Controlador;
import Vista.Vista;

/**
 *
 * @author walter
 */

//Este es el MAIN del codigo, en donde se inicia la vista y el controlador
public class Productorconsumidor {

    public static void main(String[] args) throws IOException, InterruptedException {
        
        Vista vista = new Vista();

        Controlador controlador = new Controlador(vista);

      
        

    }
}
