package Marcel.controllers.uicontrollers;

import Marcel.App;
import Marcel.controllers.entitycontrollers.LocalProjectLocationController;
import Marcel.controllers.fxmlcontroller.FxmlController;
import Marcel.entities.LocalProjectLocation;
import Marcel.entities.ProjectFiles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateProjectController {

    @FXML
    public TextField directoryPath;

    @FXML
    public Button serachButton;

    @FXML
    public Text responseMessage;

    @FXML
    public ChoiceBox<String> programmingLanguageOption;

    @FXML
    public AnchorPane rootPane;

    @FXML public Button backToProfileButton;

    @FXML
    public void searchForFileInPath(ActionEvent actionEvent) throws IOException {
        if(!(directoryPath.getText().isEmpty())){
            if(!(LocalProjectLocationController.checkIfThePathExists(Paths.get(directoryPath.getText()))) || !(Files.isDirectory(Paths.get(directoryPath.getText())))){
                responseMessage.setText("Write a directory path from your computer");
                responseMessage.setVisible(true);
            } else {
                responseMessage.setVisible(false);
                App.getAppConfiguration().setLocalProjectLocation(directoryPath.getText());
                App.getAppConfiguration().setProgrammingLanguageSelected(programmingLanguageOption.getValue());
                FxmlController.currentScene = new Scene(new FxmlController().loadFXML("/Marcel/ShowAllCodeFileFromPath"));
                App.stage.setScene(FxmlController.currentScene);
            }
        } else {
            responseMessage.setText("Write a directory path from your computer");
            responseMessage.setVisible(true);
        }
    }
    @FXML
    public void buildList(){
        if(programmingLanguageOption.getItems().size() == 0){
            programmingLanguageOption.getItems().add(".java");
            programmingLanguageOption.getItems().add(".c");
            programmingLanguageOption.getItems().add(".py");
        }
    }

    @FXML public void backToProfile() throws IOException {
        FxmlController.currentScene = new Scene(new FxmlController().loadFXML("/Marcel/ProfilePage"));
        App.stage.setScene(FxmlController.currentScene);
    }

}
