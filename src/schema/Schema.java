package schema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Schema {

    private HashMap<String, Integer> schemaMap = new HashMap<>();
    private List<String> schemaList = new ArrayList<>();

    public Schema(String[] colArr) {
        for (int i = 0; i < colArr.length; i++) {
            schemaMap.put(colArr[i], i);
        }
        schemaList = Arrays.asList(colArr);
    }

    public Schema(Schema[] schemas) {

    }

    public int getColumn(String colName) throws IllegalArgumentException {
        if (!schemaMap.containsKey(colName))
            throw new IllegalArgumentException("Schema Does Not Contain " + colName);
        return schemaMap.get(colName);
    }

    @Override
    public String toString() {
        return schemaList.toString();
    }

}
