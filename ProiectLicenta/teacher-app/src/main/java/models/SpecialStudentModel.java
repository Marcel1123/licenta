package models;

import java.util.Objects;
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

    @Override
    public String toString() {
        return firstName + ' ' + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialStudentModel that = (SpecialStudentModel) o;
        return year == that.year &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, year, id);
    }
}