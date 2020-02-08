package Marcel.controllers.uicontrollers;

import Marcel.App;
import Marcel.controllers.entitycontrollers.FileCodeController;
import Marcel.controllers.fxmlcontroller.FxmlController;
import Marcel.entities.FileCode;
import Marcel.myutil.SearchInDirectory;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ShowAllCodeFileFromPathController  implements Initializable {
    @FXML
    public TableView<FileCode> listWithAllFiles;
    @FXML
    public TableColumn<FileCode, String> fileName;
    @FXML
    public TableColumn<FileCode, LocalDateTime> fileCreateDate;
    @FXML
    public TableColumn<FileCode, LocalDateTime> fileLastUpdate;
    @FXML
    public TableColumn<FileCode, Integer> fileSize;
    @FXML
    public AnchorPane rootPane;
    @FXML
    public Button stopThread;

    private long startTime = System.currentTimeMillis();
    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if(System.currentTimeMillis() - startTime > 120_000){
                App.getAppConfiguration().setFiles(SearchInDirectory.searchInDirectoryAndSubDirectory(App.getAppConfiguration().getLocalProjectLocation().toString(), App.getAppConfiguration().getProgrammingLanguageSelected()));
                listWithAllFiles.getItems().clear();
                listWithAllFiles.setItems(FXCollections.observableList(FileCodeController.converToFileCode(App.getAppConfiguration().getFiles())));
            }
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<FileCode> observableList = FXCollections.observableArrayList();
        fileName.setCellValueFactory(new PropertyValueFactory<FileCode, String>("fileName"));
        fileCreateDate.setCellValueFactory(new PropertyValueFactory<FileCode, LocalDateTime>("createDate"));
        fileLastUpdate.setCellValueFactory(new PropertyValueFactory<FileCode, LocalDateTime>("lastUpdate"));
        fileSize.setCellValueFactory(new PropertyValueFactory<FileCode, Integer>("size"));

        App.getAppConfiguration().setFiles(SearchInDirectory.searchInDirectoryAndSubDirectory(App.getAppConfiguration().getLocalProjectLocation().toString(), App.getAppConfiguration().getProgrammingLanguageSelected()));
        listWithAllFiles.setItems(FXCollections.observableList(FileCodeController.converToFileCode(App.getAppConfiguration().getFiles())));

        animationTimer.start();
    }

    public void endThreadAction() throws IOException {
        animationTimer.stop();
        FxmlController.currentScene = new Scene(new FxmlController().loadFXML("/Marcel/CreateProject"));
        App.stage.setScene(FxmlController.currentScene);
    }
}
