package models;

public final class LoginModel {
    private String username;
    private String password;

    public LoginModel(String username, String password){
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
