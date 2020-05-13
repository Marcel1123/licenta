package Marcel.controllers.uicontrollers;

import Marcel.App;
import Marcel.AppConfiguration;
import Marcel.controllers.fxmlcontroller.FxmlController;
import Marcel.entities.GroupEntity;
import Marcel.models.StudentGroupModel;
import Marcel.myutil.HttpRequestAPI;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

public class ProfilePageController implements Initializable {
    @FXML
    public Label fullName;
    @FXML
    public Label year;
    @FXML
    public Button createProjectForAGroup;
    @FXML
    public Label groupError;

    private AppConfiguration appConfiguration = App.getAppConfiguration();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.fullName.setText(appConfiguration.getStudent().getPerson().getFirstName() + ' ' + appConfiguration.getStudent().getPerson().getLastName());
        this.year.setText(String.valueOf(App.getAppConfiguration().getStudent().getYear()));
    }

    @FXML public void toCreateProjectScreen() throws IOException, InterruptedException {
        HttpResponse response = HttpRequestAPI.GETMethodResponse("http://localhost:9091/group/", appConfiguration.getStudent().getPerson().getId().toString());

        if(response.statusCode() == HttpURLConnection.HTTP_OK && !response.body().toString().equals("[]")){
            groupError.setVisible(false);
            Gson g = new Gson();
            App.getAppConfiguration().setStudentGroupModels(g.fromJson(response.body().toString(), GroupEntity[].class));

            FxmlController.currentScene = new Scene(new FxmlController().loadFXML("/Marcel/CreateProject"));
            App.stage.setScene(FxmlController.currentScene);
        } else {
            groupError.setVisible(true);
        }
    }
}
