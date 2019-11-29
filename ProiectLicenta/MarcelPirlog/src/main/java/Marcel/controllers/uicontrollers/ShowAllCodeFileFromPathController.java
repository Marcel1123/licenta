package Marcel.controllers.uicontrollers;

import Marcel.App;
import Marcel.controllers.entitycontrollers.FileCodeController;
import Marcel.entities.FileCode;
import Marcel.entities.ProjectFiles;
import Marcel.myutil.MyThread;
import Marcel.myutil.SearchInDirectory;
import Marcel.myutil.Utilitar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.Set;

public class ShowAllCodeFileFromPathController  implements Initializable {
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

    private MyThread watchThread;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<FileCode> observableList = FXCollections.observableArrayList();
        fileName.setCellValueFactory(new PropertyValueFactory<FileCode, String>("fileName"));
        fileCreateDate.setCellValueFactory(new PropertyValueFactory<FileCode, LocalDateTime>("createDate"));
        fileLastUpdate.setCellValueFactory(new PropertyValueFactory<FileCode, LocalDateTime>("lastUpdate"));
        fileSize.setCellValueFactory(new PropertyValueFactory<FileCode, Integer>("size"));

        Utilitar.getAllFile(App.getAppConfiguration().getProjectFiles());
        listWithAllFiles.setItems(FXCollections.observableList(FileCodeController.converToFileCode(App.getAppConfiguration().getProjectFiles().getProjectCodeFiles())));

        watchThread = new MyThread("Watch for modification", App.getAppConfiguration().getLocalProjectLocation().getToString());
//        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//        System.out.println("ALL THREAD: " + threadSet);
    }

    public void endThreadAction() {
        watchThread.stopWatch();
        watchThread = null;
    }
}
