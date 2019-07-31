package Marcel.controllers.uicontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowAllCodeFileFromPathController implements Initializable {
    @FXML
    public TableView listWithAllFiles;
    @FXML
    public TableColumn filePosition;
    @FXML
    public TableColumn fileName;
    @FXML
    public TableColumn fileCreateDate;
    @FXML
    public TableColumn fileLastUpdate;
    @FXML
    public TableColumn fileSize;

    private static void populateTable(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
