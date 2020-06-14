package marcel.compiling.environement;

import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MemoryJavaFileObject extends SimpleJavaFileObject {

    private final CharSequence sourceCode;

    protected MemoryJavaFileObject(String className, CharSequence source) {
        super(makeUri(className), Kind.SOURCE);
        this.sourceCode = source;
    }

    private static URI makeUri(String className) {
        try {
            return new URI(className.replaceAll("\\.", "/") + Kind.SOURCE.extension);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return sourceCode;
    }
}
