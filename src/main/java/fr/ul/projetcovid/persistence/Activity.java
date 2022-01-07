package fr.ul.projetcovid.persistence;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "activity", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"}),
        @UniqueConstraint(columnNames = {"name"})
})

@NamedQueries({
        @NamedQuery(name = "Activity.findAll", query = "select distinct a from Activity a")

})
public class Activity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false, length = 36)
    private String id;


    @NotBlank(message = "l'activité ne peut pas être vide")
    @Column(name = "name", nullable = false, length = 50)
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(id, activity.id) &&
                Objects.equals(name, activity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
