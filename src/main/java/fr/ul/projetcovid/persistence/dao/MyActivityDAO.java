package fr.ul.projetcovid.persistence.dao;

import fr.ul.projetcovid.persistence.Activity;
import fr.ul.projetcovid.persistence.MyActivity;
import fr.ul.projetcovid.persistence.Place;
import fr.ul.projetcovid.persistence.UserAccount;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Stateless
public final class MyActivityDAO {


    private final EntityManager em = Persistence.createEntityManagerFactory("default").createEntityManager();

    @Inject
    public MyActivityDAO(){
    }


    @Transactional
    public void save(final MyActivity MyActivity) {
        em.getTransaction().begin();
        em.persist(MyActivity);
        em.getTransaction().commit();
    }



    @Transactional
    public List<MyActivity> getAll() {
        final Query query = em.createNamedQuery("MyActivity.findAll", MyActivity.class);
        final List<MyActivity> activities = query.getResultList();
        return activities;
    }

    @Transactional
    public MyActivity getById(String id) {
        MyActivity myactivity = em.find(MyActivity.class, id);
        return myactivity;

    }


    @SuppressWarnings("UnusedReturnValue")
    @Transactional
    public MyActivity update(final MyActivity myActivity) {
        final MyActivity myActivity2 = em.getReference(MyActivity.class, myActivity.getId());
        assert myActivity2 != null;


        em.getTransaction().begin();
        myActivity2.setActivity(myActivity.getActivity());
        myActivity2.setPlace(myActivity.getPlace());
        myActivity2.setDate(myActivity.getDate());
        myActivity2.setEndTime(myActivity.getEndTime());
        myActivity2.setStartTime(myActivity.getStartTime());
        myActivity2.setUserAccount(myActivity.getUserAccount());
        em.getTransaction().commit();




        return myActivity2;
    }


    public static final long FIVE_DAYS = 5 * 24 * 3600 * 1000;

    @Transactional
    public List<MyActivity> recentActivitiesOf(final UserAccount account) {
        final Query query = em.createNamedQuery("MyActivity.findRecent", MyActivity.class);
        query.setParameter("id",  account.getId());
        query.setParameter("date", new Date(System.currentTimeMillis() - FIVE_DAYS)); // 5 days
        @SuppressWarnings("unchecked") final List<MyActivity> act = (List<MyActivity>) query.getResultList();

        return act;
    }

    @Transactional
    public List<MyActivity> locatedAt(final Place place) {
        final Query query = em.createNamedQuery("MyActivity.findLocatedAt", MyActivity.class);
        query.setParameter("id", place.getId());

        @SuppressWarnings("unchecked") List<MyActivity> activities = (List<MyActivity>) query.getResultList();
        return activities;
    }

}
