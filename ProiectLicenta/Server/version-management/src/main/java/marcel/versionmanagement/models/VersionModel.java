package marcel.versionmanagement.models;

import java.util.UUID;

public class VersionModel {
    private UUID projectId;
    private ContentModel[] codeFile;
    private String isFinal;

    public VersionModel(UUID projectId, ContentModel[] codeFile, String isFinal) {
        this.projectId = projectId;
        this.codeFile = codeFile;
        this.isFinal = isFinal;
    }

    public VersionModel(){

    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public String getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(String isFinal) {
        this.isFinal = isFinal;
    }

    public ContentModel[] getCodeFile() {
        return codeFile;
    }

    public void setCodeFile(ContentModel[] codeFile) {
        this.codeFile = codeFile;
    }
}
