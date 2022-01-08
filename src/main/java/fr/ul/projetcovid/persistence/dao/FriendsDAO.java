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
        final List<UserAccount> friends = account.getFriends();

        em.getTransaction().begin();
        for(UserAccount f : friends) {
            em.remove(f);
        }
        em.flush();
        em.clear();
        em.getTransaction().commit();
    }

    @Transactional
    public void addFriendTo(final UserAccount myself, final UserAccount friend) {
        em.getTransaction().begin();
        final UserAccount myself1 = em.find(UserAccount.class, myself.getId());
        final UserAccount friend1 = em.find(UserAccount.class, friend.getId());

        final Friends fr1 = new Friends();
        fr1.setAccount1(myself1);
        fr1.setAccount2(friend1);
        em.persist(fr1);

        final Friends fr2 = new Friends();
        fr2.setAccount1(friend1);
        fr2.setAccount2(myself1);
        em.persist(fr2);

        em.getTransaction().commit();
    }

    @Transactional
    public void removeFriend(final UserAccount myself, final UserAccount friend) {
        Friends fr1 = new Friends();
        fr1.setAccount1(myself);
        fr1.setAccount2(friend);
        Friends fr2 = new Friends();
        fr2.setAccount1(friend);
        fr2.setAccount2(myself);

        fr1 = em.merge(fr1);
        fr2 = em.merge(fr2);

        em.getTransaction().begin();
        em.remove(fr1);
        em.remove(fr2);
        //em.flush();
        //em.clear();
        em.getTransaction().commit();
    }

}
