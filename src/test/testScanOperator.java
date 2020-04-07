package test;

import catalogs.DatabaseCatalog;
import dataAccess.DatabaseHandler;
import operators.ScanOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class testScanOperator {
    @Test
    void scanOperatorTest() {
        String rootPath = "/Users/pro/Desktop/Courses/CS4321/CS4321-Projects/SQL/Materials/samples/input";
        String tableName = "Boats";
        DatabaseHandler db = DatabaseCatalog.getInstance().init(rootPath).db;
        try {
            ScanOperator scan = new ScanOperator(db.getTable(tableName));
            Assertions.assertEquals(scan.getNextTuple().toString(), "(101,2,3)");
            Assertions.assertEquals(scan.getNextTuple().toString(), "(102,3,4)");
            Assertions.assertEquals(scan.getNextTuple().toString(), "(104,104,2)");
            scan.reset();
            Assertions.assertEquals(scan.getNextTuple().toString(), "(101,2,3)");
            Assertions.assertEquals(scan.getNextTuple().toString(), "(102,3,4)");
            Assertions.assertEquals(scan.getNextTuple().toString(), "(104,104,2)");
            scan.dump();

        } catch (FileNotFoundException e) {
            System.out.println(tableName + " Not Found");
            e.printStackTrace();
        }
    }
}
