package marcel.versionmanagement.models;

public class AddMemberModel {
    private String teacherId;
    private String groupId;
    private String studentId;

    public AddMemberModel(){
    }

    public AddMemberModel(String teacherId, String groupId, String studentId) {
        this.teacherId = teacherId;
        this.groupId = groupId;
        this.studentId = studentId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getStudentId() {
        return studentId;
    }
}