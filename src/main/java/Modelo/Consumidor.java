/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.Vista;
import static java.lang.Thread.sleep;

/**
 *
 * @author walter
 */
public class Consumidor extends Thread {

    private final Buffer buffer;
    private int suma;
    private Vista listener;

    //Constructor de consumidor
    public Consumidor(Buffer buffer, Vista listener) {
        this.buffer = buffer;
        this.suma = 0;
        this.listener = listener;
    }

    //Iniciar consumidor
    @Override
    public void run() {
        int i = 0;

        while (true) {

            i = buffer.consumir();

            suma += i;

//            System.out.println(
//                    Thread.currentThread().getName()
//                    + " consume: " + i
//                    + " | suma = " + suma
//            );
         
            listener.mostrarEvento("consumidor → consume: " + i + " | suma = " + suma);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
