package operators;

import net.sf.jsqlparser.expression.Expression;

import schema.Schema;
import tuple.Tuple;
import visitors.WhereVisitor;

public class SelectOperator extends Operator {

    private ScanOperator scanner;
    private Schema schema;
    private Expression whereClause;

    public SelectOperator(ScanOperator scanner, Expression whereClause, Schema schema) {
        this.schema = schema;
        this.whereClause = whereClause;
        this.scanner = scanner;
    }

    @Override
    public Tuple getNextTuple() {
        boolean pass = false;
        Tuple next;
        while ((next = scanner.getNextTuple()) != null && !pass) {
            pass = isPass(next);
        }
        return next;
    }

    private boolean isPass(Tuple tuple) {
        try {
            WhereVisitor whereVisitor = new WhereVisitor(tuple, schema);
            whereClause.accept(whereVisitor);
            return whereVisitor.validate();
        } catch (NullPointerException e) {
            return true;
        }
    }

    @Override
    public void reset() {
        scanner.reset();
    }

    @Override
    public void dump() {
        scanner.dump();
    }
}
