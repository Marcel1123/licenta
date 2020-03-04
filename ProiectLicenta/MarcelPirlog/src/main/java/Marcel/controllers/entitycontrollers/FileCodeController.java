package Marcel.controllers.entitycontrollers;

import Marcel.entities.FileCode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class FileCodeController {
    public static List<FileCode> converToFileCode(List<File> source){
        List<FileCode> destination = new LinkedList<>();
        for (File file : source) {
            try{
                BasicFileAttributes basicFileAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                destination.add(new FileCode(file.getName(), basicFileAttributes.creationTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                                                           , basicFileAttributes.lastModifiedTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                                                           , basicFileAttributes.size()));
            } catch (IOException e) {
                System.out.println("Invalid file");
            }
        }
        return destination;
    }
}
