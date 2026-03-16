/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;


import Modelo.Evento;
import javax.swing.*;

public class Vista extends JFrame implements Evento{

    public JButton btnBuscar = new JButton("Buscar archivo");
    public JButton btnEjecutar = new JButton("Ejecutar");
    public JButton btnSalir = new JButton("Salir");

    public JTextArea areaEventos = new JTextArea();

    public JLabel lblArchivo = new JLabel("Archivo: ninguno");

    public Vista() {

        setTitle("Simulación Productor-Consumidor");
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelBotones = new JPanel();

        panelBotones.add(btnBuscar);
        panelBotones.add(btnEjecutar);
        panelBotones.add(btnSalir);

        add(panelBotones,"North");
        add(new JScrollPane(areaEventos),"Center");
        add(lblArchivo,"South");

        setVisible(true);
    }

    public void mostrarEvento(String mensaje){
        areaEventos.append(mensaje + "\n");
    }

    @Override
    public void onEvento(String mensaje) {
        areaEventos.append(mensaje + "\n");
    }
}
