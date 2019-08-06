package Marcel.myutil;

import Marcel.entities.FileCode;
import Marcel.entities.ProjectFiles;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class SearchInDirectory {

    public static HashSet<File> searchInDirectoryAndSubDirectory(String directoryName, String extension)  {
        HashSet<File> fileCodes = new HashSet<>();
        File directory = new File(directoryName);
        File[] allFileFromDirector = directory.listFiles();
        if(allFileFromDirector != null){
            for (File file : allFileFromDirector) {
                if (file.isFile() && file.getName().endsWith(extension)) {
                    fileCodes.add(file);
                } else if (file.isDirectory()) {
                    fileCodes.addAll(searchInDirectoryAndSubDirectory(file.getAbsolutePath(),extension));
                }
            }
        }
        return fileCodes;
    }
}
