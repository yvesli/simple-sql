package query;

import java.util.Iterator;
import net.sf.jsqlparser.statement.Statement;

public interface QueryReader extends Iterator<Statement> {

    boolean isValid();


}
