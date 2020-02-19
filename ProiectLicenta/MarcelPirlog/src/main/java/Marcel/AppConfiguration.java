package Marcel;

import Marcel.entities.Student;
import Marcel.models.MaterialModel;
import Marcel.models.StudentGroupModel;

import java.io.File;
import java.nio.file.Path;
import java.util.HashSet;

public final class AppConfiguration {

    private Path localProjectLocation = null;

    private  Student student = null;

    private HashSet<File> files = null;

    private String programmingLanguageSelected = ".java";

    private StudentGroupModel[] studentGroupModels = null;

    private MaterialModel[] materialModels = null;

    public MaterialModel[] getMaterialModels() {
        return materialModels;
    }

    public void setMaterialModels(MaterialModel[] materialModels) {
        this.materialModels = materialModels;
    }

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

    public HashSet<File> getFiles() {
        return files;
    }

    public void setFiles(HashSet<File> files) {
        this.files = files;
    }

    public void setLocalProjectLocation(Path localProjectLocation) {
        this.localProjectLocation = localProjectLocation;
    }

    public StudentGroupModel[] getStudentGroupModels() {
        return studentGroupModels;
    }

    public void setStudentGroupModels(StudentGroupModel[] studentGroupModels) {
        this.studentGroupModels = studentGroupModels;
    }
}
