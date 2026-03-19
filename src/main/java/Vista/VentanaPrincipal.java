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

    private JLabel[] bufferPares;
    private JLabel[] bufferImpares;
    private JLabel[] bufferPrimos;

    private JLabel lblEstadoProductor;
    private JLabel lblEstadoConsumidor;

    private JLabel lblContadorPares;
    private JLabel lblContadorImpares;
    private JLabel lblContadorPrimos;

    private JTextArea log;

    public VentanaPrincipal(int capacidad) {

        setTitle("Productor-Consumidor");
        setSize(1000, 750);
        setLayout(new BorderLayout());

        JPanel panelBuffers = new JPanel(new GridLayout(3, 1));

        bufferPares = crearBufferPanel("PARES", panelBuffers, capacidad);
        bufferImpares = crearBufferPanel("IMPARES", panelBuffers, capacidad);
        bufferPrimos = crearBufferPanel("PRIMOS", panelBuffers, capacidad);

        add(panelBuffers, BorderLayout.CENTER);

        JPanel panelInfo = new JPanel(new GridLayout(3, 2));

        lblEstadoProductor = new JLabel("Productor: ");
        lblEstadoConsumidor = new JLabel("Consumidores: ");

        lblContadorPares = new JLabel("Pares: 0");
        lblContadorImpares = new JLabel("Impares: 0");
        lblContadorPrimos = new JLabel("Primos: 0");

        panelInfo.add(lblEstadoProductor);
        panelInfo.add(lblEstadoConsumidor);
        panelInfo.add(lblContadorPares);
        panelInfo.add(lblContadorImpares);
        panelInfo.add(lblContadorPrimos);

        add(panelInfo, BorderLayout.NORTH);

        log = new JTextArea();
        add(new JScrollPane(log), BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

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

    public void actualizarBuffer(JLabel[] buffer, java.util.Queue<Integer> cola) {

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

    public void log(String msg) {
        SwingUtilities.invokeLater(() -> log.append(msg + "\n"));
    }

    public void setEstadoProductor(String estado) {
        SwingUtilities.invokeLater(() -> lblEstadoProductor.setText("Productor: " + estado));
    }

    public void setEstadoConsumidor(String estado) {
        SwingUtilities.invokeLater(() -> lblEstadoConsumidor.setText("Consumidor: " + estado));
    }

    public void actualizarContadores(int pares, int impares, int primos) {
        SwingUtilities.invokeLater(() -> {
            lblContadorPares.setText("Pares: " + pares);
            lblContadorImpares.setText("Impares: " + impares);
            lblContadorPrimos.setText("Primos: " + primos);
        });
    }

    public JLabel[] getBufferPares() {
        return bufferPares;
    }

    public JLabel[] getBufferImpares() {
        return bufferImpares;
    }

    public JLabel[] getBufferPrimos() {
        return bufferPrimos;
    }

    public void actualizarSuma(String tipo, int suma) {
        SwingUtilities.invokeLater(() -> {
            log.append(tipo + " suma = " + suma + "\n");
        });
    }

}
