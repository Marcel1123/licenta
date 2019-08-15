package Marcel.myutil;

import Marcel.App;
import Marcel.entities.ProjectFiles;

public class Utilitar {
    public static void getAllFile(ProjectFiles projectFiles){
        projectFiles.setProjectCodeFiles(SearchInDirectory.searchInDirectoryAndSubDirectory(App.getAppConfiguration().getLocalProjectLocation().getToString(),App.getAppConfiguration().getProgrammingLanguageSelected()));
    }

    public static void clearProjectFiles(ProjectFiles projectFiles){
        projectFiles.setProjectCodeFiles(null);
    }
}
