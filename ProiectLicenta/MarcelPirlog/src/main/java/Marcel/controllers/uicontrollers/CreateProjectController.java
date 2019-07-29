package Marcel.controllers.uicontrollers;

import Marcel.App;
import Marcel.controllers.entitycontrollers.LocalProjectLocationController;
import Marcel.entities.LocalProjectLocation;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateProjectController {

    @FXML
    public TextField directoryPath;

    @FXML
    public Button serachButton;

    @FXML
    public TextField buttonPressMessage;

    @FXML
    public ChoiceBox<String> programmingLanguageOption;

    @FXML
    public boolean searchForFileInPath(ActionEvent actionEvent){
        System.out.println("Start search...");

        if(directoryPath.getText().isEmpty() || !(LocalProjectLocationController.checkIfThePathExists(Paths.get(directoryPath.getText())))){
            buttonPressMessage.setText("Please enter a valid path from your computer.");
            return false;
        }

        if(!(Files.isDirectory(Paths.get(directoryPath.getText())))){
            buttonPressMessage.setText("Please enter a valid directory path");
            
            return false;
        }
        programmingLanguageOption = new ChoiceBox<>(FXCollections.observableArrayList("Java","C", "Python"));
        App.getAppConfiguration().setLocalProjectLocation(new LocalProjectLocation(directoryPath.getText()));
        System.out.println("Atata:  " + programmingLanguageOption.getValue());
        System.out.println("Asta e: " + App.getAppConfiguration());
        return true;
    }
}
