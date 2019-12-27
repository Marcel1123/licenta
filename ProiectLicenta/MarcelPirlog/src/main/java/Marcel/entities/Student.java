package Marcel.entities;

import java.io.Serializable;
import java.util.UUID;

public class Student implements Serializable {
    private String firstName;
    private String lastName;
    private int year;
    private String grupa;
    private UUID accountId;
    private UUID id;

    public Student(){

    }

    public Student(String firstName, String lastName, int year, String grupa, String accountId, String id){
        this.id = UUID.fromString(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
        this.grupa = grupa;
        this.accountId = UUID.fromString(accountId);
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }
}
