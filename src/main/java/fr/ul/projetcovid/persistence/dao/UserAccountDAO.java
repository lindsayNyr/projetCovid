package fr.ul.projetcovid.persistence.dao;

import fr.ul.projetcovid.persistence.UserAccount;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserAccountDAO {
    private final EntityManager em = Persistence.createEntityManagerFactory("default").createEntityManager();

    @Inject
    public UserAccountDAO() {
    }

    @Transactional
    public void save(final UserAccount account) {
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
    }

    @Transactional
    public Optional<UserAccount> getByLogin(final String login) {
        Query query = em.createNamedQuery("UserAccount.findByLogin", UserAccount.class);
        query.setParameter("login", login);
        List<?> accounts = query.getResultList();
        return Optional.ofNullable(accounts.isEmpty() ? null : (UserAccount) accounts.get(0));
    }

    @Transactional
    public Optional<UserAccount> getById(final String id) {
        Query query = em.createNamedQuery("UserAccount.findById", UserAccount.class);
        query.setParameter("id", id);
        List<?> accounts = query.getResultList();
        return Optional.ofNullable(accounts.isEmpty() ? null : (UserAccount) accounts.get(0));
    }
}
