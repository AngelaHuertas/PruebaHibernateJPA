package com.angela.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaPrincipal extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton btnPersona, btnMascota;

    public VentanaPrincipal() {
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Bienvenido", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titulo);

        btnPersona = new JButton("Registrar Persona");
        btnPersona.addActionListener(this);
        panel.add(btnPersona);

        btnMascota = new JButton("Registrar Mascota");
        btnMascota.addActionListener(this);
        panel.add(btnMascota);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPersona) {
            VentanaPersona ventanaP = new VentanaPersona();
            ventanaP.setVisible(true);
        } else if (e.getSource() == btnMascota) {
            VentanaMascota ventanaM = new VentanaMascota();
            ventanaM.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}