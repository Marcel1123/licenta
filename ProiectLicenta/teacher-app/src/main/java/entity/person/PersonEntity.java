package entity.person;


import entity.AccountEntity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class PersonEntity implements Serializable {

    private UUID id;

    private String firstName;

    private String lastName;

    private AccountEntity accountId;

    public PersonEntity() {
    }

    public PersonEntity(UUID id, String firstName, String lastName, AccountEntity accountId1){
        id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountId = accountId1;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AccountEntity getAccountId() {
        return accountId;
    }

    public void setAccountId(AccountEntity accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonEntity)) return false;
        PersonEntity that = (PersonEntity) o;
        return id.equals(that.id) &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                accountId.equals(that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, accountId);
    }
}
