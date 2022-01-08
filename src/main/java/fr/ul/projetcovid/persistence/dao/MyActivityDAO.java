package fr.ul.projetcovid.persistence.dao;

import fr.ul.projetcovid.persistence.Activity;
import fr.ul.projetcovid.persistence.MyActivity;
import fr.ul.projetcovid.persistence.UserAccount;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

@Stateless
public final class MyActivityDAO {


    private final EntityManager em = Persistence.createEntityManagerFactory("default").createEntityManager();

    @Inject
    public MyActivityDAO(){
    }


    @Transactional
    public void save(final MyActivity MyActivity) {
        em.getTransaction().begin();
        em.persist(MyActivity);
        em.getTransaction().commit();
    }





   /* @SuppressWarnings("UnusedReturnValue")
    @Transactional
    public MyActivity update(final MyActivity myActivity) {
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
    }*/






}
