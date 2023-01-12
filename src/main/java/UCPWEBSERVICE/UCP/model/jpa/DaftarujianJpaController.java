/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UCPWEBSERVICE.UCP.model.jpa;

import UCPWEBSERVICE.UCP.exceptions.NonexistentEntityException;
import UCPWEBSERVICE.UCP.exceptions.PreexistingEntityException;
import UCPWEBSERVICE.UCP.model.entity.Daftarujian;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ThinkPad
 */
public class DaftarujianJpaController implements Serializable {

    public DaftarujianJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("UCPWEBSERVICE_UCP_jar_0.0.1-SNAPSHOTPU");

    
    public DaftarujianJpaController() {
        
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Daftarujian daftarujian) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(daftarujian);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDaftarujian(daftarujian.getId()) != null) {
                throw new PreexistingEntityException("Daftarujian " + daftarujian + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Daftarujian daftarujian) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            daftarujian = em.merge(daftarujian);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = daftarujian.getId();
                if (findDaftarujian(id) == null) {
                    throw new NonexistentEntityException("The daftarujian with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Daftarujian daftarujian;
            try {
                daftarujian = em.getReference(Daftarujian.class, id);
                daftarujian.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The daftarujian with id " + id + " no longer exists.", enfe);
            }
            em.remove(daftarujian);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Daftarujian> findDaftarujianEntities() {
        return findDaftarujianEntities(true, -1, -1);
    }

    public List<Daftarujian> findDaftarujianEntities(int maxResults, int firstResult) {
        return findDaftarujianEntities(false, maxResults, firstResult);
    }

    private List<Daftarujian> findDaftarujianEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Daftarujian.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Daftarujian findDaftarujian(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Daftarujian.class, id);
        } finally {
            em.close();
        }
    }

    public int getDaftarujianCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Daftarujian> rt = cq.from(Daftarujian.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
