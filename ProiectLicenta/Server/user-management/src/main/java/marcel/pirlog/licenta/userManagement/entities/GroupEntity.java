package marcel.pirlog.licenta.userManagement.entities;

import marcel.pirlog.licenta.userManagement.entities.person.PersonEntity;
import marcel.pirlog.licenta.userManagement.entities.person.TeacherEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity(name = "GroupEntity")
@Table(name = "Grupuri")
public class GroupEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genname")
//    @SequenceGenerator(name = "genname", sequenceName = "seqname", allocationSize = 1)
    @Column(name = "id")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    private TeacherEntity creator;

    @Column(name = "Nume")
    private String name;

//    @ManyToMany(cascade = CascadeType.ALL)
    @ManyToMany
    @JoinTable(
            name = "group_memger",
            joinColumns = @JoinColumn(
                    name = "group_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "memgru_id",
                    referencedColumnName = "id"
            )
    )
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
