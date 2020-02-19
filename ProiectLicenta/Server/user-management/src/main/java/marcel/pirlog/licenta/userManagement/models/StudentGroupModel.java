package marcel.pirlog.licenta.userManagement.models;

import java.util.UUID;

public class StudentGroupModel {
    private UUID groupId;
    private String groupName;

    public StudentGroupModel(UUID groupId, String groupName){
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public UUID getGroupId() {
        return groupId;
    }

    public void setGroupId(UUID groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
