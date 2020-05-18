package marcel.pirlog.licenta.userManagement.entities;

import org.hibernate.boot.jaxb.hbm.spi.SubEntityInfo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "SubVersionContentEntity")
@Table(name = "content")
public class SubVersionContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genname")
//    @SequenceGenerator(name = "genname", sequenceName = "seqname", allocationSize = 1)
    @Column(name = "id")
    @NotNull
    @NotEmpty
    private UUID id;

    @Column(name = "Cod_sura")
    @NotNull
    private String file;

    @Column(name = "file_name")
    @NotNull
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
