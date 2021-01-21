package br.com.alura.springdata.service;

import br.com.alura.springdata.model.Position;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Service
public class JpaCrudPositionService {

    private EntityManagerFactory emf;
    private EntityManager em;

    public void JpaExample() {
        emf = Persistence.createEntityManagerFactory("jpa");
        em = emf.createEntityManager();
    }

    public void save(Position position) {
        em.getTransaction().begin();
        em.persist(position);
        em.getTransaction().commit();
        em.close();
    }

    public Position findById(Integer id) {
        em.getTransaction().begin();
        Position position = em.find(Position.class, id);
        em.getTransaction().commit();
        em.close();
        return position;
    }

    public void deleteById(Integer id) {
        Position position = em.find(Position.class, id);
        em.getTransaction().begin();
        em.remove(position);
        em.getTransaction().commit();
    }
}
