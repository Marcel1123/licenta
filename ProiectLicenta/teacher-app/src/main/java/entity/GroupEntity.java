package entity;

import java.io.Serializable;
import java.util.Objects;

public class GroupEntity implements Serializable {
    private String name;
    private String creatorId;
    private String id;

    public GroupEntity(){

    }

    public GroupEntity(String name, String creatorId, String id){
        this.id = id;
        this.creatorId = creatorId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupEntity that = (GroupEntity) o;
        return name.equals(that.name) &&
                creatorId.equals(that.creatorId) &&
                id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, creatorId, id);
    }
}
