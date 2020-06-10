package marcel.pirlog.licenta.userManagement.entities.person;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Profesori")
public class TeacherEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genname")
//    @SequenceGenerator(name = "genname", sequenceName = "seqname", allocationSize = 1)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @Column(name = "Email")
    private String email;

    public TeacherEntity(){
    }

    public TeacherEntity(UUID id, String email, PersonEntity person_id){
        this.id = id;
        this.person = person_id;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person_id) {
        this.person = person_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeacherEntity)) return false;
        TeacherEntity that = (TeacherEntity) o;
        return id.equals(that.id) &&
                person.equals(that.person) &&
                email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, person, email);
    }
}
