/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.VentanaPrincipal;

import java.util.Queue;
import java.util.ArrayDeque;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author walter
 */
public class Buffer {

    //Cola en donde se encuentra los numeros leidos por el prodcutor
    private Queue<Integer> almacen = new ArrayDeque<>();

    //variable para poder tener un limite.
    private final int capacidad;


    private VentanaPrincipal vista;

    public Buffer(int capacidad, VentanaPrincipal vista) {
        this.capacidad = capacidad;
        this.vista = vista;

    }

    //Funcion para que el consumidor pueda consumir los numeros del almacen
    /* synchronized es una  palabra clave se trata de diferentes hilos que leen y escriben en las mismas variables, objetos y recursos.*/
    //Pide una etiqueta para mandarla a la vista.
    public synchronized int consumir(JLabel[] bufferVisual) {

        //Si el almacen esta vacio, espere
        while (almacen.isEmpty()) {

            try {
               
                wait();

            } catch (InterruptedException e) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        

        int i = almacen.poll();
        //Notifica a todos que ha consumido un numero

        vista.log("Consumido: " + i);
       
        vista.actualizarBuffer(bufferVisual, almacen);

        notifyAll();
        return i;
    }

    public synchronized void producir(int valor, JLabel[] bufferVisual) {

        //Si la capacidad es igual a la cantidad de la lista tiene que esperar
        while (almacen.size() == capacidad) {
            try {
              
                wait();

            } catch (InterruptedException e) {
            }
        }
        //Insertar un valor en el almacen
        almacen.offer(valor);

        
        //Envia a un texto en la interfaz
        
        vista.actualizarBuffer(bufferVisual, almacen);
        vista.log("Insertado: " + valor);

        //Notifica a todos que hay un numero en la buffer
        notifyAll();
    }

    //Funcion para poder saber en el productor cual es el tamañio de la cola.
    public int size() {
        return almacen.size();
    }

    

}
