package Marcel.controllers.uicontrollers;

import Marcel.App;
import Marcel.controllers.entitycontrollers.LocalProjectLocationController;
import Marcel.entities.LocalProjectLocation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
    public void searchForFileInPath(ActionEvent actionEvent){
        if(!(directoryPath.getText().isEmpty())){
            if(!(LocalProjectLocationController.checkIfThePathExists(Paths.get(directoryPath.getText()))) || !(Files.isDirectory(Paths.get(directoryPath.getText())))){
                responseMessage.setText("Write a directory path from your computer");
                responseMessage.setVisible(true);
            } else {
                responseMessage.setVisible(false);
                App.getAppConfiguration().setLocalProjectLocation(new LocalProjectLocation(directoryPath.getText()));
                App.getAppConfiguration().setProgrammingLanguageSelected(programmingLanguageOption.getValue());
            }
        } else {
            responseMessage.setText("Write a directory path from your computer");
            responseMessage.setVisible(true);
        }
    }
    @FXML
    public void buildList(){
        if(programmingLanguageOption.getItems().size() == 0){
            programmingLanguageOption.getItems().add("Java");
            programmingLanguageOption.getItems().add("C");
            programmingLanguageOption.getItems().add("Python");
        }
    }
//C:\Program Files\SceneBuilder
}
