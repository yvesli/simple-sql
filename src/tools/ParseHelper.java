package tools;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;

import java.util.List;

public class ParseHelper {

    public static PlainSelect getPlainSelect(Statement statement) {
        Select select = (Select) statement;
        return (PlainSelect) select.getSelectBody();
    }

    public static FromItem getFromItem(PlainSelect plainSelect) {
        return plainSelect.getFromItem();
    }

    public static FromItem getFromItem(Statement statement) {
        return getFromItem(getPlainSelect(statement));
    }

    public static Expression getWhereClause(Statement statement) {
        PlainSelect plainSelect = getPlainSelect(statement);
        return plainSelect.getWhere();
    }

    public static List getSelectItem(Statement statement) {
        PlainSelect plainSelect = getPlainSelect(statement);
        return plainSelect.getSelectItems();
    }

}
