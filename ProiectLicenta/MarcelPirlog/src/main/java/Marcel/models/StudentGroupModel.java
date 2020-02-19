package Marcel.models;

import java.util.UUID;

public final class StudentGroupModel {
    private UUID groupId;
    private String groupName;

    public StudentGroupModel(){}

    public StudentGroupModel(UUID groupId, String groupName){
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public UUID getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }
}
