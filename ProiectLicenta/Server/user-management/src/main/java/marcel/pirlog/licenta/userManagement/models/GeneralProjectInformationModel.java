package marcel.pirlog.licenta.userManagement.models;

public class GeneralProjectInformationModel {
    private String projectId;
    private String projectName;
    private String student;
    private String statusPlagiere;
    private String isFinal;

    public GeneralProjectInformationModel(){
    }

    public GeneralProjectInformationModel(String projectId, String projectName, String student, String statusPlagiere, String isFinal) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.student = student;
        this.statusPlagiere = statusPlagiere;
        this.isFinal = isFinal;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getStatusPlagiere() {
        return statusPlagiere;
    }

    public void setStatusPlagiere(String statusPlagiere) {
        this.statusPlagiere = statusPlagiere;
    }

    public String getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(String isFinal) {
        this.isFinal = isFinal;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
