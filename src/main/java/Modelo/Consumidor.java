/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.VentanaPrincipal;

import static java.lang.Thread.sleep;
import javax.swing.JLabel;

/**
 *
 * @author walter
 */
public class Consumidor extends Thread {

    private final Buffer buffer;
    private int suma;
    private JLabel[] bufferVisual;
    private String tipo;
    private VentanaPrincipal vista;

    // Objeto de vista para que el consumidor pueda mostrar a la vista lo que tiene que mostrar a la interfaz
    //private Vista listener;
    //Constructor de consumidor, llama al buffer correspondiente, la etiqueta, ventana y el nombre
    public Consumidor(Buffer buffer, JLabel[] bufferVisual, VentanaPrincipal vista, String tipo) {
        this.buffer = buffer;
        this.suma = 0;
        this.bufferVisual = bufferVisual;
        this.tipo = tipo;
        this.vista = vista;
    }

    //Iniciar consumidor
    @Override
    public void run() {
        int i = 0;

        while (true) {

            //llamada a la funcion consumir del buffer
            i = buffer.consumir(bufferVisual);
            suma+=i;
            //Actualizar la suma del consumidor y luego mostrarla a la vista
            vista.actualizarSuma(tipo, suma);


            
            try {
                sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
