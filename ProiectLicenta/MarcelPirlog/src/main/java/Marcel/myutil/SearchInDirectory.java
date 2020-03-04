package Marcel.myutil;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class SearchInDirectory {

    public static List<File> searchInDirectoryAndSubDirectory(String directoryName)  {
        List<File> fileCodes = new LinkedList<File>();
        File directory = new File(directoryName);
        File[] allFileFromDirector = directory.listFiles();
        if(allFileFromDirector != null){
            for (File file : allFileFromDirector) {
                if (file.isFile() && file.getName().endsWith(".java")) {
                    fileCodes.add(file);
                } else if (file.isDirectory()) {
                    fileCodes.addAll(searchInDirectoryAndSubDirectory(file.getAbsolutePath()));
                }
            }
        }
        return fileCodes;
    }
}
//C:\Users\parlo\Documents\GitHub\licenta\ProiectLicenta\Server