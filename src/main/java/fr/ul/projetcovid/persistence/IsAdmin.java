package fr.ul.projetcovid.persistence;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "is_admin")
@NamedQueries({
        @NamedQuery(name = "IsAdmin.findById", query = "select i from IsAdmin i where i.account.id = :id")
})
public class IsAdmin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private UserAccount account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }
}