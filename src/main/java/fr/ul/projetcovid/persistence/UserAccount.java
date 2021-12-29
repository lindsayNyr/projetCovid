package fr.ul.projetcovid.persistence;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"}),
        @UniqueConstraint(columnNames = {"login"})
})
@NamedQueries({
        @NamedQuery(name = "UserAccount.findAll", query = "select u from UserAccount u where u.login = :login")
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
    @Past
    @Temporal(TemporalType.DATE)
    @Column(name = "naissance", nullable = false)
    private Date naissance;

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
}
