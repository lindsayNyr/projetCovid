package fr.ul.projetcovid.persistence.dao;

import fr.ul.projetcovid.persistence.FriendRequestNotification;
import fr.ul.projetcovid.persistence.Notification;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

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
}
