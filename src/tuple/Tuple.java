package tuple;

public interface Tuple {
    TupleType getType();
    int size();
    Object getNthValue(int i);
}
