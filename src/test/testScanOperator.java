package test;

import catalogs.DatabaseCatalog;
import catalogs.QueryFileManager;
import dataAccess.DatabaseHandler;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import operators.ScanOperator;
import operators.SelectOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import query.QueryReader;
import query.SingleFileQueryReader;
import tuple.Tuple;

import java.io.File;
import java.io.FileNotFoundException;

public class testScanOperator {
    @Test
    void scanOperatorTest() {
        String rootPath = "/Users/pro/Desktop/Courses/CS4321/CS4321-Projects/SQL/Materials/samples/input";
        String tableName = "Boats";
        DatabaseHandler db = DatabaseCatalog.getInstance().init(rootPath).getDb();
        ScanOperator scan = new ScanOperator(db.getTable(tableName));
        Assertions.assertEquals(scan.getNextTuple().toString(), "(101,2,3)");
        Assertions.assertEquals(scan.getNextTuple().toString(), "(102,3,4)");
        Assertions.assertEquals(scan.getNextTuple().toString(), "(104,104,2)");
        scan.reset();
        Assertions.assertEquals(scan.getNextTuple().toString(), "(101,2,3)");
        Assertions.assertEquals(scan.getNextTuple().toString(), "(102,3,4)");
        Assertions.assertEquals(scan.getNextTuple().toString(), "(104,104,2)");
        scan.reset();
    }

    @Test
    void fromItemVisitorTest() {
        String rootPath = "/Users/pro/Desktop/Courses/CS4321/CS4321-Projects/SQL/Materials/samples/input";
        DatabaseCatalog catalog = DatabaseCatalog.getInstance().init(rootPath);
        File qFile = catalog.getQueryFileManager().getQueryFile();
        QueryReader queryReader = new SingleFileQueryReader(qFile);
        Select select = (Select) queryReader.next();
    }
}
