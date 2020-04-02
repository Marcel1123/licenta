package marcel.pirlog.licenta.userManagement.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Studenti")
public class StudentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genname")
    @SequenceGenerator(name = "genname", sequenceName = "seqname", allocationSize = 1)
    @Column(name = "Id")
    private UUID Id;

    @Column(name = "Nume")
    private String firstName;

    @Column(name = "Prenume")
    private String lastName;

    @Column(name = "An")
    private int year;

    @Column(name = "Id_cont")
    private UUID accountId;

    @Override
    public String toString() {
        return "StudentEntity{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", year=" + year +
                ", accountId=" + accountId +
                '}';
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return year == that.year &&
                Id.equals(that.Id) &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                accountId.equals(that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, firstName, lastName, year, accountId);
    }
}
