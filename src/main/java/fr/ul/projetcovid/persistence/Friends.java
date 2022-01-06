package fr.ul.projetcovid.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "friends", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id1", "id2"})
})
@NamedQueries({
        @NamedQuery(name = "Friends.of", query = "select distinct f from Friends f where f.account1.id = :id or f.account2.id = :id")
})
//@IdClass(FriendsId.class)
public class Friends implements Serializable {
    @Id
    @OneToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    @JoinColumn(name = "id1")
    private UserAccount account1;
    @Id
    @OneToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    @JoinColumn(name = "id2")
    private UserAccount account2;

    public UserAccount getAccount1() {
        return this.account1;
    }

    public UserAccount getAccount2() {
        return this.account2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friends friends = (Friends) o;
        return Objects.equals(account1, friends.account1) && Objects.equals(account2, friends.account2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account1, account2);
    }
}
