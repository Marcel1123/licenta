package marcel.compiling.environement;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class MemoryFileManager extends ForwardingJavaFileManager<JavaFileManager> {
    private URI EMPTY;
    private Map<String, String> byteCodeForClasses1 = new HashMap<String, String>();
    private ByteArrayOutputStream byteCodeForClasses;
    protected MemoryFileManager(JavaFileManager fileManager, ByteArrayOutputStream byteCodeForClasses) {
        super(fileManager);
        try {
            EMPTY = new URI("");
        } catch (URISyntaxException e) {
            throw new Error(e);
        }
        this.byteCodeForClasses = byteCodeForClasses;
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, final String className, JavaFileObject.Kind kind, FileObject sibling){
        return new SimpleJavaFileObject(EMPTY, kind){
            public OutputStream openOutputStream() throws IOException{
                ByteArrayOutputStream outputStream = byteCodeForClasses;
                if(outputStream == null){
                    throw new IllegalStateException("write one only");
                }
                byte[] b = byteCodeForClasses.toByteArray();
                outputStream = new ByteArrayOutputStream(b.length);
                outputStream.write(b);
                return outputStream;
            }
        };
    }
}
