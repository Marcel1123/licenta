package entity.person;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class TeacherEntity implements Serializable {
    private UUID id;

    private PersonEntity person;

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
