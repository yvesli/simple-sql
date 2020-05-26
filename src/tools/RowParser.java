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
        List<Object> lst = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != ',' && line.charAt(i) != ' ') {
                sb.append(line.charAt(i));
            }
            else {
                if (sb.length() != 0) {
                    String str = sb.toString();
                    if (Tools.isLong(str))
                        lst.add(Long.parseLong(str));
                    else if (Tools.isDouble(str))
                        lst.add(Double.parseDouble(str));
                    else
                        lst.add(sb.toString());
                    sb = new StringBuilder();
                }
            }
        }
        if (sb.length() != 0) {
            String str = sb.toString();
            if (Tools.isLong(str))
                lst.add(Long.parseLong(str));
            else if (Tools.isDouble(str))
                lst.add(Double.parseDouble(str));
            else
                lst.add(sb.toString());
        }
        Class<?>[] type = new Class[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            type[i] = lst.get(i).getClass();
        }
        TupleType tupleType = TupleType.DefaultFactory.create(type);
        return tupleType.createTuple(lst.toArray());
    }

}
