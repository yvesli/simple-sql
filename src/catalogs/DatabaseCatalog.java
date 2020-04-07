package catalogs;

import dataAccess.DatabaseHandler;

public class DatabaseCatalog {

    private static final DatabaseCatalog self = new DatabaseCatalog();
    private static String rootPath;
    public DatabaseHandler db;

    private DatabaseCatalog() {

    }

    private void assertInit() {
        if (rootPath == null)
            throw new AssertionError("no root path specified, init with root path first");
    }

    public DatabaseCatalog init(String rootPath) {
        final String dbDirPath = rootPath + "/db/data/";
        DatabaseCatalog.rootPath = rootPath;
        db = DatabaseHandler.getInstance().init(dbDirPath);
        return self;
    }

    public static DatabaseCatalog getInstance() {
        return self;
    }

    public String getRootPath() {
        assertInit();
        return rootPath;
    }

}
