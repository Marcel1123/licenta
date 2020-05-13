package marcel.versionmanagement.models;

import java.util.UUID;

public class CreateGroupModel {
    private String name;
    private UUID teacherId;

    public CreateGroupModel(String name, UUID teacherId){
        this.name = name;
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public UUID getTeacherId() {
        return teacherId;
    }
}
