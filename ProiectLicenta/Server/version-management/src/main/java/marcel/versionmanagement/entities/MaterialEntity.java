package marcel.versionmanagement.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "MaterialEntity")
@Table(name = "Materii")
public class MaterialEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genname")
//    @SequenceGenerator(name = "genname", sequenceName = "seqname", allocationSize = 1)
    @Column(name = "Id")
    @NotNull
    @NotEmpty
    private UUID id;

    @Column(name = "Nume")
    @NotNull
    @NotEmpty
    private String name;

    @Column(name = "An")
    @NotNull
    private int year;

    @Column(name = "Semestru")
    @NotNull
    @NotEmpty
    private String semester;

    public MaterialEntity(){
    }

    public MaterialEntity(UUID id, String name, int year, String semester){
        this.id = id;
        this.name = name;
        this.year = year;
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "MaterialEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", semester='" + semester + '\'' +
                '}';
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
