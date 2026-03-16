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
public class Productorconsumidor {

    public static void main(String[] args) throws IOException, InterruptedException {
        
        Vista vista = new Vista();

        Controlador controlador = new Controlador(vista);

//        Buffer bufferPares = new Buffer(5);
//        Buffer bufferImpares = new Buffer(5);
//        Buffer bufferPrimos = new Buffer(5);
//
//        Productor productor = new Productor(
//                bufferPares,
//                bufferImpares,
//                bufferPrimos,
//                vista
//        );
//
//        Consumidor c1 = new Consumidor(bufferPares);
//        Consumidor c2 = new Consumidor(bufferImpares);
//        Consumidor c3 = new Consumidor(bufferPrimos);
//
//        c1.setName("Consumidor PARES");
//        c2.setName("Consumidor IMPARES");
//        c3.setName("Consumidor PRIMOS");
//        productor.read("/home/walter/Descargas/doc.txt");
//
//        productor.start();
//        c1.start();
//        c2.start();
//        c3.start();
        

    }
}
