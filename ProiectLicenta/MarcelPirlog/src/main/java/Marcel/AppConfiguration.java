package Marcel;

import Marcel.controllers.entitycontrollers.LocalProjectLocationController;
import Marcel.entities.LocalProjectLocation;
import Marcel.entities.Student;

public class AppConfiguration {
    private  LocalProjectLocation localProjectLocation = new LocalProjectLocation("C:\\Users\\User\\Documents\\GitHub\\E-Catalog\\Client");

//    private  LocalProjectLocationController localProjectLocationController = null;

    private  Student student = null;

    private String programmingLanguageSelected = ".java";

    public String getProgrammingLanguageSelected() {
        return programmingLanguageSelected;
    }

    public void setProgrammingLanguageSelected(String programmingLanguageSelected) {
        this.programmingLanguageSelected = programmingLanguageSelected;
    }

    public  LocalProjectLocation getLocalProjectLocation() {
        return localProjectLocation;
    }

    public  void setLocalProjectLocation(LocalProjectLocation localProjectLocation) {
        this.localProjectLocation = localProjectLocation;
    }

//    public  LocalProjectLocationController getLocalProjectLocationController() {
//        return localProjectLocationController;
//    }
//
//    public  void setLocalProjectLocationController(LocalProjectLocationController localProjectLocationController) {
//        this.localProjectLocationController = localProjectLocationController;
//    }

    public  Student getStudent() {
        return student;
    }

    public  void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "AppConfiguration{" +
                "localProjectLocation=" + localProjectLocation +
//                ", localProjectLocationController=" + localProjectLocationController +
                ", student=" + student +
                '}';
    }
}
