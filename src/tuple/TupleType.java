package tuple;

public interface TupleType {

    int size();
    Class<?> getNthType(int i);
    Tuple createTuple(Object... values);

    class DefaultFactory {
        public static TupleType create(final Class<?>... types) {
            return new TupleTypeImpl(types);
        }
    }

    class SingleTypeFactory {
        public static TupleType create(final Class<?> type, final int size) {
            Class<?>[] types = new Class[size];
            for (int i = 0; i < size; i++) {
                types[i] = type;
            }
            return new TupleTypeImpl(types);
        }
    }
}
