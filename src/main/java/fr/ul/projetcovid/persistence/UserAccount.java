package fr.ul.projetcovid.persistence;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"}),
        @UniqueConstraint(columnNames = {"login"})
})
@NamedQueries({
        @NamedQuery(name = "UserAccount.findByLogin", query = "select u from UserAccount u where u.login = :login"),
        @NamedQuery(name = "UserAccount.findById", query = "select u from UserAccount u where u.id = :id"),
        @NamedQuery(name = "UserAccount.findAllExceptMe", query = "select distinct  u from UserAccount u where u.id <> :id")

})


public class UserAccount implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false, length = 36)
    private String id;
    @Email(message = "Email invalide")
    @NotBlank(message = "Email ne peut pas être vide")
    @Column(name = "login", nullable = false, length = 50)
    private String login;
    @Column(name = "password", nullable = false, length = 256)
    private String password;
    @NotBlank(message = "Nom ne peut pas être vide")
    @Column(name = "nom", nullable = false, length = 50)
    private String nom;
    @NotBlank(message = "Prénom ne peut pas être vide")
    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;
    @Past(message = "La date de naissance doit être antérieure à aujourd'hui")
    @Temporal(TemporalType.DATE)
    @Column(name = "naissance", nullable = false)
    private Date naissance;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "id1"),
            inverseJoinColumns = @JoinColumn(name = "id2")
    )
    private List<UserAccount> friends = new ArrayList<>();
    @OneToMany(mappedBy = "recipient", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Notification> notifications = new ArrayList<>();

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    public List<UserAccount> getFriends() {
        return friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return Objects.equals(id, that.id) && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(naissance, that.naissance) && Objects.equals(friends, that.friends);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, nom, prenom, naissance, friends);
    }
}
