package com.angela.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import com.angela.dao.MascotaDao;
import com.angela.dao.PersonaDao;
import com.angela.entidades.Mascota;
import com.angela.entidades.Persona;

public class VentanaMascota extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdMascota, txtIdPersona, txtNombre, txtRaza, txtColor, txtSexo;
    private JButton btnRegistrar, btnConsultar, btnActualizar, btnEliminar, btnConsultarLista, btnLimpiar, btnConsultarPorSexo;
    private JTextArea textArea;
    private MascotaDao mascotaDao = new MascotaDao();
    private PersonaDao personaDao = new PersonaDao();

    public VentanaMascota() {
        setTitle("Gestión de Mascotas");
        setResizable(false);
        setBounds(100, 100, 415, 577);
        setLocationRelativeTo(null);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        contentPane = new JPanel(); contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("MASCOTAS"); 
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16)); 
        lblTitulo.setBounds(170, 11, 100, 20);
        contentPane.add(lblTitulo);

        JLabel lblIdMascota = new JLabel("Id Mascota:"); 
        lblIdMascota.setFont(new Font("Arial", Font.PLAIN, 12)); 
        lblIdMascota.setBounds(20, 50, 70, 14);
        contentPane.add(lblIdMascota);
        
        txtIdMascota = new JTextField(); 
        txtIdMascota.setBounds(100, 47, 147, 20); 
        contentPane.add(txtIdMascota);

        JLabel lblIdPersona = new JLabel("Id Dueño:"); 
        lblIdPersona.setFont(new Font("Arial", Font.PLAIN, 12)); 
        lblIdPersona.setBounds(20, 80, 70, 14);
        contentPane.add(lblIdPersona);
        txtIdPersona = new JTextField(); 
        txtIdPersona.setBounds(100, 77, 147, 20); 
        contentPane.add(txtIdPersona);

        JLabel lblNombre = new JLabel("Nombre:"); 
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 12)); 
        lblNombre.setBounds(20, 110, 70, 14);
        contentPane.add(lblNombre);
        
        txtNombre = new JTextField(); 
        txtNombre.setBounds(100, 107, 147, 20); 
        contentPane.add(txtNombre);

        JLabel lblRaza = new JLabel("Raza:");
        lblRaza.setFont(new Font("Arial", Font.PLAIN, 12)); 
        lblRaza.setBounds(20, 140, 70, 14);
        contentPane.add(lblRaza);
        
        txtRaza = new JTextField(); 
        txtRaza.setBounds(100, 137, 147, 20); 
        contentPane.add(txtRaza);

        JLabel lblColor = new JLabel("Color:"); 
        lblColor.setFont(new Font("Arial", Font.PLAIN, 12)); 
        lblColor.setBounds(20, 170, 70, 14);
        contentPane.add(lblColor);
        txtColor = new JTextField(); 
        
        txtColor.setBounds(100, 167, 147, 20); 
        contentPane.add(txtColor);

        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setFont(new Font("Arial", Font.PLAIN, 12)); 
        lblSexo.setBounds(20, 200, 70, 14);
        contentPane.add(lblSexo);
        
        txtSexo = new JTextField(); 
        txtSexo.setBounds(100, 197, 147, 20); 
        contentPane.add(txtSexo);

        JSeparator separator = new JSeparator(); 
        separator.setBounds(20, 230, 355, 2); 
        contentPane.add(separator);

        btnRegistrar = new JButton("Registrar"); 
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 12)); 
        btnRegistrar.setBounds(20, 245, 174, 23); 
        btnRegistrar.addActionListener(this); 
        contentPane.add(btnRegistrar);
        
        btnConsultar = new JButton("Consultar"); 
        btnConsultar.setFont(new Font("Arial", Font.BOLD, 12)); 
        btnConsultar.setBounds(201, 245, 174, 23); 
        btnConsultar.addActionListener(this); 
        contentPane.add(btnConsultar);
        
        btnActualizar = new JButton("Actualizar"); 
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 12)); 
        btnActualizar.setBounds(20, 279, 174, 23); 
        btnActualizar.addActionListener(this); 
        contentPane.add(btnActualizar);
        
        btnEliminar = new JButton("Eliminar"); 
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 12)); 
        btnEliminar.setBounds(201, 279, 174, 23); 
        btnEliminar.addActionListener(this); 
        contentPane.add(btnEliminar);
        
        btnConsultarLista = new JButton("Lista Mascotas"); 
        btnConsultarLista.setFont(new Font("Arial", Font.BOLD, 12)); 
        btnConsultarLista.setBounds(20, 313, 174, 23); 
        btnConsultarLista.addActionListener(this); 
        contentPane.add(btnConsultarLista);
        
        btnConsultarPorSexo = new JButton("Mascotas por Sexo");
        btnConsultarPorSexo.setFont(new Font("Arial", Font.BOLD, 12));
        btnConsultarPorSexo.setBounds(20, 340, 355, 23);
        btnConsultarPorSexo.addActionListener(this);
        contentPane.add(btnConsultarPorSexo);
        
        btnLimpiar = new JButton("Limpiar"); 
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 12)); 
        btnLimpiar.setBounds(201, 313, 174, 23); 
        btnLimpiar.addActionListener(this); 
        contentPane.add(btnLimpiar);

        JScrollPane scrollPane = new JScrollPane(); scrollPane.setBounds(20, 375, 355, 175); contentPane.add(scrollPane);
        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) registrarMascota();
        else if (e.getSource() == btnConsultar) consultarMascota();
        else if (e.getSource() == btnActualizar) actualizarMascota();
        else if (e.getSource() == btnEliminar) eliminarMascota();
        else if (e.getSource() == btnConsultarLista) listarMascotas();
        else if (e.getSource() == btnLimpiar) limpiarCampos();
        else if (e.getSource() == btnConsultarPorSexo) consultarMascotasPorSexo();

    }

    private void consultarMascotasPorSexo() {
        String sexo = txtSexo.getText().trim();
        if (sexo.isEmpty()) {
            textArea.setText("Por favor ingrese un sexo para buscar.");
            return;
        }

        List<Mascota> lista = mascotaDao.consultarListaMascotasPorSexo(sexo);
        if (lista.isEmpty()) {
            textArea.setText("No se encontraron mascotas con sexo: " + sexo);
        } else {
            StringBuilder sb = new StringBuilder("Mascotas con sexo '" + sexo + "':\n");
            lista.forEach(m -> sb.append(m).append("\n"));
            textArea.setText(sb.toString());
        }
    }

    private void registrarMascota() {
        try {
            Long idM = Long.parseLong(txtIdMascota.getText());
            Long idP = Long.parseLong(txtIdPersona.getText());

            // Verificar si el ID de la mascota ya existe
            if (mascotaDao.consultarMascota(idM) != null) {
                JOptionPane.showMessageDialog(this, "Ya existe una mascota con ese ID", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar si el dueño existe
            Persona p = personaDao.consultarPersona(idP);
            if (p == null) {
                JOptionPane.showMessageDialog(this, "Dueño no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear mascota con ID manual
            Mascota m = new Mascota();
            m.setIdMascota(idM); 
            m.setNombre(txtNombre.getText());
            m.setRaza(txtRaza.getText());
            m.setColorMascota(txtColor.getText());
            m.setSexo(txtSexo.getText());
            m.setDueno(p);

            textArea.setText(mascotaDao.registrarMascota(m));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarMascota() {
        try {
            Long idM = Long.parseLong(txtIdMascota.getText());
            Mascota m = mascotaDao.consultarMascota(idM);
            if (m != null) {
                textArea.setText(m.toString());
                txtIdPersona.setText(m.getDueno().getIdPersona().toString());
                txtNombre.setText(m.getNombre());
                txtRaza.setText(m.getRaza());
                txtColor.setText(m.getColorMascota());
                txtSexo.setText(m.getSexo());
            } else textArea.setText("Mascota no encontrada");
        } catch (NumberFormatException ex) { textArea.setText("Id Mascota incorrecto"); }
    }

    private void actualizarMascota() {
        consultarMascota(); // carga datos
        try {
            Long idM = Long.parseLong(txtIdMascota.getText());
            Mascota m = mascotaDao.consultarMascota(idM);
            m.setNombre(txtNombre.getText()); m.setRaza(txtRaza.getText());
            m.setColorMascota(txtColor.getText()); m.setSexo(txtSexo.getText());
            textArea.setText(mascotaDao.actualizarMascota(m));
        } catch (Exception ex) { textArea.setText("Error al actualizar"); }
    }

    private void eliminarMascota() {
        try {
            Long idM = Long.parseLong(txtIdMascota.getText());
            Mascota m = mascotaDao.consultarMascota(idM);
            if (m != null) {
                textArea.setText(mascotaDao.eliminarMascota(m));
                limpiarCampos();
            } else textArea.setText("Mascota no encontrada");
        } catch (NumberFormatException ex) { textArea.setText("Id Mascota incorrecto"); }
    }

    private void listarMascotas() {
        List<Mascota> list = mascotaDao.consultarListaMascotas();
        StringBuilder sb = new StringBuilder("Mascotas:\n");
        list.forEach(m -> sb.append(m).append("\n"));
        textArea.setText(sb.toString());
    }

    private void limpiarCampos() {
        txtIdMascota.setText(""); txtIdPersona.setText(""); txtNombre.setText("");
        txtRaza.setText(""); txtColor.setText(""); txtSexo.setText(""); textArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaMascota().setVisible(true));
    }
}