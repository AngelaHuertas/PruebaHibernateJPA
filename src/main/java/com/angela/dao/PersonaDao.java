package com.angela.dao;

import java.util.List;
import com.angela.aplicacion.JPAUtil;
import com.angela.entidades.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class PersonaDao {
    private EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

    public String registrarPersona(Persona p) {
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        return "Persona registrada!";
    }

    public Persona consultarPersona(Long id) {
        return em.find(Persona.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Persona> consultarListaPersonas() {
        Query q = em.createQuery("SELECT p FROM Persona p");
        return q.getResultList();
    }

    public String actualizarPersona(Persona p) {
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        return "Persona actualizada!";
    }

    public String eliminarPersona(Persona p) {
        em.getTransaction().begin();
        em.remove(em.contains(p) ? p : em.merge(p));
        em.getTransaction().commit();
        return "Persona eliminada!";
    }

    public void close() {
        em.close();
        JPAUtil.shutdown();
    }
}