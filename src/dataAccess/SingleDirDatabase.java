package dataAccess;

import java.io.File;

public class SingleDirDatabase implements DatabaseHandler {

    private static final SingleDirDatabase self = new SingleDirDatabase();
    private static String dbFolderPath = null;

    private SingleDirDatabase() {
    }

    private void assertInit() {
        if (dbFolderPath == null)
            throw new AssertionError("no root path specified, init with root path first");
    }

    private void setRootPath(String path) {
        if (dbFolderPath == null) {
            dbFolderPath = path;
        }
    }

    static SingleDirDatabase getInstance() {
        return self;
    }

    @Override
    public File getTable(String name) {
        assertInit();
        return new File(dbFolderPath + name);
    }

    @Override
    public DatabaseHandler init(String rootPath) {
        setRootPath(rootPath);
        return self;
    }

}
