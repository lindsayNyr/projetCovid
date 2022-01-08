package fr.ul.projetcovid.persistence.dao;

import fr.ul.projetcovid.persistence.Activity;
import fr.ul.projetcovid.persistence.UserAccount;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Stateless
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


    @Transactional
    public Activity getById(String id) {
        System.out.println(id);
        final Query query = em.createNamedQuery("Activity.findAll", Activity.class);
        Activity activity = em.find(Activity.class, id);
        return activity;

    }




    @Transactional
    public void delete(Activity activity) {
        final Query query = em.createNamedQuery("Activity.findAll", Activity.class);
        final List<Activity> activities = query.getResultList();

        em.getTransaction().begin();
        em.remove(activity);
        em.flush();
        em.clear();
        em.getTransaction().commit();

    }

    @SuppressWarnings("UnusedReturnValue")
    @Transactional
    public Activity update(final Activity activity) {


        final Activity activity2 = em.getReference(Activity.class, activity.getId());
        assert activity2 != null;

        em.getTransaction().begin();
        activity2.setName(activity.getName());

        em.getTransaction().commit();

        return activity2;
    }




}