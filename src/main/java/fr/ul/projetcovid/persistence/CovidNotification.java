package fr.ul.projetcovid.persistence;

import javax.persistence.*;

@Entity
@Table(name = "covid_notification")
@DiscriminatorValue(value="1")
@NamedQueries({
        @NamedQuery(name = "CovidNotification.findById", query = "select c from CovidNotification c where c.id = :id")
})
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