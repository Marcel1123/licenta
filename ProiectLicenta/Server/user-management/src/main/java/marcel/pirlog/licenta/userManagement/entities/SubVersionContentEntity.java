package marcel.pirlog.licenta.userManagement.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "SubVersionContentEntity")
@Table(name = "content")
public class SubVersionContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genname")
    @Column(name = "id")
    private UUID id;

    @Column(name = "Cod_sura")
    private String file;

    @Column(name = "file_name")
    private String fName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "vers_content",
            joinColumns = @JoinColumn(
                    name = "content_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "vers_id",
                    referencedColumnName = "id"
            )
    )
    private SubVersionEntity subVersionEntities;

    public SubVersionContentEntity() {
    }

    public SubVersionContentEntity(UUID id, String file, LocalDateTime uploadDate){
        this.id = id;
        this.file = file;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public SubVersionEntity getSubVersionEntities() {
        return subVersionEntities;
    }

    public void setSubVersionEntities(SubVersionEntity subVersionEntities) {
        this.subVersionEntities = subVersionEntities;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
}
