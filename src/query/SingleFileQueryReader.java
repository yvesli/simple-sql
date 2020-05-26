package query;

import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.ParseException;
import net.sf.jsqlparser.statement.Statement;

import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SingleFileQueryReader implements QueryReader {

    private File queryFile;
    private Statement statement;
    private CCJSqlParser parser;

    public SingleFileQueryReader(File queryFile) {
        try {
            this.queryFile = queryFile;
            parser = new CCJSqlParser(new FileReader(queryFile));
            statement = parser.Statement();
        } catch (FileNotFoundException | ParseException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean hasNext() {
        return statement != null;
    }

    @Override
    public Statement next() {
        Statement temp = statement;
        try {
            statement = parser.Statement();
        } catch (ParseException e) {
            return null;
        }
        return temp;
    }
}
