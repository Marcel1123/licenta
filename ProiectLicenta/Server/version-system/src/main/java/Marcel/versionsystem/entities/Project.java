package Marcel.versionsystem.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;


public class Project {

    @Id
    @GeneratedValue
    private long id;
    private ArrayList<File> files;

    public Project() {
        id = 0;
        files = new ArrayList<>();
    }

    public Project(long id, ArrayList<File> files) {
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
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return id == project.id &&
                files.equals(project.files);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, files);
    }

    @Override
    public String toString() {
        return "[id= " + id + "files= [" + files + "]]\n";
    }
}
