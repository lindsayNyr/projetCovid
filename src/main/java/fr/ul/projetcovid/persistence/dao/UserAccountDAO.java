package fr.ul.projetcovid.persistence.dao;

import fr.ul.projetcovid.persistence.UserAccount;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Stateless
public class UserAccountDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("default").createEntityManager();

    private UserAccount account;

    @Inject
    public UserAccountDAO() {
    }

    public void setAccount(final UserAccount account) {
        this.account = account;
    }

    @Transactional
    public void save() {
        em.getTransaction().begin();
        em.persist(this.account);
        em.getTransaction().commit();
    }
}
