package path;

import java.util.Iterator;

public interface Path extends Iterator<String> {

    void storePath(String... path);
    Iterator<String> iterator();
    void reset();

}
