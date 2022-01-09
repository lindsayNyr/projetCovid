package fr.ul.projetcovid.persistence.dao;


import fr.ul.projetcovid.persistence.MyActivity;
import fr.ul.projetcovid.persistence.Place;
import fr.ul.projetcovid.persistence.Place;
import fr.ul.projetcovid.persistence.UserAccount;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class PlaceDAO {
    private final EntityManager em = Persistence.createEntityManagerFactory("default").createEntityManager();

    @Inject
    public PlaceDAO() {
    }


    @Transactional
    public void save(final Place place) {
        em.getTransaction().begin();
        em.persist(place);
        em.getTransaction().commit();
    }


    @Transactional
    public List<Place> getAll() {
        final Query query = em.createNamedQuery("Place.findAll", Place.class);
        final List<Place> place = query.getResultList();
        return place;
    }

    @Transactional
    public Place getById(String id) {
        Place Place = em.find(Place.class, id);
        return Place;

    }


    @Transactional
    public void remove(final Place place){
        em.getTransaction().begin();
        em.remove(place);
        em.flush();
        em.clear();
        em.getTransaction().commit();
    }



    @SuppressWarnings("UnusedReturnValue")
    @Transactional
    public Place update(final Place place) {
        final Place place2 = em.getReference(Place.class, place.getId());
        assert place2 != null;
        em.getTransaction().begin();
        place2.setName(place.getName());
        place2.setAdresse(place.getAdresse());
        place2.setCity(place.getCity());
        place2.setCodePostal(place.getCodePostal());
        em.getTransaction().commit();

        return place2;
    }
}
