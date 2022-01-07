package fr.ul.projetcovid.persistence.dao;

import fr.ul.projetcovid.persistence.IsAdmin;
import fr.ul.projetcovid.persistence.UserAccount;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
}