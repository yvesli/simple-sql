package visitors;

import schema.Schema;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.SubSelect;
import tools.Tools;
import tuple.Tuple;

import java.text.ParseException;
import java.util.ListIterator;

public class WhereVisitor implements ExpressionVisitor {

    private Boolean isPass;
    private Number number;
    private String stringValue;
    private final Tuple tuple;
    private final Schema schema;

    public WhereVisitor(Tuple tuple, Schema schema) {
        this.tuple = tuple;
        this.schema = schema;
    }

    public boolean validate() {
        if (isPass != null) {
            return isPass;
        } else {
            throw new AssertionError("Cannot Validate");
        }
    }

    public Object getResult() {
        if (number != null)
            return number;
        if (stringValue != null)
            return stringValue;
        return null;
    }

    @Override
    public void visit(NullValue nullValue) {

    }

    @Override
    public void visit(Function function) {

    }

    @Override
    public void visit(InverseExpression inverseExpression) {

    }

    @Override
    public void visit(JdbcParameter jdbcParameter) {

    }

    @Override
    public void visit(DoubleValue doubleValue) {
        this.number = doubleValue.toDouble();
        this.isPass = true;
    }

    @Override
    public void visit(LongValue longValue) {
        this.number = longValue.toLong();
        this.isPass = true;
    }

    @Override
    public void visit(DateValue dateValue) {

    }

    @Override
    public void visit(TimeValue timeValue) {

    }

    @Override
    public void visit(TimestampValue timestampValue) {

    }

    @Override
    public void visit(Parenthesis parenthesis) {

    }

    @Override
    public void visit(StringValue stringValue) {
        this.stringValue = stringValue.toString();
        this.isPass = true;
    }

    @Override
    public void visit(Addition addition) {
        WhereVisitor leftVisitor = new WhereVisitor(tuple, schema);
        WhereVisitor rightVisitor = new WhereVisitor(tuple, schema);
        addition.getLeftExpression().accept(leftVisitor);
        addition.getRightExpression().accept(rightVisitor);
        Object left = leftVisitor.getResult();
        Object right = rightVisitor.getResult();
        try {
            if (left instanceof Long && right instanceof Long)
                this.number = (Long) left + (Long) right;
            else
                this.number = Double.parseDouble(left.toString()) + Double.parseDouble(right.toString());
            this.isPass = true;
        } catch (NumberFormatException e) {
            System.out.println("Cannot Subtract Two NonNumber");
        }
    }

    @Override
    public void visit(Division division) {
        WhereVisitor leftVisitor = new WhereVisitor(tuple, schema);
        WhereVisitor rightVisitor = new WhereVisitor(tuple, schema);
        division.getLeftExpression().accept(leftVisitor);
        division.getRightExpression().accept(rightVisitor);
        Object left = leftVisitor.getResult();
        Object right = rightVisitor.getResult();
        try {
            if (left instanceof Long && right instanceof Long)
                this.number = (Long) left / (Long) right;
            else
                this.number = Double.parseDouble(left.toString()) / Double.parseDouble(right.toString());
            this.isPass = true;
        } catch (NumberFormatException e) {
            System.out.println("Cannot Subtract Two NonNumber");
        }
    }

    @Override
    public void visit(Multiplication multiplication) {
        WhereVisitor leftVisitor = new WhereVisitor(tuple, schema);
        WhereVisitor rightVisitor = new WhereVisitor(tuple, schema);
        multiplication.getLeftExpression().accept(leftVisitor);
        multiplication.getRightExpression().accept(rightVisitor);
        Object left = leftVisitor.getResult();
        Object right = rightVisitor.getResult();
        try {
            if (left instanceof Long && right instanceof Long)
                this.number = (Long) left * (Long) right;
            else
                this.number = Double.parseDouble(left.toString()) * Double.parseDouble(right.toString());
            this.isPass = true;
        } catch (NumberFormatException e) {
            System.out.println("Cannot Subtract Two NonNumber");
        }
    }

    @Override
    public void visit(Subtraction subtraction) {
        WhereVisitor leftVisitor = new WhereVisitor(tuple, schema);
        WhereVisitor rightVisitor = new WhereVisitor(tuple, schema);
        subtraction.getLeftExpression().accept(leftVisitor);
        subtraction.getRightExpression().accept(rightVisitor);
        Object left = leftVisitor.getResult();
        Object right = rightVisitor.getResult();
        try {
            if (left instanceof Long && right instanceof Long)
                this.number = (Long) left - (Long) right;
            else
                this.number = Double.parseDouble(left.toString()) - Double.parseDouble(right.toString());
            this.isPass = true;
        } catch (NumberFormatException e) {
            System.out.println("Cannot Subtract Two NonNumber");
        }
    }

    @Override
    public void visit(AndExpression andExpression) {
        WhereVisitor leftVisitor = new WhereVisitor(tuple, schema);
        WhereVisitor rightVisitor = new WhereVisitor(tuple, schema);
        andExpression.getLeftExpression().accept(leftVisitor);
        andExpression.getRightExpression().accept(rightVisitor);
        this.isPass = leftVisitor.validate() && rightVisitor.validate();
    }

    @Override
    public void visit(OrExpression orExpression) {
        WhereVisitor leftVisitor = new WhereVisitor(tuple, schema);
        WhereVisitor rightVisitor = new WhereVisitor(tuple, schema);
        orExpression.getLeftExpression().accept(leftVisitor);
        orExpression.getRightExpression().accept(rightVisitor);
        this.isPass = leftVisitor.validate() || rightVisitor.validate();
    }

    @Override
    public void visit(Between between) {

    }

    @Override
    public void visit(EqualsTo equalsTo) {
        WhereVisitor leftVisitor = new WhereVisitor(tuple, schema);
        WhereVisitor rightVisitor = new WhereVisitor(tuple, schema);
        equalsTo.getLeftExpression().accept(leftVisitor);
        equalsTo.getRightExpression().accept(rightVisitor);
        Object left = leftVisitor.getResult();
        Object right = rightVisitor.getResult();
        this.isPass = left.equals(right);
    }

    @Override
    public void visit(GreaterThan greaterThan) {
        WhereVisitor leftVisitor = new WhereVisitor(tuple, schema);
        WhereVisitor rightVisitor = new WhereVisitor(tuple, schema);
        greaterThan.getLeftExpression().accept(leftVisitor);
        greaterThan.getRightExpression().accept(rightVisitor);
        Object left = leftVisitor.getResult();
        Object right = rightVisitor.getResult();
        try {
            this.isPass = Double.parseDouble(left.toString()) > Double.parseDouble(right.toString());
        } catch (NumberFormatException e) {
            System.out.println("Cannot Compare NonNumber");
        }
    }

    @Override
    public void visit(GreaterThanEquals greaterThanEquals) {
        WhereVisitor leftVisitor = new WhereVisitor(tuple, schema);
        WhereVisitor rightVisitor = new WhereVisitor(tuple, schema);
        greaterThanEquals.getLeftExpression().accept(leftVisitor);
        greaterThanEquals.getRightExpression().accept(rightVisitor);
        Object left = leftVisitor.getResult();
        Object right = rightVisitor.getResult();
        try {
            this.isPass = Double.parseDouble(left.toString()) >= Double.parseDouble(right.toString());
        } catch (NumberFormatException e) {
            System.out.println("Cannot Compare NonNumber");
        }
    }

    @Override
    public void visit(InExpression inExpression) {

    }

    @Override
    public void visit(IsNullExpression isNullExpression) {

    }

    @Override
    public void visit(LikeExpression likeExpression) {

    }

    @Override
    public void visit(MinorThan minorThan) {
        WhereVisitor leftVisitor = new WhereVisitor(tuple, schema);
        WhereVisitor rightVisitor = new WhereVisitor(tuple, schema);
        minorThan.getLeftExpression().accept(leftVisitor);
        minorThan.getRightExpression().accept(rightVisitor);
        Object left = leftVisitor.getResult();
        Object right = rightVisitor.getResult();
        try {
            this.isPass = Double.parseDouble(left.toString()) < Double.parseDouble(right.toString());
        } catch (NumberFormatException e) {
            System.out.println("Cannot Compare NonNumber");
        }
    }

    @Override
    public void visit(MinorThanEquals minorThanEquals) {
        WhereVisitor leftVisitor = new WhereVisitor(tuple, schema);
        WhereVisitor rightVisitor = new WhereVisitor(tuple, schema);
        minorThanEquals.getLeftExpression().accept(leftVisitor);
        minorThanEquals.getRightExpression().accept(rightVisitor);
        Object left = leftVisitor.getResult();
        Object right = rightVisitor.getResult();
        try {
            this.isPass = Double.parseDouble(left.toString()) <= Double.parseDouble(right.toString());
        } catch (NumberFormatException e) {
            System.out.println("Cannot Compare NonNumber");
        }
    }

    @Override
    public void visit(NotEqualsTo notEqualsTo) {
        WhereVisitor leftVisitor = new WhereVisitor(tuple, schema);
        WhereVisitor rightVisitor = new WhereVisitor(tuple, schema);
        notEqualsTo.getLeftExpression().accept(leftVisitor);
        notEqualsTo.getRightExpression().accept(rightVisitor);
        Object left = leftVisitor.getResult();
        Object right = rightVisitor.getResult();
        try {
            this.isPass = !left.equals(right);
        } catch (ClassCastException e) {
            System.out.println("Cannot Add Two NonNumber");
        }
    }

    @Override
    public void visit(Column column) {
        Object colValue = tuple.getNthValue(schema.getColumn(column.getColumnName()));
        try {
            if (colValue instanceof Long)
                this.number = (Long) colValue;
            else if (colValue instanceof Double)
                this.number = (Double) colValue;
            else
                this.stringValue = (String) colValue;
        } catch (ClassCastException e) {
            System.out.println("Need To Add more Class");
            e.printStackTrace();
        }
        this.isPass = true;
    }

    @Override
    public void visit(SubSelect subSelect) {

    }

    @Override
    public void visit(CaseExpression caseExpression) {

    }

    @Override
    public void visit(WhenClause whenClause) {

    }

    @Override
    public void visit(ExistsExpression existsExpression) {

    }

    @Override
    public void visit(AllComparisonExpression allComparisonExpression) {

    }

    @Override
    public void visit(AnyComparisonExpression anyComparisonExpression) {

    }

    @Override
    public void visit(Concat concat) {

    }

    @Override
    public void visit(Matches matches) {

    }

    @Override
    public void visit(BitwiseAnd bitwiseAnd) {

    }

    @Override
    public void visit(BitwiseOr bitwiseOr) {

    }

    @Override
    public void visit(BitwiseXor bitwiseXor) {

    }
}
