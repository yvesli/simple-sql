package path;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class PathImpl implements Path {

    private final LinkedList<String> p = new LinkedList<>();
    private ListIterator<String> pIterator;

    @Override
    public void storePath(String... path) {
        p.addAll(Arrays.asList(path));
        pIterator = p.listIterator(0);
    }

    @Override
    public Iterator<String> iterator() {
        return p.listIterator(0);
    }

    @Override
    public boolean hasNext() {
        return pIterator.hasNext();
    }

    @Override
    public String next() {
        return pIterator.next();
    }

    public void reset() {
        pIterator = p.listIterator(0);
    }
}
