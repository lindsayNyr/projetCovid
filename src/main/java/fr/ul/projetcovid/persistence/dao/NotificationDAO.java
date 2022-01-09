package fr.ul.projetcovid.persistence.dao;

import fr.ul.projetcovid.persistence.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class NotificationDAO {
    private final EntityManager em = Persistence.createEntityManagerFactory("default").createEntityManager();

    @Inject
    public NotificationDAO() {
    }

    @Transactional
    public <T extends Notification> void sendNotification(final T anyNotification) {
        this.em.getTransaction().begin();
        this.em.persist(anyNotification);
        this.em.getTransaction().commit();
    }

    @Transactional
    public Optional<Notification> getNotificationById(final Long id) {
        final Query query1 = em.createNamedQuery("FriendRequestNotification.findById", FriendRequestNotification.class);
        final Query query2 = em.createNamedQuery("CovidNotification.findById", CovidNotification.class);
        final Query query3 = em.createNamedQuery("BasicNotification.findById", BasicNotification.class);

        for (Query q : new Query[] {query1, query2, query3}) {
            q.setParameter("id", id);
            Notification result = (Notification) q.getSingleResult();
            if (result != null)
                return Optional.of(result);
        }

        return Optional.empty();
    }

    @Transactional
    public <T extends FriendRequestNotification> void markAccepted(T notification) {
        notification = em.merge(notification);
        this.em.getTransaction().begin();
        notification.setAccepted(true);
        this.em.getTransaction().commit();
        em.refresh(notification);
    }

    @Transactional
    public <T extends Notification> void markRead(T notification) {
        notification = em.merge(notification);
        this.em.getTransaction().begin();
        notification.setRead(true);
        this.em.getTransaction().commit();
        em.refresh(notification);
    }

    @Transactional
    public void removeUserFKNotif(final UserAccount account){
        final List<Notification> notifications = account.getNotifications();

        em.getTransaction().begin();
        for(Notification n : notifications) {
            em.detach(n);
            if (!em.contains(n)) {
                em.remove(em.merge(n));

            }else{
                em.remove(n);
            }

        }
        em.flush();
        em.clear();
        em.getTransaction().commit();
    }


    @Transactional
    public boolean hasFriendRequestFrom(final UserAccount author, final UserAccount recipient) {
        final Query q = em.createNamedQuery("FriendRequestNotification.fromAccountIdTo", FriendRequestNotification.class);
        q.setParameter("id1", author.getId());
        q.setParameter("id2", recipient.getId());

        @SuppressWarnings("unchecked") List<FriendRequestNotification> results = q.getResultList();

        return results.stream().anyMatch(n -> !n.getAccepted());
    }
}
