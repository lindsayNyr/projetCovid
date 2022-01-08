package fr.ul.projetcovid.persistence.dao;

import fr.ul.projetcovid.persistence.FriendRequestNotification;
import fr.ul.projetcovid.persistence.Notification;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
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
