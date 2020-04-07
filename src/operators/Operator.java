package operators;

import tuple.Tuple;

import java.io.PrintStream;
import java.util.Iterator;

public abstract class Operator {

    public abstract Tuple getNextTuple();
    public abstract void reset();
    public abstract void dump();

}
