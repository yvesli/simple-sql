package test;

import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import org.junit.jupiter.api.Test;
import query.QueryReader;
import query.SingleFileQueryReader;

import java.io.File;
import java.io.FileReader;

public class TestJqslParser {
    @Test
    void testParser() {
        try {
            String queriesFile = "/Users/pro/Desktop/Courses/CS4321/CS4321-Projects/SQL/Materials/samples/input/queries.sql";
            CCJSqlParser parser = new CCJSqlParser(new FileReader(queriesFile));
            Statement statement;
            while ((statement = parser.Statement()) != null) {
                System.out.println("Read statement: " + statement);
                Select select = (Select) statement;
                System.out.println("Select body is " + select.getSelectBody());
            }
        } catch (Exception e) {
            System.err.println("Exception occurred during parsing");
            e.printStackTrace();
        }
    }

    @Test
    void testQueryReader() {
        File file = new File("/Users/pro/Desktop/Courses/CS4321/CS4321-Projects/SQL/Materials/samples/input/queries.sql");
        QueryReader query = new SingleFileQueryReader(file);
        while (query.hasNext()) {
            System.out.println(query.next());
        }
    }
}
