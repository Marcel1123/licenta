package marcel.pirlog.licenta.userManagement.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "GroupMemberEntity")
@Table(name = "Membri_Grup")
public class GroupMemberEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genname")
    @SequenceGenerator(name = "genname", sequenceName = "seqname", allocationSize = 1)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Id_membru")
    private UUID memberId;

    @Column(name = "Id_grup")
    private UUID groupId;

    public GroupMemberEntity(){
    }

    public GroupMemberEntity(UUID id, UUID memberId, UUID groupId){
        this.id = id;
        this.memberId = memberId;
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "GroupMemberEntity{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", groupId=" + groupId +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public void setMemberId(UUID memberId) {
        this.memberId = memberId;
    }

    public UUID getGroupId() {
        return groupId;
    }

    public void setGroupId(UUID groupId) {
        this.groupId = groupId;
    }
}
