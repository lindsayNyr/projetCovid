package fr.ul.projetcovid.persistence;

import javax.persistence.*;

@Entity
@Table(name = "basic_notification")
@DiscriminatorValue(value="3")
@NamedQueries({
        @NamedQuery(name = "BasicNotification.findById", query = "select b from BasicNotification b where b.id = :id")
})
public class BasicNotification extends Notification {
    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "author_id", nullable = false)
    private UserAccount author;

    public UserAccount getAuthor() {
        return author;
    }

    public void setAuthor(UserAccount author) {
        this.author = author;
    }

    @Override
    public NotificationType getType() {
        return NotificationType.BASIC;
    }
}