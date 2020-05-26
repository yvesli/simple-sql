package test;

import catalogs.DatabaseCatalog;
import interpreter.Interpreter;
import org.junit.jupiter.api.Test;
import query.SingleFileQueryReader;
import visitors.ProjectVisitor;

import java.io.File;
import java.io.IOException;

public class TestSelectOperator {

    @Test
    void randomTest() {
        StringBuilder sb = new StringBuilder();
        sb.append("some");
        sb.append(" thing");
        System.out.println(sb.toString());
    }

    @Test
    void testSelect() {
        String rootPath = "/Users/pro/Desktop/Courses/CS4321/CS4321-Projects/SQL/Materials/samples/input";
        DatabaseCatalog catalog = DatabaseCatalog.getInstance().init(rootPath);
        SingleFileQueryReader query = new SingleFileQueryReader(catalog.getQueryFileManager().getQueryFile());
        query.next();
    }

}
