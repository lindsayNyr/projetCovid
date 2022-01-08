package fr.ul.projetcovid.persistence;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "notification")
@Inheritance(strategy = InheritanceType.JOINED)
@MappedSuperclass
@DiscriminatorColumn(
        name="type",
        discriminatorType = DiscriminatorType.INTEGER
)
@NamedQueries({
        @NamedQuery(name = "Notification.findByRecipientId", query = "select distinct n from Notification n where n.recipient.id = :id")
})
public abstract class Notification implements Serializable {
    public enum NotificationType {
        COVID, FRIEND_REQUEST, BASIC
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "message", nullable = false)
    @NotBlank
    @NotNull
    private String message;
    @Column(name = "is_read", nullable = false)
    @NotNull
    private Boolean read = false;
    @ManyToOne(optional = false)
    @JoinColumn(name = "recipient_id", nullable = false)
    @NotNull
    private UserAccount recipient;

    public UserAccount getRecipient() {
        return recipient;
    }

    public void setRecipient(UserAccount recipient) {
        this.recipient = recipient;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract NotificationType getType();
}