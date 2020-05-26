package visitors;

import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItemVisitor;
import schema.Schema;
import tuple.Tuple;

public class ProjectVisitor implements SelectItemVisitor {

    public ProjectVisitor(Tuple tuple, Schema schema) {

    }

    public ProjectVisitor() {

    }

    @Override
    public void visit(AllColumns allColumns) {
        System.out.println(allColumns.toString());
    }

    @Override
    public void visit(AllTableColumns allTableColumns) {
        System.out.println(allTableColumns.toString());
    }

    @Override
    public void visit(SelectExpressionItem selectExpressionItem) {

    }
}
