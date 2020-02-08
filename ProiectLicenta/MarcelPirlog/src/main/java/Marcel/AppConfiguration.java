package Marcel;

import Marcel.entities.ProjectFiles;
import Marcel.entities.Student;

import java.io.File;
import java.nio.file.Path;
import java.util.HashSet;

public final class AppConfiguration {

    private Path localProjectLocation = null;

    private  Student student = null;

    private HashSet<File> files = null;

//    private ProjectFiles projectFiles = new ProjectFiles();

    private String programmingLanguageSelected = ".java";

    public String getProgrammingLanguageSelected() {
        return programmingLanguageSelected;
    }

    public void setProgrammingLanguageSelected(String programmingLanguageSelected) {
        this.programmingLanguageSelected = programmingLanguageSelected;
    }

    public Path getLocalProjectLocation() {
        return localProjectLocation;
    }

    public void setLocalProjectLocation(String localProjectLocation) {
        this.localProjectLocation = Path.of(localProjectLocation);
    }

    public  Student getStudent() {
        return student;
    }

    public  void setStudent(Student student) {
        this.student = student;
    }

//    public ProjectFiles getProjectFiles() {
//        return projectFiles;
//    }
//
//    public void setProjectFiles(ProjectFiles projectFiles) {
//        this.projectFiles = projectFiles;
//    }
    public HashSet<File> getFiles() {
        return files;
    }

    public void setFiles(HashSet<File> files) {
        this.files = files;
    }
}
