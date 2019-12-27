package marcel.pirlog.licenta.userManagement.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "GroupEntity")
@Table(name = "Grupuri")
public class GroupEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genname")
    @SequenceGenerator(name = "genname", sequenceName = "seqname", allocationSize = 1)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Id_creator")
    private UUID creatorId;

    @Column(name = "Nume")
    private String name;

    public GroupEntity(){
    }

    public GroupEntity(UUID id, UUID creatorId, String name) {
        this.id = id;
        this.creatorId = creatorId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "GroupEntity{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", name='" + name + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(UUID creatorId) {
        this.creatorId = creatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
