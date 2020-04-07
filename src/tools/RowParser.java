package tools;

import tuple.Tuple;
import tuple.TupleType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RowParser {

    public static Tuple parse(String line) {
        if (line.length() == 0)
            return null;
        List<String> lst = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != ',' || line.charAt(i) != ' ')
                sb.append(line.charAt(i));
            else {
                if (sb.length() != 0) {
                    lst.add(sb.toString());
                    sb = new StringBuilder();
                }
            }
        }
        if (sb.length() != 0)
            lst.add(sb.toString());

        TupleType tupleType = TupleType.SingleTypeFactory.create(String.class, lst.size());
        return tupleType.createTuple(lst.toArray());
    }

}
