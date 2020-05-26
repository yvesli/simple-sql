package operators;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.SelectItemVisitor;
import schema.Schema;
import tuple.Tuple;
import visitors.ProjectVisitor;

public class ProjectOperator extends Operator {

    private Operator operator;
    private Schema schema;
    private SelectItem selectItem;

    public ProjectOperator(Operator operator, SelectItem selectItem, Schema schema) {
        this.operator = operator;
        this.schema = schema;
        this.selectItem = selectItem;
    }

    @Override
    public Tuple getNextTuple() {
        Tuple tuple = operator.getNextTuple();
        if (tuple == null)
            return null;
        ProjectVisitor projectVisitor = new ProjectVisitor(tuple, schema);
        selectItem.accept(projectVisitor);
        return tuple;
    }

    @Override
    public void reset() {
        operator.reset();
    }

    @Override
    public void dump() {
        operator.dump();
    }
}
