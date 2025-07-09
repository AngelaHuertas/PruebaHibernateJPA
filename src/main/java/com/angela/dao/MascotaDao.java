package com.angela.dao;

import java.util.List;
import com.angela.aplicacion.JPAUtil;
import com.angela.entidades.Mascota;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class MascotaDao {
    private EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

    public String registrarMascota(Mascota m) {
        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
        return "Mascota registrada!";
    }

    public Mascota consultarMascota(Long id) {
        return em.find(Mascota.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Mascota> consultarListaMascotas() {
        Query q = em.createQuery("SELECT m FROM Mascota m");
        return q.getResultList();
    }

    public String actualizarMascota(Mascota m) {
        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
        return "Mascota actualizada!";
    }

    public String eliminarMascota(Mascota m) {
        em.getTransaction().begin();
        em.remove(em.contains(m) ? m : em.merge(m));
        em.getTransaction().commit();
        return "Mascota eliminada!";
    }

    public void close() {
        em.close();
        JPAUtil.shutdown();
    }

    public List<Mascota> consultarListaMascotasPorSexo(String sexo) {
        Query q = em.createQuery("SELECT m FROM Mascota m WHERE m.sexo = :sexo");
        q.setParameter("sexo", sexo);
        return q.getResultList();
    }
}

