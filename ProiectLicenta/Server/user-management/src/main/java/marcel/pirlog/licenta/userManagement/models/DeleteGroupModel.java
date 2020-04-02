package marcel.pirlog.licenta.userManagement.models;

public class DeleteGroupModel {
    private String id;
    private String teacherId;

    public DeleteGroupModel() {
    }

    public DeleteGroupModel(String id, String teacherId) {
        this.id = id;
        this.teacherId = teacherId;
    }

    public String getId() {
        return id;
    }

    public String getTeacherId() {
        return teacherId;
    }
}
