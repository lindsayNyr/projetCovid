package fr.ul.projetcovid.persistence;

import javax.persistence.*;

@Entity
@Table(name = "friend_request_notification")
@DiscriminatorValue(value="2")
@NamedQueries({
        @NamedQuery(name = "FriendRequestNotification.findById", query = "select f from FriendRequestNotification f where f.id = :id"),
        @NamedQuery(name = "FriendRequestNotification.fromAccountIdTo", query = "select f from FriendRequestNotification f where f.author.id = :id1 and f.recipient.id = :id2")
})
public class FriendRequestNotification extends Notification {
    @OneToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "author_id", nullable = false)
    private UserAccount author;
    @Column(name = "accepted", nullable = false)
    private Boolean accepted = false;

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public UserAccount getAuthor() {
        return author;
    }

    public void setAuthor(UserAccount author) {
        this.author = author;
    }

    @Override
    public NotificationType getType() {
        return NotificationType.FRIEND_REQUEST;
    }
}