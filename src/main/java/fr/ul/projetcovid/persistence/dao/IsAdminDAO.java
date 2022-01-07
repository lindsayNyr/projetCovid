package fr.ul.projetcovid.persistence.dao;

import fr.ul.projetcovid.persistence.IsAdmin;
import fr.ul.projetcovid.persistence.UserAccount;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class IsAdminDAO {
    private final EntityManager em = Persistence.createEntityManagerFactory("default").createEntityManager();

    @Inject
    public IsAdminDAO() {
    }

    public boolean isAdmin(final UserAccount account) {
        final Query query = this.em.createNamedQuery("IsAdmin.findById", IsAdmin.class);
        query.setParameter("id", account.getId());
        return !query.getResultList().isEmpty();
    }


    public void removeAdminFK(final UserAccount account) {
        final Query query = this.em.createNamedQuery("IsAdmin.findById", IsAdmin.class);
        query.setParameter("id", account.getId());
        List isAdmin = query.getResultList();
        System.out.println(isAdmin.size());

        if (!isAdmin.isEmpty()) {
            this.em.getTransaction();
            this.em.remove(isAdmin.get(0));
            this.em.flush();
            this.em.clear();
            this.em.getTransaction().commit();
        }


    }

}
