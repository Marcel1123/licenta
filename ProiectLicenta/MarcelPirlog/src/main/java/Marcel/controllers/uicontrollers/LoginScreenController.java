package Marcel.controllers.uicontrollers;

import Marcel.App;
import Marcel.controllers.fxmlcontroller.FxmlController;
import Marcel.entities.Student;
import Marcel.myutil.HttpRequestAPI;
import Marcel.myutil.MapFromUserEntityToJson;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.http.HttpResponse;

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
    public void LoginFunction() throws IOException, InterruptedException {
        String credential = MapFromUserEntityToJson.returnCredentialsInJson(usernameField.getText(), passwordField.getText());

        HttpResponse httpResponse = HttpRequestAPI.POSTMethod("http://localhost:9091/login/student", credential);

        if(httpResponse.statusCode() == HttpURLConnection.HTTP_CREATED){
            String response = (String) httpResponse.body();

            if(response.isEmpty()){
                errorLabel.setText("Invalid username or password!");
                errorLabel.setVisible(true);
            } else {
                Gson g = new Gson();
                Reader reader = new StringReader(response);
                App.getAppConfiguration().setStudent(g.fromJson(reader, Student.class));

                FxmlController.currentScene = new Scene(new FxmlController().loadFXML("/Marcel/ProfilePage"));
                App.stage.setScene(FxmlController.currentScene);

                errorLabel.setText("");
                errorLabel.setVisible(false);
            }
        } else {
            errorLabel.setText("Invalid username or password!");
            errorLabel.setVisible(true);
        }
    }
}
