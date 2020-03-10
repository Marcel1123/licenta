package marcel.pirlog.licenta.userManagement.models;

import java.util.UUID;

public class VersionModel {
    private UUID projectId;
    private String[] codeFile;
    private String isFinal;

    public VersionModel(UUID projectId, String[] codeFile, String isFinal){
        this.projectId = projectId;
        this.codeFile = codeFile;
        this.isFinal = isFinal;
    }

    public VersionModel(){

    }

    public UUID getProjectId() {
        return projectId;
    }

    public String[] getCodeFile() {
        return codeFile;
    }

    public String getIsFinal() {
        return isFinal;
    }
}
