package Marcel.controllers.uicontrollers;

import Marcel.App;
import Marcel.controllers.fxmlcontroller.FxmlController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfilePageController implements Initializable {
    @FXML public Label fullName;
    @FXML public Label year;
    @FXML public Label grupa;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.fullName.setText(App.getAppConfiguration().getStudent().getFirstName() + ' ' + App.getAppConfiguration().getStudent().getLastName());
        this.year.setText(String.valueOf(App.getAppConfiguration().getStudent().getYear()));
        this.grupa.setText(App.getAppConfiguration().getStudent().getGrupa());
    }

    @FXML public void toCreateProjectScreen() throws IOException {
        FxmlController.currentScene = new Scene(new FxmlController().loadFXML("/Marcel/CreateProject"));
        App.stage.setScene(FxmlController.currentScene);
    }
}
