package marcel.compiling.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "SubVersionEntity")
@Table(name = "Versiuni")
public class SubVersionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genname")
//    @SequenceGenerator(name = "genname", sequenceName = "seqname", allocationSize = 1)
    @Column(name = "Id")
    @NotNull
    @NotEmpty
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL)
    @NotNull
    @NotEmpty
    @JoinTable(
            name = "vers_content",
            joinColumns = @JoinColumn(
                    name = "vers_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "content_id",
                    referencedColumnName = "id"
            )
    )
    private List<SubVersionContentEntity> content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "projet_version",
            joinColumns = @JoinColumn(
                    name = "vers_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "proj_id",
                    referencedColumnName = "id"
            )
    )
    @NotNull
    @NotEmpty
    private ProjectEntity project;

    @Column(name = "Data_incarcarii")
    @NotNull
    @NotEmpty
    private LocalDateTime uploadDate;

    @Column(name = "compiling")
    @NotNull
    @NotEmpty
    private String compiling;

    public SubVersionEntity() {
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<SubVersionContentEntity> getContent() {
        return content;
    }

    public void setContent(List<SubVersionContentEntity> content) {
        this.content = content;
    }

    public String getCompiling() {
        return compiling;
    }

    public void setCompiling(String compiling) {
        this.compiling = compiling;
    }
}
