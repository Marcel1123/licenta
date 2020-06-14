package Marcel.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class SubVersionEntity {

    private UUID id;

    private List<SubVersionContentEntity> content;

    private ProjectEntity project;

    private LocalDateTime uploadDate;

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
