package test;

import catalogs.DatabaseCatalog;
import interpreter.Interpreter;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class
TestInterpretor {
    @Test
    void testBasic() throws IOException {
        DatabaseCatalog.getInstance().init("/Users/pro/Desktop/Courses/CS4321/CS4321-Projects/SQL/Materials/samples/input/");
        Interpreter interpreter = Interpreter.getInstance();
        interpreter.run();
    }
}
