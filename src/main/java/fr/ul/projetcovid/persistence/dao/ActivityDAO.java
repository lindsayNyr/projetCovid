package fr.ul.projetcovid.persistence.dao;

import fr.ul.projetcovid.persistence.Activity;
import fr.ul.projetcovid.persistence.UserAccount;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class ActivityDAO {


    private final EntityManager em = Persistence.createEntityManagerFactory("default").createEntityManager();

    @Inject
    public ActivityDAO() {
    }

    @Transactional
    public void save(final Activity activity) {
        em.getTransaction().begin();
        em.persist(activity);
        em.getTransaction().commit();
    }


    @Transactional
    public List<Activity> getAll() {
        final Query query = em.createNamedQuery("Activity.findAll", Activity.class);
        final List<Activity> activities = query.getResultList();
        return activities;
    }






}