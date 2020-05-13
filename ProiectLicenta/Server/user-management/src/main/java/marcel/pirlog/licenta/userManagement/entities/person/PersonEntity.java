package marcel.pirlog.licenta.userManagement.entities.person;

import marcel.pirlog.licenta.userManagement.entities.AccountEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Person")
public class PersonEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genname")
//    @SequenceGenerator(name = "genname", sequenceName = "seqname", allocationSize = 1)
    @Column(name = "Id")
    @NotNull
    @NotEmpty
    private UUID Id;

    @Column(name = "Nume")
    @NotNull
    @NotEmpty
    private String firstName;

    @Column(name = "Prenume")
    @NotNull
    @NotEmpty
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_cont")
    @NotNull
    @NotEmpty
    private AccountEntity accountId;

    public PersonEntity() {
    }

    public PersonEntity(UUID id, String firstName, String lastName, AccountEntity accountId1){
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountId = accountId1;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
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
        return Id.equals(that.Id) &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                accountId.equals(that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, firstName, lastName, accountId);
    }
}
