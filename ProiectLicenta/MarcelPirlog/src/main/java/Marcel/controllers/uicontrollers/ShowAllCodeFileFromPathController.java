package Marcel.controllers.uicontrollers;

import Marcel.App;
import Marcel.controllers.entitycontrollers.FileCodeController;
import Marcel.entities.FileCode;
import Marcel.entities.ProjectFiles;
import Marcel.myutil.SearchInDirectory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ShowAllCodeFileFromPathController implements Initializable {
//    FileCode
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<FileCode> observableList = FXCollections.observableArrayList();
        fileName.setCellValueFactory(new PropertyValueFactory<FileCode, String>("fileName"));
        fileCreateDate.setCellValueFactory(new PropertyValueFactory<FileCode, LocalDateTime>("createDate"));
        fileLastUpdate.setCellValueFactory(new PropertyValueFactory<FileCode, LocalDateTime>("lastUpdate"));
        fileSize.setCellValueFactory(new PropertyValueFactory<FileCode, Integer>("size"));

        listWithAllFiles.setItems(seti());
        ProjectFiles projectFiles = new ProjectFiles();
        getAllFile(projectFiles);
        listWithAllFiles.setItems(FXCollections.observableList(FileCodeController.converToFileCode(projectFiles.getProjectCodeFiles())));
    }

    public ObservableList<FileCode> seti(){
        ObservableList<FileCode> observableList = FXCollections.observableArrayList();

        observableList.add(new FileCode("dasd",LocalDateTime.now(),LocalDateTime.now(),(long)12314));
        observableList.add(new FileCode("dasd",LocalDateTime.now(),LocalDateTime.now(),(long)12314));
        observableList.add(new FileCode("dasd",LocalDateTime.now(),LocalDateTime.now(),(long)12314));
        return  observableList;
    }

    private ObservableList<FileCode> fileCodeObservableList(ProjectFiles projectFiles){
        return FXCollections.observableList(FileCodeController.converToFileCode(projectFiles.getProjectCodeFiles()));
    }

    private void getAllFile(ProjectFiles projectFiles){
        projectFiles.setProjectCodeFiles(SearchInDirectory.searchInDirectoryAndSubDirectory(App.getAppConfiguration().getLocalProjectLocation().getToString(),App.getAppConfiguration().getProgrammingLanguageSelected()));
    }
}
//createDate;
//        lastUpdate;
//        roperty size;