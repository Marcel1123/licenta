package marcel.compiling.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "Profesori")
public class TeacherEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genname")
    @SequenceGenerator(name = "genname", sequenceName = "seqname", allocationSize = 1)
    @Column(name = "Id")
    private UUID Id;

    @Column(name = "Nume")
    private String firstName;

    @Column(name = "Prenume")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Id_cont")
    private UUID accountId;

    public TeacherEntity(){

    }

    public TeacherEntity(UUID id, String firstName, String lastName, String email, UUID accountId){
        this.Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accountId = accountId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }
}
