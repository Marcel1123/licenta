package marcel.compiling.more;

public class CompilingModel {
    private String teacherId;
    private String versionId;

    public CompilingModel(String teacherId, String versionId) {
        this.teacherId = teacherId;
        this.versionId = versionId;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
