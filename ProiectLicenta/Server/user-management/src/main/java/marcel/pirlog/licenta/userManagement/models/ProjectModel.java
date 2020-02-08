package marcel.pirlog.licenta.userManagement.models;

public class ProjectModel {

    private String name;
    private String studentId;
    private String materieId;
    private String teacherId;

    public ProjectModel(String name,String studentId, String materieId){
        this.name = name;
        this.studentId = studentId;
        this.materieId = materieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getMaterieId() {
        return materieId;
    }

    public void setMaterieId(String materieId) {
        this.materieId = materieId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
