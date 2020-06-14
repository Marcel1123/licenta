package Marcel.controllers.uicontrollers;

import Marcel.App;
import Marcel.AppConfiguration;
import Marcel.controllers.fxmlcontroller.FxmlController;
import Marcel.entities.ProjectEntity;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.nio.file.Files;
import java.nio.file.Path;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class ContinueProjectController implements Initializable {
    @FXML
    public ListView<ProjectEntity> projList;

    @FXML
    public Label errorLabelLocation;

    @FXML
    public TextField newLocation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabelLocation.setVisible(false);
        projList.setItems(
                FXCollections.observableArrayList(AppConfiguration.getInstance().getProjectEntities())
        );
    }

    @FXML
    public void back() throws IOException {
        AppConfiguration.getInstance().setProjectEntities(null);
        projList = null;
        FxmlController.currentScene = new Scene(new FxmlController().loadFXML("/Marcel/ProfilePage"));
        App.stage.setScene(FxmlController.currentScene);
    }

    @FXML
    public void continueProject() throws IOException {
        ProjectEntity p = projList.getSelectionModel().getSelectedItem();
        if (p != null && verifyLocation(newLocation.getText())){
            AppConfiguration.getInstance().setLocalProjectLocation(newLocation.getText());
            AppConfiguration.getInstance().setProjectId(p.getId().toString());
            projList = null;
            AppConfiguration.getInstance().setProjectEntities(null);
            FxmlController.currentScene = new Scene(new FxmlController().loadFXML("/Marcel/ShowAllCodeFileFromPath"));
            App.stage.setScene(FxmlController.currentScene);
        }
    }

    private boolean verifyLocation(String location){
        if(location == null || location.isEmpty()){
            errorLabelLocation.setVisible(true);
            return false;
        }
        Path p = Paths.get(location);
        if(!Files.exists(p)){
            errorLabelLocation.setVisible(true);
            return false;
        }
        if(!Files.isDirectory(p)){
            errorLabelLocation.setVisible(true);
            return false;
        }
        return true;
    }
}
