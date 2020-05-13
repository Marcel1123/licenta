package marcel.versionmanagement.entities;

import marcel.versionmanagement.entities.person.StudentEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity(name = "ProjectEntity")
@Table(name = "Proiect")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genname")
//    @SequenceGenerator(name = "genname", sequenceName = "seqname", allocationSize = 1)
    @Column(name = "Id")
    @NotNull
    @NotEmpty
    private UUID id;

    @Column(name = "nume")
    @NotNull
    @NotEmpty
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_student")
    @NotNull
    @NotEmpty
    private StudentEntity studentId;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "Id_materie")
    @NotNull
    @NotEmpty
    private MaterialEntity materieId;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "Id_group")
    @NotNull
    @NotEmpty
    private GroupEntity groupId;

    @Column(name = "Finalizat")
    @NotNull
    @NotEmpty
    private String isFinal;

    @Column(name = "Status_plagiere")
    private String plagiaryStatus;

//    @OneToMany(fetch = FetchType.LAZY)
    @OneToMany(cascade = CascadeType.DETACH)
    @NotNull
    @NotEmpty
    @JoinTable(name = "projet_version",
        joinColumns = @JoinColumn(
            name = "proj_id",
            referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
            name = "vers_id",
            referencedColumnName = "id"
        )
    )
    private List<SubVersionEntity> versionEntities;

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

    public List<SubVersionEntity> getVersionEntities() {
        return versionEntities;
    }

    public void setVersionEntities(List<SubVersionEntity> versionEntities) {
        this.versionEntities = versionEntities;
    }


}
