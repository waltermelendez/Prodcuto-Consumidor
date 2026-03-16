/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.Vista;
import java.io.*;
import static java.lang.Thread.sleep;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 *
 * @author walter
 */
public class Productor extends Thread {

    private Buffer bufferPares;
    private Buffer bufferImpares;
    private Buffer bufferPrimos;
   

    //Cola para guardar los numeros leidos del archivo
    private Queue<Integer> producto = new ArrayDeque<>();

    //Constructor de productor con los diferentes buffers para cada timpo de numeros
    public Productor(Buffer p, Buffer i, Buffer pr) {
        this.bufferPares = p;
        this.bufferImpares = i;
        this.bufferPrimos = pr;
        
    }

    @Override
    public void run() {

        while (!producto.isEmpty()) {

            int numero = producto.poll();

            if (clasificar(numero)) {

                bufferPrimos.producir(numero);
                // System.out.println("Productor → primo: " + numero);
                listener.onEvento("Productor → primo: " + numero);

            } else if (numero % 2 == 0) {

                bufferPares.producir(numero);
                // System.out.println("Productor → par: " + numero);
                listener.onEvento("Productor → par: " + numero);

            } else {

                bufferImpares.producir(numero);
                // System.out.println("Productor → impar: " + numero);
                listener.onEvento("Productor → impar: " + numero);

            }

            try {
                sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    private boolean clasificar(int n) {
        if (n <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {

            if (n % i == 0) {
                return false;
            }

        }

        return true;

    }

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
