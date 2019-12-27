package marcel.pirlog.licenta.userManagement.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "DidacticEntity")
@Table(name = "Didactic")
public class DidacticEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genname")
    @SequenceGenerator(name = "genname", sequenceName = "seqname", allocationSize = 1)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Id_profesor")
    private UUID teacherId;

    @Column(name = "Id_materie")
    private UUID materialId;

    @Column(name = "Grupa")
    private String group;

    public DidacticEntity(){
    }

    public DidacticEntity(UUID id, UUID teacherId, UUID materialId, String group){
        this.id = id;
        this.teacherId = teacherId;
        this.materialId = materialId;
        this.group = group;
    }

    @Override
    public String toString() {
        return "DidacticEntity{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", materialId=" + materialId +
                ", group='" + group + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

    public UUID getMaterialId() {
        return materialId;
    }

    public void setMaterialId(UUID materialId) {
        this.materialId = materialId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
