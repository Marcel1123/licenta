package Marcel.controllers.uicontrollers;

import Marcel.App;
import Marcel.controllers.fxmlcontroller.FxmlController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginScreenController {
    @FXML
    public TextField usernameField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public Label errorLabel;
    @FXML
    public Button loginButton;

    @FXML
    public void LoginFunction() throws IOException {
        if(usernameField.getText().equals("marcel.pirlog") && passwordField.getText().equals("marcel")){
            System.out.println("Ok!");
            errorLabel.setVisible(false);
            FxmlController.currentScene = new Scene(new FxmlController().loadFXML("/Marcel/CreateProject"));
            App.stage.setScene(FxmlController.currentScene);
        } else {
            System.out.println("Not Ok!");
            errorLabel.setText("Invalid username or password!");
            errorLabel.setVisible(true);
        }
    }

    @FXML
    public  void CheckUsernameLength(){
        if(usernameField.getText().length() > 32){
            errorLabel.setText("Username too long!");
            errorLabel.setVisible(true);
        } else {
            errorLabel.setVisible(false);
        }
    }
}
