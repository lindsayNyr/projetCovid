package fr.ul.projetcovid.persistence;

import java.io.Serializable;
import java.util.Objects;

public class FriendsId implements Serializable {
    private String account1;
    private String account2;

    public void setAccount1(final String account) {
        this.account1 = account;
    }

    public String getAccount1() {
        return account1;
    }

    public void setAccount2(final String account) {
        this.account2 = account;
    }

    public String getAccount2() {
        return account2;
    }

    public FriendsId() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendsId friendsId = (FriendsId) o;
        return Objects.equals(account1, friendsId.account1) && Objects.equals(account2, friendsId.account2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account1, account2);
    }
}
