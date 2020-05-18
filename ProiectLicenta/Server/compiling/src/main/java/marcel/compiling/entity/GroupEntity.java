package marcel.compiling.entity;


import marcel.compiling.entity.person.PersonEntity;
import marcel.compiling.entity.person.TeacherEntity;

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
    @NotNull
    @NotEmpty
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    @NotEmpty
    private TeacherEntity creator;

    @Column(name = "Nume")
    @NotNull
    @NotEmpty
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
    @NotNull
    @NotEmpty
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
