package entity;

import java.time.LocalDateTime;

public class SubVersionEntity {
    private String id;
    private String versionId;
    private String projectId;
    private String file;
    private LocalDateTime uploadDate;

    public SubVersionEntity(){
    }

    public SubVersionEntity(String id, String projectId, String versionId, String file, LocalDateTime uploadDate){
        this.id = id;
        this.projectId = projectId;
        this.file = file;
        this.uploadDate = uploadDate;
        this.versionId = versionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
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

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
}
