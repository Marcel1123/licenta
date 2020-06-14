package marcel.compiling.environement;

import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singleton;

public class MemoryCompilerClassLoader extends ClassLoader {
    private Map<String, ByteArrayOutputStream> byteCodeForClasses = new HashMap<String, ByteArrayOutputStream>();
    private URI EMPTY;

    public MemoryCompilerClassLoader(ClassLoader parent, String className, String source, DiagnosticListener<JavaFileObject> diagnosticListener) throws IOException {
        super(parent);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(256);
        outputStream.write(source.getBytes());
        byteCodeForClasses.put(className, outputStream);
    }

    @Override
    public Class findClass(String name) throws ClassNotFoundException {
        ByteArrayOutputStream byteCode = byteCodeForClasses.get(name);
        if (byteCode == null) {
            throw new ClassNotFoundException(name);
        }
        return defineClass(name, byteCode.toByteArray(), 0, byteCode.size());
    }

    public boolean compile(String className, CharSequence source, DiagnosticListener<JavaFileObject> diagnosticListener){
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        System.setProperty("useJavaUtilZip", "true");
        List<String> options = new LinkedList<String>();
        options.add("-XDuseJavaUtilZip");
        JavaFileObject javaFile = new MemoryJavaFileObject(className, source);
        MemoryFileManager fileManager = new MemoryFileManager(javaCompiler.getStandardFileManager(null, null,null), byteCodeForClasses.get(className));
        JavaCompiler.CompilationTask compilationTask = javaCompiler.getTask(null, fileManager, diagnosticListener, options, null, singleton(javaFile));
        return compilationTask.call();
    }

}
