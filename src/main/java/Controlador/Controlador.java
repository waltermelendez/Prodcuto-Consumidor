/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Productor;
import Modelo.Buffer;
import Modelo.Consumidor;
import Modelo.Evento;
import Vista.Vista;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Controlador {

    private Vista vista;

    private String archivo;

    private Evento listener;

    public Controlador(Vista vista) {

        this.vista = vista;

        eventos();
    }

    private void eventos() {

        vista.btnBuscar.addActionListener(e -> buscarArchivo());

        vista.btnEjecutar.addActionListener(e -> ejecutar());

        vista.btnSalir.addActionListener(e -> System.exit(0));
    }

    private void buscarArchivo() {

        JFileChooser chooser = new JFileChooser();

        int result = chooser.showOpenDialog(vista);

        if (result == JFileChooser.APPROVE_OPTION) {

            File f = chooser.getSelectedFile();

            archivo = f.getAbsolutePath();

            vista.lblArchivo.setText("Archivo: " + archivo);

            vista.mostrarEvento("Archivo cargado");
        }
    }

    private void ejecutar() {

        vista.mostrarEvento("Iniciando simulación...");

        Buffer bufferPares = new Buffer(5,vista);
        Buffer bufferImpares = new Buffer(5,vista);
        Buffer bufferPrimos = new Buffer(5,vista);

        Productor productor = new Productor(bufferPares, bufferImpares, bufferPrimos,vista);
        try {
            productor.read(archivo);
        } catch (IOException ex) {
            System.getLogger(Controlador.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        Consumidor c1 = new Consumidor(bufferPares,vista);
        Consumidor c2 = new Consumidor(bufferImpares,vista);
        Consumidor c3 = new Consumidor(bufferPrimos,vista);

        productor.start();
        c1.start();
        c2.start();
        c3.start();

        // aquí iniciarías productor y consumidores
    }
}
