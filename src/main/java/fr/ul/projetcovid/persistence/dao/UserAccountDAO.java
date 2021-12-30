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
        final UserAccount account = em.find(UserAccount.class, id);
        return Optional.ofNullable(account);
    }

    @SuppressWarnings("UnusedReturnValue")
    @Transactional
    public UserAccount update(final UserAccount account) {
        final UserAccount account2 = em.getReference(UserAccount.class, account.getId());
        assert account2 != null;

        em.getTransaction().begin();
        account2.setLogin(account.getLogin());
        account2.setPrenom(account.getPrenom());
        account2.setNom(account.getNom());
        account2.setNaissance(account.getNaissance());
        account2.setPassword(account.getPassword());
        em.getTransaction().commit();

        return account2;
    }
}
