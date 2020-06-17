package entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class SubVersionEntity {

    private UUID id;

    private List<SubVersionContentEntity> content;

    private ProjectEntity project;

    private LocalDateTime uploadDate;

    private String compiling;

    private String fName;

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

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubVersionEntity)) return false;
        SubVersionEntity entity = (SubVersionEntity) o;
        return getId().equals(entity.getId()) &&
                getContent().equals(entity.getContent()) &&
                getProject().equals(entity.getProject()) &&
                getUploadDate().equals(entity.getUploadDate()) &&
                getCompiling().equals(entity.getCompiling()) &&
                getfName().equals(entity.getfName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent(), getProject(), getUploadDate(), getCompiling(), getfName());
    }
}
