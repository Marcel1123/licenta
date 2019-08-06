package Marcel;

import Marcel.controllers.fxmlcontroller.FxmlController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static AppConfiguration appConfiguration = new AppConfiguration();

    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FxmlController.scene = new Scene(new FxmlController().loadFXML("/Marcel/ShowAllCodeFileFromPath"));
        stage.setScene(FxmlController.scene);
        App.stage = stage;
        App.stage.resizableProperty().setValue(Boolean.FALSE);
        App.stage.show();
    }

    @Override
    public void stop(){
        System.out.println("Safe stop...");
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