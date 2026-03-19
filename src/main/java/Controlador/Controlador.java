/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Productor;
import Modelo.Buffer;
import Modelo.Consumidor;
import Vista.VentanaPrincipal;
import Vista.Vista;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Controlador {

    //Vista, objeto que conecta tanto la vista (la interfaz) a al controlador (acciones de los botones).
    private Vista vista;

    private String archivo;

    //Constructor del controlador
    public Controlador(Vista vista) {

        this.vista = vista;

        eventos();
    }

    private void eventos() {

        //Evento para el boton de buscar archivo
        vista.btnBuscar.addActionListener(e -> buscarArchivo());

        //Evento para el boton de ejecutar el codigo
        vista.btnEjecutar.addActionListener(e -> {
            try {
                ejecutar();
            } catch (IOException ex) {
                System.getLogger(Controlador.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });

        //Evento para el boton de salir del programa
        vista.btnSalir.addActionListener(e -> System.exit(0));
    }

    //Funcion para buscar el archivo, por medio de un explorador de archivo
    private void buscarArchivo() {

        //Declaracion del explorador de archivo
        JFileChooser chooser = new JFileChooser();

        int result = chooser.showOpenDialog(vista);

        //Si el archivo existe
        if (result == JFileChooser.APPROVE_OPTION) {

            File f = chooser.getSelectedFile();

            archivo = f.getAbsolutePath();

            //Muestra el nomber del archivo en texto
            vista.lblArchivo.setText("Archivo: " + archivo);

            vista.mostrarEvento("Archivo cargado");
        }
    }

    //Función en donde se declara los productores como los consumidores y que se inicia el 
    private void ejecutar() throws IOException {

        // aquí iniciara el  productor y consumidores
        vista.mostrarEvento("Iniciando simulación...");

        VentanaPrincipal vista = new VentanaPrincipal(5);

        Buffer pares = new Buffer(5, vista);
        Buffer impares = new Buffer(5, vista);
        Buffer primos = new Buffer(5, vista);

        Productor p = new Productor(pares, impares, primos, vista);
        p.read(archivo);

        Consumidor c1 = new Consumidor(pares, vista.getBufferPares(), vista, "PARES");
        Consumidor c2 = new Consumidor(impares, vista.getBufferImpares(), vista, "IMPARES");
        Consumidor c3 = new Consumidor(primos, vista.getBufferPrimos(), vista, "PRIMOS");

        p.start();
        c1.start();
        c2.start();
        c3.start();

    }
}
