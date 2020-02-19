package marcel.pirlog.licenta.userManagement.models;

import java.util.UUID;

public class GroupMemberModel {
    private UUID memberId;
    private UUID groupId;

    public GroupMemberModel(UUID memberId, UUID groupId){
        this.groupId = groupId;
        this.memberId = memberId;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public UUID getGroupId() {
        return groupId;
    }
}
