package catalogs;

import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    private static final Properties config = new Properties();

    public ConfigLoader() {
        try {
            config.load(ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            System.out.println("config.properties not found");
            e.getStackTrace();
        }
    }

    public String getDatabasePath() {
        return config.getProperty("data");
    }

    public String getQueryPath() {
        return config.getProperty("query");
    }

    public String getSchemaPath() {
        return config.getProperty("schema");
    }

}
