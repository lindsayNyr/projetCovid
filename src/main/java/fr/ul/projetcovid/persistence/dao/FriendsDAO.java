package fr.ul.projetcovid.persistence.dao;

import fr.ul.projetcovid.persistence.Friends;
import fr.ul.projetcovid.persistence.UserAccount;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public final class FriendsDAO {
    private final EntityManager em = Persistence.createEntityManagerFactory("default").createEntityManager();

    @Inject
    public FriendsDAO() {
    }

    @Transactional
    public void save(final Friends fr) {
        em.getTransaction().begin();
        em.persist(fr);
        em.getTransaction().commit();
    }

    @Transactional
    public void removeUserFKFriend(final UserAccount account){
        final Query query = em.createNamedQuery("Friends.of", Friends.class);
        query.setParameter("id", account.getId());
        @SuppressWarnings("unchecked") final List<Friends> friends = (List<Friends>) query.getResultList();

        em.getTransaction().begin();
        for(Friends f : friends) {
            em.remove(f);
        }
        em.flush();
        em.clear();
        em.getTransaction().commit();
    }

}
