package Marcel;

import Marcel.entities.Student;
import Marcel.models.MaterialModel;
import Marcel.models.StudentGroupModel;

import java.nio.file.Path;

public final class AppConfiguration {

    private static final AppConfiguration appConfig = new AppConfiguration();

    private AppConfiguration(){
        this.localProjectLocation = null;
        this.student = null;
        this.studentGroupModels = null;
        this.materialModels = null;
        this.projectId = null;
    }

    private String projectId;

    private Path localProjectLocation;

    private  Student student;

    private StudentGroupModel[] studentGroupModels;

    private MaterialModel[] materialModels;

    public MaterialModel[] getMaterialModels() {
        return materialModels;
    }

    public void setMaterialModels(MaterialModel[] materialModels) {
        this.materialModels = materialModels;
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

    public static AppConfiguration getInstance(){
        return appConfig;
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
