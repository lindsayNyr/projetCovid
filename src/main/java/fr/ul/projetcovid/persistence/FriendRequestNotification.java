package fr.ul.projetcovid.persistence;

import javax.persistence.*;

@Entity
@Table(name = "friend_request_notification")
public class FriendRequestNotification extends Notification {
    @ManyToOne(optional = false)
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
        return NotificationType.FRIEND_REQUEST;
    }
}