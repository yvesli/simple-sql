package tuple;

public interface Tuple {
    TupleType getType();
    int size();
    <T> T getNthValue(int i);
}
