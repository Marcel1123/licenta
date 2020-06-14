package Marcel.entities;

import Marcel.entities.person.StudentEntity;

import java.util.List;
import java.util.UUID;

public class ProjectEntity {

    private UUID id;

    private String name;

    private StudentEntity studentId;

    private MaterialEntity materieId;

    private GroupEntity groupId;

    private String isFinal;

    private String plagiaryStatus;

//    private List<SubVersionEntity> versionEntities;

    public ProjectEntity(){
    }

    public ProjectEntity(UUID id, String name, StudentEntity studentId, MaterialEntity materieId, GroupEntity groupId, String isFinal, String plagiaryStatus){
        this.id = id;
        this.name = name;
        this.studentId = studentId;
        this.materieId = materieId;
        this.groupId = groupId;
        this.isFinal = isFinal;
        this.plagiaryStatus = plagiaryStatus;
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

    public StudentEntity getStudentId() {
        return studentId;
    }

    public void setStudentId(StudentEntity studentId) {
        this.studentId = studentId;
    }

    public MaterialEntity getMaterieId() {
        return materieId;
    }

    public void setMaterieId(MaterialEntity materieId) {
        this.materieId = materieId;
    }

    public GroupEntity getGroupId() {
        return groupId;
    }

    public void setGroupId(GroupEntity groupId) {
        this.groupId = groupId;
    }

    public String getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(String isFinal) {
        this.isFinal = isFinal;
    }

    public String getPlagiaryStatus() {
        return plagiaryStatus;
    }

    public void setPlagiaryStatus(String plagiaryStatus) {
        this.plagiaryStatus = plagiaryStatus;
    }

//    public List<SubVersionEntity> getVersionEntities() {
//        return versionEntities;
//    }
//
//    public void setVersionEntities(List<SubVersionEntity> versionEntities) {
//        this.versionEntities = versionEntities;
//    }

    @Override
    public String toString() {
        return name;
    }
}
