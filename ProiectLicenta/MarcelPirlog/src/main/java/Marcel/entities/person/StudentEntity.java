package Marcel.entities.person;

import java.io.Serializable;
import java.util.UUID;

public class StudentEntity implements Serializable {

    private UUID id;

    private PersonEntity person;

    private int year;

    public StudentEntity() {
    }

    public StudentEntity(UUID id, int year, PersonEntity person_id){
        this.id = id;
        this.person = person_id;
        this.year = year;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
