package Marcel;

import Marcel.entities.LocalProjectLocation;
import Marcel.entities.ProjectFiles;
import Marcel.entities.Student;

public class AppConfiguration {
    private  LocalProjectLocation localProjectLocation = new LocalProjectLocation("C:\\Users\\User\\Documents\\GitHub\\E-Catalog\\Client");
//    private LocalProjectLocation localProjectLocation = new LocalProjectLocation("C:\\Users\\User\\Documents\\GitHub\\licenta\\ProiectLicenta\\MarcelPirlog");
    private  Student student = null;

    private ProjectFiles projectFiles = new ProjectFiles();

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

    public  Student getStudent() {
        return student;
    }

    public  void setStudent(Student student) {
        this.student = student;
    }

    public ProjectFiles getProjectFiles() {
        return projectFiles;
    }

    public void setProjectFiles(ProjectFiles projectFiles) {
        projectFiles = projectFiles;
    }
}
