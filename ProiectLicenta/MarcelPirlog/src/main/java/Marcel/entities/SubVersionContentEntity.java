package Marcel.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class SubVersionContentEntity {
    private UUID id;

    private String file;

    private String fName;

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
