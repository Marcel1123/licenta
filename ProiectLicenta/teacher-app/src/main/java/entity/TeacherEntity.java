package entity;

import java.util.UUID;

public class TeacherEntity {
    private UUID Id;

    private String firstName;

    private String lastName;

    private String email;

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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public UUID getAccountId() {
        return accountId;
    }
}
