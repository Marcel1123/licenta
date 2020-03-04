package marcel.pirlog.licenta.userManagement.entities;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "Versiuni")
@Entity(name = "SubVersionEntity")
public class SubVersionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genname")
    @SequenceGenerator(name = "genname", sequenceName = "seqname", allocationSize = 1)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Id_versiune")
    private UUID versionId;

    @Column(name = "Id_proiect")
    private UUID projectId;

    @Column(name = "Cod_sura")
    private String file;

    @Column(name = "Data_incarcarii")
    private LocalDateTime uploadDate;

    public SubVersionEntity(){
    }

    public SubVersionEntity(UUID id, UUID projectId, String file, LocalDateTime uploadDate){
        this.id = id;
        this.projectId = projectId;
        this.file = file;
        this.uploadDate = uploadDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }
}
