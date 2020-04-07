package dataAccess;

import java.io.File;

public interface DatabaseHandler {

    File getTable(String name);
    DatabaseHandler init(String rootPath);

    static DatabaseHandler getInstance() {
        return Factory.create();
    }

}
