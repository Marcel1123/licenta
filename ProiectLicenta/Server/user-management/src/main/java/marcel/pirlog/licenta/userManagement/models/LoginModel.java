package marcel.pirlog.licenta.userManagement.models;

public class LoginModel {
    public String username;

    public String password;

    public LoginModel(String username, String password){
        this.username = username;
        this.password = password;
    }

    public LoginModel(LoginModel loginModel){
        this.password = loginModel.password;
        this.username = loginModel.username;
    }
}
