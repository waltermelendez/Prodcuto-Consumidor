/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.VentanaPrincipal;
import java.io.*;
//Liberira para la funcion "sleep" del thread.
import static java.lang.Thread.sleep;
//Librerias para poder declarar una cola.
import java.util.ArrayDeque;
import java.util.Queue;

/**
 *
 * @author walter
 */
public class Productor extends Thread {

    //Declaracion de los buffer, son los espacios en donde tanto el consumindor el productor se puede comunicar
    private Buffer bufferPares;
    private Buffer bufferImpares;
    private Buffer bufferPrimos;

    private VentanaPrincipal listener;

    //Cola para guardar los numeros leidos del archivo
    private Queue<Integer> producto = new ArrayDeque<>();

    //Constructor de productor con los diferentes buffers para cada timpo de numeros
    public Productor(Buffer p, Buffer i, Buffer pr, VentanaPrincipal listener) {
        this.bufferPares = p;
        this.bufferImpares = i;
        this.bufferPrimos = pr;
        this.listener = listener;
    }

    //Funcion de inicio el productor
    @Override
    public void run() {

        while (!producto.isEmpty()) {

            int numero = producto.poll();

            if (clasificar(numero)) {
                //Caso en donde el numero es primo
                bufferPrimos.producir(numero, listener.getBufferPrimos());
                // System.out.println("Productor → primo: " + numero);
                // listener.mostrarEvento("Productor → primo: " + numero);

            } else if (numero % 2 == 0) {

                //Caso en donde el numero es par
                bufferPares.producir(numero, listener.getBufferPares());
                // System.out.println("Productor → par: " + numero);
                //  listener.mostrarEvento("Productor → par: " + numero);

            } else {
                //Caso en donde el numero es impar
                bufferImpares.producir(numero, listener.getBufferImpares());
                // System.out.println("Productor → impar: " + numero);
                //listener.mostrarEvento("Productor → impar: " + numero);

            }
           

            //Funcion para dormir al thread
            try {
                sleep(500);
            } catch (InterruptedException e) {
            }

        }
    }

    //Funcion para clasificar numeros
    private boolean clasificar(int n) {
        if (n <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {

            //Si es divisiable dentro de i, no es par
            if (n % i == 0) {
                return false;
            }

        }
        //Si es no  divisiable dentro de i, es primo
        return true;

    }

    //Funcion para que el productor tenga la capacidad de leer archivos
    public void read(String archivo) throws FileNotFoundException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String line;
            int i = 0;

            while ((line = reader.readLine()) != null) {
                //Se elimina cualquier salto de linea o espacio en blanaco
                i = Integer.parseInt(line.trim());
                this.producto.offer(i);

            }
            reader.close();
        }

    }
}
