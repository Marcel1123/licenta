package Marcel.entities;

import java.io.File;
import java.time.LocalDate;

public class CodeFile {
    private File file = null;
    private String name = null;
    private LocalDate create = null;
    private LocalDate lastModification = null;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreate() {
        return create;
    }

    public void setCreate(LocalDate create) {
        this.create = create;
    }

    public LocalDate getLastModification() {
        return lastModification;
    }

    public void setLastModification(LocalDate lastModification) {
        this.lastModification = lastModification;
    }
}
