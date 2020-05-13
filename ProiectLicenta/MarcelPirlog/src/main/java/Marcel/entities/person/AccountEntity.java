package Marcel.entities.person;

import java.io.Serializable;
import java.util.UUID;


public class AccountEntity implements Serializable {

    private UUID id;

    private String username;

    private String password;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public AccountEntity(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public AccountEntity(){
        this.id = new UUID(0L,0L);
        this.username = "";
        this.password = "";
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
