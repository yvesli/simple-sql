package interpreter;

import catalogs.DatabaseCatalog;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import operators.ScanOperator;
import operators.SelectOperator;
import schema.Schema;
import tools.ParseHelper;
import tuple.Tuple;

import java.io.File;
import java.io.IOException;

public class Interpreter {
    private static final Interpreter self = new Interpreter();
    private static DatabaseCatalog catalog = DatabaseCatalog.getInstance();
    private Statement statement;
    private File file;

    private Interpreter() {

    }

    public static Interpreter getInstance() {
        return self;
    }

    private void assertLoad() {
        if (statement == null || file == null)
            throw new AssertionError("no statement or file loaded, load them first");
    }

    public Interpreter load(Statement statement, File fileToWrite) {
        this.statement = statement;
        this.file = fileToWrite;
        return self;
    }

    public void run() throws IOException {
        assertLoad();
        System.out.println(statement);
        String tableName = ParseHelper.getFromItem(statement).toString();
        ScanOperator scanOperator = new ScanOperator(catalog.getDb().getTable(tableName));
        Schema schema = catalog.getSchemaLoader().getSchema(tableName);
        Expression whereClause = ParseHelper.getWhereClause(statement);
        SelectOperator selectOperator = new SelectOperator(scanOperator, whereClause, schema);
        Tuple tuple = selectOperator.getNextTuple();
        while (tuple != null) {
            System.out.println(tuple);
            tuple = selectOperator.getNextTuple();
        }
    }
}
