package com.angela.aplicacion;

import java.util.List;
import javax.swing.JOptionPane;
import com.angela.dao.MascotaDao;
import com.angela.dao.PersonaDao;
import com.angela.entidades.Mascota;
import com.angela.entidades.Persona;

public class Aplicacion {
    MascotaDao mascotaDao = new MascotaDao();
    PersonaDao personaDao = new PersonaDao();

    public void iniciar() {
        String menu = "MENU DE OPCIONES\n\n";
        menu += "1. Registrar Persona\n";
        menu += "2. Consultar Persona\n";
        menu += "3. Eliminar Persona\n";
        menu += "4. Registrar Mascota\n";
        menu += "5. Consultar Mascota\n";
        menu += "6. Consultar Lista de Mascotas\n";
        menu += "7. Consultar Lista de Mascotas por sexo\n";
        menu += "8. Actualizar Mascota\n";
        menu += "9. Eliminar Mascota\n";
        menu += "10. Salir\n";

        int opc = 0;
        while (opc != 10) {
            try {
                opc = Integer.parseInt(JOptionPane.showInputDialog(menu));
                switch (opc) {
                    case 1: registrarPersona();
                    case 2: consultarPersona();
                    case 3: eliminarPersona();
                    case 4: registrarMascota();
                    case 5: consultarMascota();
                    case 6: consultarLista();
                    case 7: consultarListaPorSexo();
                    case 8: actualizarMascota();
                    case 9: eliminarMascota();
                    case 10: 
                    	mascotaDao.close();
                        personaDao.close();
                 
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }

    private void registrarPersona() {
        Persona persona = new Persona();
        persona.setIdPersona(Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID de la persona")));
        persona.setNombre(JOptionPane.showInputDialog("Ingrese el nombre de la persona"));
        System.out.println(personaDao.registrarPersona(persona));
    }

    private void consultarPersona() {
        Long id = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID de la persona"));
        Persona p = personaDao.consultarPersona(id);
        if (p != null) {
            System.out.println(p);
        } else {
            System.out.println("No se encontró la persona.");
        }
    }

    private void eliminarPersona() {
        Long id = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID de la persona"));
        Persona p = personaDao.consultarPersona(id);
        if (p != null) {
            System.out.println(personaDao.eliminarPersona(p));
        } else {
            System.out.println("No se encontró la persona.");
        }
    }

    private void registrarMascota() {
        Long idPersona = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID del dueño"));
        Persona dueno = personaDao.consultarPersona(idPersona);

        if (dueno != null) {
            Mascota mascota = new Mascota();
            mascota.setIdMascota(Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID de la mascota")));
            mascota.setNombre(JOptionPane.showInputDialog("Ingrese el nombre de la mascota"));
            mascota.setRaza(JOptionPane.showInputDialog("Ingrese la raza"));
            mascota.setColorMascota(JOptionPane.showInputDialog("Ingrese el color"));
            mascota.setSexo(JOptionPane.showInputDialog("Ingrese el sexo"));
            mascota.setDueno(dueno);

            System.out.println(mascotaDao.registrarMascota(mascota));
        } else {
            System.out.println("Dueño no encontrado.");
        }
    }

    private void consultarMascota() {
        Long id = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID de la mascota"));
        Mascota m = mascotaDao.consultarMascota(id);
        if (m != null) {
            System.out.println(m);
        } else {
            System.out.println("No se encontró la mascota.");
        }
    }

    private void consultarLista() {
        List<Mascota> lista = mascotaDao.consultarListaMascotas();
        System.out.println("Lista de mascotas:");
        lista.forEach(System.out::println);
    }

    private void consultarListaPorSexo() {
        String sexo = JOptionPane.showInputDialog("Ingrese el sexo a consultar (M/F):");
        List<Mascota> lista = mascotaDao.consultarListaMascotasPorSexo(sexo);
        System.out.println("Mascotas del sexo " + sexo + ":");
        lista.forEach(System.out::println);
    }

    private void actualizarMascota() {
        Long id = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID de la mascota"));
        Mascota m = mascotaDao.consultarMascota(id);
        if (m != null) {
            m.setNombre(JOptionPane.showInputDialog("Nuevo nombre", m.getNombre()));
            m.setRaza(JOptionPane.showInputDialog("Nueva raza", m.getRaza()));
            m.setColorMascota(JOptionPane.showInputDialog("Nuevo color", m.getColorMascota()));
            m.setSexo(JOptionPane.showInputDialog("Nuevo sexo", m.getSexo()));
            System.out.println(mascotaDao.actualizarMascota(m));
        } else {
            System.out.println("No se encontró la mascota.");
        }
    }

    private void eliminarMascota() {
        Long id = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID de la mascota"));
        Mascota m = mascotaDao.consultarMascota(id);
        if (m != null) {
            System.out.println(mascotaDao.eliminarMascota(m));
        } else {
            System.out.println("No se encontró la mascota.");
        }
    }

    public static void main(String[] args) {
        new Aplicacion().iniciar();
    }
}

