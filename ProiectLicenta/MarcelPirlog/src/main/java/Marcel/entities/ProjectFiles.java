package Marcel.entities;

import java.io.File;
import java.security.PrivilegedAction;
import java.util.HashSet;
import java.nio.file.Path;

public class ProjectFiles {
    private HashSet<File> projectCodeFiles = null;
    private HashSet<Path> projectFiles = null;

    // java.nio Path
    // !!!!!! Specificatii functionale
    // !!!!!! Schema bazei de date

    public void update(Path path){
        projectFiles.add(path);
    }

    public HashSet<File> getProjectCodeFiles() {
        return projectCodeFiles;
    }

    public void setProjectCodeFiles(HashSet<File> projectCodeFiles) {
        this.projectCodeFiles = projectCodeFiles;
    }

    public void addOtherList(HashSet<File> projectCodeFiles){
        this.projectCodeFiles.addAll(projectCodeFiles);
    }

    public void addNewFile(File projectCodeFiles){
        this.projectCodeFiles.add(projectCodeFiles);
    }
}