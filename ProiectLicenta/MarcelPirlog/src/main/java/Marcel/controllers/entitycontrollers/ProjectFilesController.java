package Marcel.controllers.entitycontrollers;

import Marcel.entities.CodeFile;
import Marcel.entities.ProjectFiles;

public class ProjectFilesController {
    public static void addNewFile(ProjectFiles projectFiles, CodeFile codeFile){
        projectFiles.getProjectCodeFiles().add(codeFile);
    }

//    public static void
}
