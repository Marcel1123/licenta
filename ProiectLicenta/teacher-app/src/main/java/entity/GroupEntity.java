package entity;

import entity.person.PersonEntity;
import entity.person.TeacherEntity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class GroupEntity implements Serializable {

    private UUID id;

    private TeacherEntity creator;

    private String name;

    private List<PersonEntity> groupMember;

    public GroupEntity(){
    }

    public GroupEntity(UUID id, TeacherEntity creator, String name, List<PersonEntity> groupMember) {
        this.id = id;
        this.creator = creator;
        this.name = name;
        this.groupMember = groupMember;
    }

    public TeacherEntity getCreator() {
        return creator;
    }

    public void setCreator(TeacherEntity creator) {
        this.creator = creator;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PersonEntity> getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(List<PersonEntity> groupMember) {
        this.groupMember = groupMember;
    }
}
