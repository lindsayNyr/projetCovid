package fr.ul.projetcovid.persistence.dao;

import fr.ul.projetcovid.persistence.BasicNotification;
import fr.ul.projetcovid.persistence.CovidNotification;
import fr.ul.projetcovid.persistence.FriendRequestNotification;
import fr.ul.projetcovid.persistence.Notification;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Optional;

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
    public <T extends FriendRequestNotification> void markAccepted(final T notification) {
        this.em.getTransaction().begin();
        notification.setAccepted(true);
        this.em.getTransaction().commit();
    }

    @Transactional
    public <T extends Notification> void markRead(final T notification) {
        this.em.getTransaction().begin();
        notification.setRead(true);
        this.em.getTransaction().commit();
    }
}
