package catalogs;

import java.io.File;

public class QueryFileManager {

    private File file;

    QueryFileManager(String path) {
        file = new File(path);
    }

    public File getQueryFile() {
        return file;
    }

}
