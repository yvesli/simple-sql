package schema;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SchemaLoader {

    private Scanner scanner;
    private final HashMap<String, Schema> schemaTable = new HashMap<>();

    public SchemaLoader(String path) {
        try {
            File file = new File(path);
            scanner = new Scanner(file);
            loadSchema();
        } catch (FileNotFoundException e) {
            System.out.println("Schema File Not Found");
            e.printStackTrace();
        }
    }

    public Schema getSchema(String tableName) throws IllegalArgumentException {
        if (!schemaTable.containsKey(tableName))
            throw new IllegalArgumentException("Invalid Table Name");
        return schemaTable.get(tableName);
    }

    private void loadSchema() {
        String line = nextLine();
        while (line != null) {
            ArrayList<String> tableSchema = parseLine(line);
            String tableName = tableSchema.get(0);
            tableSchema.remove(0);
            Schema schema = new Schema(tableSchema.toArray(new String[0]));
            schemaTable.put(tableName, schema);
            line = nextLine();
        }
    }

    private String nextLine() {
        if (scanner.hasNextLine())
            return scanner.nextLine();
        return null;
    }

    private ArrayList<String> parseLine(String line) {
        String[] parsedArray = line.split(" ");
        return new ArrayList<>(Arrays.asList(parsedArray));
    }

}
