/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author walter
 */
public class VentanaPrincipal extends JFrame {
    
    //Declaracion de los arreglos de etiquetas para mostrar en pantalla
    private JLabel[] bufferPares;
    private JLabel[] bufferImpares;
    private JLabel[] bufferPrimos;
    
    //Etiquetas del estado del prodcutor y del consumidor
    private JLabel lblEstadoProductor;
    private JLabel lblEstadoConsumidor;

    //Etiquetas de los nombres de los buffers
    private JLabel lblContadorPares;
    private JLabel lblContadorImpares;
    private JLabel lblContadorPrimos;

    // Texto en donde se muestra las acciones
    private JTextArea log;

    //Ventana en donde se mostrar el 
    public VentanaPrincipal(int capacidad) {

        setTitle("Productor-Consumidor");
        setSize(1000, 750);
        setLayout(new BorderLayout());
        
        //Crea un nuevo panel 
        JPanel panelBuffers = new JPanel(new GridLayout(3, 1));

        //Creacion de los bufferes, pide el nombre del buffer, un panel a donde mardar la información y la capacidad del buffer.
        bufferPares = crearBufferPanel("PARES", panelBuffers, capacidad);
        bufferImpares = crearBufferPanel("IMPARES", panelBuffers, capacidad);
        bufferPrimos = crearBufferPanel("PRIMOS", panelBuffers, capacidad);

        add(panelBuffers, BorderLayout.CENTER);
        
        //Nuevo panel para mostrar la informacion
        JPanel panelInfo = new JPanel(new GridLayout(3, 2));
        
        
        


        //Crea un area de texto mostrar la informacion
        log = new JTextArea();
        add(new JScrollPane(log), BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    //Crea el buffer visual en la ventana, pide el titulo como se va a llamar, el panel y la capacidad del buffer
    private JLabel[] crearBufferPanel(String titulo, JPanel padre, int capacidad) {

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(titulo));

        JLabel[] buffer = new JLabel[capacidad];

        for (int i = 0; i < capacidad; i++) {
            buffer[i] = new JLabel("_", SwingConstants.CENTER);
            buffer[i].setPreferredSize(new Dimension(40, 40));
            buffer[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.add(buffer[i]);
        }

        padre.add(panel);
        return buffer;
    }
    
    //Funcion para actualizar los datos al ejecutarse el programa
    public void actualizarBuffer(JLabel[] buffer, java.util.Queue<Integer> cola) {
        
        
        //Se declara un hilo aparte para que se encargue de actualizar los datos de la parte visual.
        SwingUtilities.invokeLater(() -> {

            int i = 0;
            for (Integer num : cola) {
                buffer[i].setText(String.valueOf(num));
                i++;
            }

            for (; i < buffer.length; i++) {
                buffer[i].setText("_");
            }
        });
    }
    
    //Mandar mensaje
    public void log(String msg) {
        SwingUtilities.invokeLater(() -> log.append(msg + "\n"));
    }



    
    
    //Funciones para obtener los areglos de etiquetas
    public JLabel[] getBufferPares() {
        return bufferPares;
    }

    public JLabel[] getBufferImpares() {
        return bufferImpares;
    }

    public JLabel[] getBufferPrimos() {
        return bufferPrimos;
    }
    
    //Funcion para actualizar y mostrar la suma del consumidor 
    public void actualizarSuma(String tipo, int suma) {
        SwingUtilities.invokeLater(() -> {
            log.append(tipo + " suma = " + suma + "\n");
        });
    }

}
