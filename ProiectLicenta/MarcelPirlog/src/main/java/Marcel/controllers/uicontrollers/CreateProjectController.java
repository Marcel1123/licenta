package Marcel.controllers.uicontrollers;

import Marcel.App;
import Marcel.AppConfiguration;
import Marcel.controllers.entitycontrollers.LocalProjectLocationController;
import Marcel.controllers.fxmlcontroller.FxmlController;
import Marcel.models.CreateProjectModel;
import Marcel.models.MaterialModel;
import Marcel.models.StudentGroupModel;
import Marcel.myutil.HttpRequestAPI;
import Marcel.myutil.MapFromUserEntityToJson;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CreateProjectController implements Initializable {

    @FXML
    public TextField directoryPath;
    @FXML
    public Button serachButton;
    @FXML
    public Text responseMessage;
//    @FXML
//    public ChoiceBox<String> programmingLanguageOption;
    @FXML
    public AnchorPane rootPane;
    @FXML
    public Button backToProfileButton;
    @FXML
    public ChoiceBox<String> groupChoiceBox;
    @FXML
    public ChoiceBox<String> materialName;
    @FXML
    public TextField projectName;

    private List<String> groups = null;
    private boolean ok = true;
    private AppConfiguration appConfiguration = AppConfiguration.getInstance();

    @FXML
    public void searchForFileInPath(ActionEvent actionEvent) throws IOException, InterruptedException {
        if(verifyProjectInformation()){
            responseMessage.setVisible(false);
            if(groups.contains(groupChoiceBox.getValue())){
                createProjectModelForClass();
            } else {
                createProjectModelForGroup();
            }
        } else {
            responseMessage.setText("All fields are mandatory");
            responseMessage.setVisible(true);
        }
    }

    @FXML public void backToProfile() throws IOException {
        FxmlController.currentScene = new Scene(new FxmlController().loadFXML("/Marcel/ProfilePage"));
        App.stage.setScene(FxmlController.currentScene);
    }

    @FXML
    public void createMaterial(){
        if(groupChoiceBox.getValue() != null && groups.contains(groupChoiceBox.getValue()) && ok){
            try{
                HttpResponse response = HttpRequestAPI.GETMethodResponse("http://localhost:9091/material/", "");

                Gson g = new Gson();

                MaterialModel[] materials = g.fromJson(response.body().toString(), MaterialModel[].class);

                for(MaterialModel m : materials){
                    materialName.getItems().add(m.getName());
                }

                App.getAppConfiguration().setMaterialModels(materials);

                ok = false;
                materialName.setVisible(true);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } else if(groupChoiceBox.getValue() != null && !groups.contains(groupChoiceBox.getValue())){
            materialName.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        groups = new LinkedList<>();

        for(int i = 1; i <= 3; i++){
            for(int j = 1; j <= 6; j++){
                groups.add("" + i + "A" + j);
                groups.add("" + i + "B" + j);
            }
        }

//        programmingLanguageOption.getItems().add(".java");
//        programmingLanguageOption.getItems().add(".c");
//        programmingLanguageOption.getItems().add(".py");

        for(StudentGroupModel studentGroupModel : App.getAppConfiguration().getStudentGroupModels()){
            groupChoiceBox.getItems().add(studentGroupModel.getGroupName());
        }
    }

    private void createProjectModelForClass() throws IOException, InterruptedException {
        List<MaterialModel> materialId = Arrays.stream(App.getAppConfiguration().getMaterialModels())
                .filter(p -> p.getName().equals(materialName.getValue()))
                .collect(Collectors.toList());

        List<StudentGroupModel> studentGroupModels = Arrays.stream(appConfiguration.getStudentGroupModels())
                .filter(p -> p.getGroupName().equals(groupChoiceBox.getValue()))
                .collect(Collectors.toList());

        CreateProjectModel createProjectModel = new CreateProjectModel(materialId.get(0).getId(),
                                                                        appConfiguration.getStudent().getId().toString(),
                                                                        projectName.getText(),
                                                                        studentGroupModels.get(0).getGroupId().toString());

        materialId = null;
        studentGroupModels = null;

        HttpResponse response = HttpRequestAPI.POSTMethod("http://localhost:9091/project/", MapFromUserEntityToJson.returnCreateProjectInJson(createProjectModel));

        if(response.statusCode() == HttpURLConnection.HTTP_CREATED){
            responseMessage.setVisible(false);
            appConfiguration.setLocalProjectLocation(directoryPath.getText());

            appConfiguration.setProjectId(response.body().toString());

            FxmlController.currentScene = new Scene(new FxmlController().loadFXML("/Marcel/ShowAllCodeFileFromPath"));
            App.stage.setScene(FxmlController.currentScene);

        }
    }

    private void createProjectModelForGroup(){

    }

    private boolean verifyProjectInformation(){
        if(directoryPath.getText().isEmpty()){
            return false;
        }
        if(!LocalProjectLocationController.checkIfThePathExists(Paths.get(directoryPath.getText()))){
            return false;
        }
        if(projectName.getText().isEmpty()){
            return false;
        }
//        if(programmingLanguageOption.getValue() == null){
//            return false;
//        }
        if(groupChoiceBox.getValue() == null){
            return false;
        }
        return true;
    }
}
