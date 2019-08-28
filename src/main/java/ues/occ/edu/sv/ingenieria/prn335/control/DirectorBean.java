/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.control;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ues.occ.edu.sv.ingenieria.prn335.cinedatalib.entities.Director;

/**
 *
 * @author keviin
 */
@Stateless
public class DirectorBean implements Serializable {

    @PersistenceContext(unitName = "cinePU")
    private EntityManager em;

    /**
     * ingresar datos
     *
     * @param Director
     */
    public void insert(Director director) {
        try {

            if (em != null) {
                em.persist(director);

                System.err.println("ID: " + director.getIdDirector() + " Nombre: " + director.getNombre());
            } else {
                throw new IllegalStateException("ENTIDAD VACIA, ERROR ENTITY MANAGER");
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public List<Director> selectAll() {
        try {
            if (em == null) {
                throw new IllegalStateException("ENTIDAD VACIA, ERROR ENTITY MANAGER");
            } else {
                List lista = em.createQuery("SELECT d FROM Director AS d").getResultList();
                return lista;
            }
        } catch (javax.persistence.NoResultException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);

        }
        return null;
    }

    /**
     * elimina datos
     *
     * @param menuConsumible
     */
    public void delete(Director director) {
        try {
            if (em == null) {
                throw new IllegalStateException("ENTIDAD VACIA, ERROR ENTITY MANAGER");
            } else {
                em.remove(em.merge(director));
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
