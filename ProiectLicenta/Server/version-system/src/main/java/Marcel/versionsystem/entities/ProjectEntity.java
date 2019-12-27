package Marcel.versionsystem.entities;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

@Table(name = "Versiuni")
@Entity(name = "ProjectEntity")
public class ProjectEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;


    private ArrayList<File> files;

    public ProjectEntity() {
    }

    public ProjectEntity(long id, ArrayList<File> files) {
        this.id = id;
        this.files = files;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectEntity)) return false;
        ProjectEntity projectEntity = (ProjectEntity) o;
        return id == projectEntity.id &&
                files.equals(projectEntity.files);
    }

    @Override
    public String toString() {
        return "[id= " + id + "files= [" + files + "]]\n";
    }
}
