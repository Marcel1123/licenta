package Marcel;

import Marcel.controllers.fxmlcontroller.FxmlController;
import Marcel.controllers.uicontrollers.CreateProjectController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static AppConfiguration appConfiguration = new AppConfiguration();

    public Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FxmlController.scene = new Scene(new FxmlController().loadFXML("/Marcel/CreateProject"));
        stage.setScene(FxmlController.scene);
        this.stage = stage;
        stage.show();
    }

    public static AppConfiguration getAppConfiguration() {
        return appConfiguration;
    }

    public static void setAppConfiguration(AppConfiguration appConfiguration) {
        App.appConfiguration = appConfiguration;
    }

    public static void main(String[] args) {
        launch();
    }

}