package marcel.pirlog.licenta.userManagement.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "ProjectEntity")
@Table(name = "Proiect")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genname")
    @SequenceGenerator(name = "genname", sequenceName = "seqname", allocationSize = 1)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "nume")
    private String name;

    @Column(name = "Id_student")
    private UUID studentId;

    @Column(name = "Id_materie")
    private UUID materieId;

    @Column(name = "Id_profesor")
    private UUID teacherId;

    @Column(name = "Id_group")
    private UUID groupId;

    @Column(name = "Finalizat")
    private String isFinal;

    @Column(name = "Status_compilare")
    private String compilationStatus;

    @Column(name = "Status_plagiere")
    private String plagiaryStatus;

    public ProjectEntity(){}

    public ProjectEntity(UUID id, String name, UUID studentId, UUID materieId, UUID teacherId, UUID groupId, String isFinal, String compilationStatus, String plagiaryStatus){
        this.id = id;
        this.name = name;
        this.studentId = studentId;
        this.materieId = materieId;
        this.teacherId = teacherId;
        this.groupId = groupId;
        this.isFinal = isFinal;
        this.compilationStatus = compilationStatus;
        this.plagiaryStatus = plagiaryStatus;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public UUID getMaterieId() {
        return materieId;
    }

    public void setMaterieId(UUID materieId) {
        this.materieId = materieId;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

    public String getCompilationStatus() {
        return compilationStatus;
    }

    public void setCompilationStatus(String compilationStatus) {
        this.compilationStatus = compilationStatus;
    }

    public String getPlagiaryStatus() {
        return plagiaryStatus;
    }

    public void setPlagiaryStatus(String plagiaryStatus) {
        this.plagiaryStatus = plagiaryStatus;
    }

    public String getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(String isFinal) {
        this.isFinal = isFinal;
    }

    public UUID getGroupId() {
        return groupId;
    }

    public void setGroupId(UUID groupId){
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
