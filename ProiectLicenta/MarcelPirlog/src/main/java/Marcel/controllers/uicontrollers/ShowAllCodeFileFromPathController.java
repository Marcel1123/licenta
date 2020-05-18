package Marcel.controllers.uicontrollers;

import Marcel.App;
import Marcel.AppConfiguration;
import Marcel.controllers.entitycontrollers.FileCodeController;
import Marcel.controllers.fxmlcontroller.FxmlController;
import Marcel.entities.FileCode;
import Marcel.models.ContentModel;
import Marcel.models.VersionModel;
import Marcel.myutil.HttpRequestAPI;
import Marcel.myutil.SearchInDirectory;
import com.google.gson.Gson;
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

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.*;

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

    private AppConfiguration appConfiguration = App.getAppConfiguration();
    private List<File> fileList = new LinkedList<>();
    private List<FileCode> fileCodes = new LinkedList<>();
    private List<ContentModel> contentList = new LinkedList<>();
    private long startTime = 0;
    private VersionModel versionModel = new VersionModel();
    private final Base64.Encoder encoder = Base64.getEncoder();

    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if(System.currentTimeMillis() - startTime > 120_000){
                fileCodes.clear();
                fileList.clear();
                contentList.clear();

                fileList = SearchInDirectory.searchInDirectoryAndSubDirectory(appConfiguration.getLocalProjectLocation().toString());
                fileCodes = FileCodeController.converToFileCode(fileList);

                updateFileListContent();

                sendFileToServer("false");

                listWithAllFiles.getItems().clear();
                listWithAllFiles.setItems(FXCollections.observableList(fileCodes));
                startTime = System.currentTimeMillis();
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

        animationTimer.start();
    }

    public void endThreadAction() throws IOException {
        animationTimer.stop();

        updateFileListContent();

        sendFileToServer("true");

        FxmlController.currentScene = new Scene(new FxmlController().loadFXML("/Marcel/CreateProject"));
        App.stage.setScene(FxmlController.currentScene);
    }

    private void updateFileListContent(){
        try{
            for(File file : fileList){
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fileInputStream.read(data);
                fileInputStream.close();
                contentList.add(new ContentModel(encoder.encodeToString(new String(data, "UTF-8").getBytes()), file.getName()));
            }
        } catch (FileNotFoundException fnfe){

        } catch (UnsupportedEncodingException e) {

        } catch (IOException e) {

        }
    }

    private void sendFileToServer(String string){
        if(contentList.size() > 0){
            String[] strings = new String[contentList.size()];
            versionModel = new VersionModel(UUID.fromString(appConfiguration.getProjectId()), contentList.toArray(new ContentModel[0]), string);
            Gson gson = new Gson();
            String bodyForRequest = gson.toJson(versionModel);
            try {
                HttpResponse response = HttpRequestAPI.POSTMethod("http://localhost:9093/version/", bodyForRequest);

                if(response.statusCode() != HttpURLConnection.HTTP_CREATED){
                    Thread.sleep(5000);
                    sendFileToServer(string);
                }
            } catch (IOException e) {
            } catch (InterruptedException e) {
            }
        }
    }
}
