package test;

import catalogs.DatabaseCatalog;
import dataAccess.DatabaseHandler;
import interpreter.Interpreter;
import org.junit.jupiter.api.Test;
import query.SingleFileQueryReader;
import visitors.ProjectVisitor;

import java.io.File;
import java.io.IOException;

public class TestProjectOperator {
    @Test
    void testProjectVisitor() {
        String rootPath = "/Users/pro/Desktop/Courses/CS4321/CS4321-Projects/SQL/Materials/samples/input";
        DatabaseCatalog catalog = DatabaseCatalog.getInstance().init(rootPath);
        SingleFileQueryReader query = new SingleFileQueryReader(catalog.getQueryFileManager().getQueryFile());
        query.next();
        try {
            ProjectVisitor projectVisitor = new ProjectVisitor();
            query.next();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
