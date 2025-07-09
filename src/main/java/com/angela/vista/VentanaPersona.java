package com.angela.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import com.angela.dao.PersonaDao;
import com.angela.entidades.Persona;

public class VentanaPersona extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtId, txtNombre;
    private JButton btnRegistrar, btnConsultar, btnActualizar, btnEliminar, btnConsultarLista, btnConsultarMascotas, btnLimpiar;
    private JTextArea textArea;
    private PersonaDao personaDao = new PersonaDao();

    public VentanaPersona() {
        setTitle("Gestión de Personas");
        setResizable(false);
        setBounds(100, 100, 415, 577);
        setLocationRelativeTo(null);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        contentPane = new JPanel(); contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("PERSONAS"); 
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16)); 
        lblTitulo.setBounds(170, 11, 100, 20);
        contentPane.add(lblTitulo);

        JLabel lblId = new JLabel("Id Persona:"); 
        lblId.setFont(new Font("Arial", Font.PLAIN, 12)); 
        lblId.setBounds(20, 50, 70, 14);
        contentPane.add(lblId);
        
        txtId = new JTextField(); 
        txtId.setBounds(100, 47, 147, 20); 
        contentPane.add(txtId);

        JLabel lblNombre = new JLabel("Nombre:"); 
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 12)); 
        lblNombre.setBounds(20, 80, 70, 14);
        contentPane.add(lblNombre);
        
        txtNombre = new JTextField(); 
        txtNombre.setBounds(100, 77, 147, 20); 
        contentPane.add(txtNombre);

        JSeparator separator = new JSeparator(); 
        separator.setBounds(20, 120, 355, 2); 
        contentPane.add(separator);

        btnRegistrar = new JButton("Registrar"); 
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 12)); 
        btnRegistrar.setBounds(20, 135, 174, 23); 
        btnRegistrar.addActionListener(this); 
        contentPane.add(btnRegistrar);
        
        btnConsultar = new JButton("Consultar");
        btnConsultar.setFont(new Font("Arial", Font.BOLD, 12)); 
        btnConsultar.setBounds(201, 135, 174, 23); 
        btnConsultar.addActionListener(this); 
        contentPane.add(btnConsultar);
        
        btnActualizar = new JButton("Actualizar"); 
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 12)); 
        btnActualizar.setBounds(20, 169, 174, 23); 
        btnActualizar.addActionListener(this); 
        contentPane.add(btnActualizar);
        
        btnEliminar = new JButton("Eliminar"); 
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 12)); 
        btnEliminar.setBounds(201, 169, 174, 23); 
        btnEliminar.addActionListener(this); 
        contentPane.add(btnEliminar);
        
        btnConsultarLista = new JButton("Lista Personas"); 
        btnConsultarLista.setFont(new Font("Arial", Font.BOLD, 12)); 
        btnConsultarLista.setBounds(20, 203, 174, 23); 
        btnConsultarLista.addActionListener(this); 
        contentPane.add(btnConsultarLista);
        
        btnConsultarMascotas = new JButton("Ver Mascotas"); 
        btnConsultarMascotas.setFont(new Font("Arial", Font.BOLD, 12)); 
        btnConsultarMascotas.setBounds(201, 203, 174, 23); 
        btnConsultarMascotas.addActionListener(this); 
        contentPane.add(btnConsultarMascotas);
        
        btnLimpiar = new JButton("Limpiar"); 
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 12)); 
        btnLimpiar.setBounds(20, 237, 355, 23); 
        btnLimpiar.addActionListener(this); 
        contentPane.add(btnLimpiar);

        JScrollPane scrollPane = new JScrollPane(); scrollPane.setBounds(20, 271, 355, 273); contentPane.add(scrollPane);
        textArea = new JTextArea(); scrollPane.setViewportView(textArea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) registrarPersona();
        else if (e.getSource() == btnConsultar) consultarPersona();
        else if (e.getSource() == btnActualizar) actualizarPersona();
        else if (e.getSource() == btnEliminar) eliminarPersona();
        else if (e.getSource() == btnConsultarLista) listarPersonas();
        else if (e.getSource() == btnConsultarMascotas) listarMascotas();
        else if (e.getSource() == btnLimpiar) limpiarCampos();
    }

    private void registrarPersona() {
        try {
            Long id = Long.parseLong(txtId.getText().trim());

            // Validar si ya existe
            Persona existente = personaDao.consultarPersona(id);
            if (existente != null) {
                JOptionPane.showMessageDialog(this, "Ya existe una persona con ese ID", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nombre = txtNombre.getText().trim();

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre es obligatorio", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Persona p = new Persona();
            p.setIdPersona(id); 
            p.setNombre(nombre);

            String mensaje = personaDao.registrarPersona(p);
            JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarPersona() {
        try {
            Long id = Long.parseLong(txtId.getText());
            Persona p = personaDao.consultarPersona(id);
            if (p != null) {
                textArea.setText(p.toString());
                txtNombre.setText(p.getNombre());
            } else textArea.setText("No encontrada");
        } catch (NumberFormatException ex) { textArea.setText("Id incorrecto"); }
    }

    private void actualizarPersona() {
        consultarPersona(); // carga datos
        Persona p = personaDao.consultarPersona(Long.parseLong(txtId.getText()));
        if (p != null) textArea.setText(personaDao.actualizarPersona(p));
    }

    private void eliminarPersona() {
        try {
            Long id = Long.parseLong(txtId.getText());
            Persona p = personaDao.consultarPersona(id);
            if (p != null) {
                textArea.setText(personaDao.eliminarPersona(p));
                limpiarCampos();
            } else textArea.setText("No encontrada");
        } catch (NumberFormatException ex) { textArea.setText("Id incorrecto"); }
    }

    private void listarPersonas() {
        List<Persona> list = personaDao.consultarListaPersonas();
        StringBuilder sb = new StringBuilder("Personas:\n");
        list.forEach(persona -> sb.append(persona).append("\n"));
        textArea.setText(sb.toString());
    }

    private void listarMascotas() {
        try {
            Long id = Long.parseLong(txtId.getText());
            Persona p = personaDao.consultarPersona(id);
            if (p != null) {
                StringBuilder sb = new StringBuilder("Mascotas de " + p.getNombre() + ":\n");
                p.getMascotas().forEach(m -> sb.append(m).append("\n"));
                textArea.setText(sb.toString());
            } else textArea.setText("Persona no encontrada");
        } catch (NumberFormatException ex) { textArea.setText("Id incorrecto"); }
    }

    private void limpiarCampos() {
        txtId.setText(""); txtNombre.setText(""); textArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPersona().setVisible(true));
    }
}
