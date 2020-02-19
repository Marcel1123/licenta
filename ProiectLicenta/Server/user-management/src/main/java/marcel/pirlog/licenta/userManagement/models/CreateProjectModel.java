package marcel.pirlog.licenta.userManagement.models;

public final class CreateProjectModel {
    private String materialId;
    private String studentId;
    private String name;
    private String groupId;

    public CreateProjectModel(String materialId, String studentId, String name, String groupId){
        this.materialId = materialId;
        this.studentId = studentId;
        this.name = name;
        this.groupId = groupId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getGroupId() {
        return groupId;
    }
}