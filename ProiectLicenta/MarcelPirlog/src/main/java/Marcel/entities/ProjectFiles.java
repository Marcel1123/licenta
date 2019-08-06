package Marcel.entities;

import java.io.File;
import java.util.HashSet;

public class ProjectFiles {
    private HashSet<File> projectCodeFiles = null;

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
