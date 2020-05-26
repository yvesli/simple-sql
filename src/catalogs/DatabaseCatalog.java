package catalogs;

import dataAccess.DatabaseHandler;
import schema.SchemaLoader;

public class DatabaseCatalog {

    private static final DatabaseCatalog self = new DatabaseCatalog();
    private static String rootPath;
    private static ConfigLoader conf;

    private DatabaseHandler db;
    private QueryFileManager queryFileManager;
    private SchemaLoader schemaLoader;


    private DatabaseCatalog() {
        conf = new ConfigLoader();
    }

    private void assertInit() {
        if (rootPath == null)
            throw new AssertionError("no root path specified, init with root path first");
    }

    public DatabaseCatalog init(String rootPath) {
        DatabaseCatalog.rootPath = rootPath;

        final String dbDirPath = rootPath + conf.getDatabasePath();
        final String queryPath = rootPath + conf.getQueryPath();
        final String schemaPath = rootPath + conf.getSchemaPath();

        db = DatabaseHandler.getInstance().init(dbDirPath);
        queryFileManager = new QueryFileManager(queryPath);
        schemaLoader = new SchemaLoader(schemaPath);
        return self;
    }

    public static DatabaseCatalog getInstance() {
        return self;
    }

    public String getRootPath() {
        assertInit();
        return rootPath;
    }

    public DatabaseHandler getDb() {
        assertInit();
        return db;
    }

    public QueryFileManager getQueryFileManager() {
        assertInit();
        return queryFileManager;
    }

    public SchemaLoader getSchemaLoader() {
        assertInit();
        return schemaLoader;
    }

}
