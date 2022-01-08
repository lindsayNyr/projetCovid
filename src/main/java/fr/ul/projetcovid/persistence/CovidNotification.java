package fr.ul.projetcovid.persistence;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "covid_notification")
public class CovidNotification extends Notification {
    @ManyToOne(optional = false)
    @JoinColumn(name = "source_id", nullable = false)
    private UserAccount source;

    public UserAccount getSource() {
        return source;
    }

    public void setSource(UserAccount source) {
        this.source = source;
    }

    @Override
    public NotificationType getType() {
        return NotificationType.COVID;
    }
}