package Marcel.models;

import java.util.UUID;

public final class VersionModel {
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
}
