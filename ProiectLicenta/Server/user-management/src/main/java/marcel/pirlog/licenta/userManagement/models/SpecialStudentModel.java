package marcel.pirlog.licenta.userManagement.models;

import java.io.Serializable;
import java.util.UUID;

public class SpecialStudentModel {
    private String firstName;
    private String lastName;
    private int year;
    private UUID id;

    public SpecialStudentModel(){

    }

    public SpecialStudentModel(String firstName, String lastName, int year, UUID id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UUID getId() {
        return id;
    }

    public int getYear() {
        return year;
    }
}
