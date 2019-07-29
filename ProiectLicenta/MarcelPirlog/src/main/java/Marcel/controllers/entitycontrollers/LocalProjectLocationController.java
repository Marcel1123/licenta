package Marcel.controllers.entitycontrollers;

import Marcel.entities.LocalProjectLocation;

import java.nio.file.Files;
import java.nio.file.Path;

public class LocalProjectLocationController {

    public static boolean checkIfThePathExists(Path localProjectLocation){
        if(Files.exists(localProjectLocation)){
            return true;
        }
        return false;
    }


}
