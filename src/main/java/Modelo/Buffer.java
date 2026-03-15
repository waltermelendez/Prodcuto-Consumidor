/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Queue;
import java.util.ArrayDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author walter
 */
public class Buffer {

    private Queue<Integer> almacen = new ArrayDeque<>();

    private final int capacidad;

    public Buffer(int capacidad) {
        this.capacidad = capacidad;

    }

    public synchronized int consumir() {
        while (almacen.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        int i = almacen.poll();
        notifyAll();
        return i;
    }

    public synchronized void producir(int valor) {

        while (almacen.size() == capacidad) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        almacen.offer(valor);

        System.out.println("Buffer recibe: " + valor);

        notifyAll();
    }

    public int size() {
        return almacen.size();
    }

}
