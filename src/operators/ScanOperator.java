package operators;

import tools.RowParser;
import tuple.Tuple;

import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class ScanOperator extends Operator {

    private File file;
    private Scanner scanner;

    public ScanOperator(File file) throws FileNotFoundException {
        this.file = file;
        scanner = new Scanner(file);
    }

    @Override
    public Tuple getNextTuple() {
        String currentLine = scanner.nextLine();
        return RowParser.parse(currentLine);
    }

    @Override
    public void reset() {
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find original file: " + file.getName() + " to reset");
            e.printStackTrace();
        }
    }

    @Override
    public void dump() {
        OutputStream out = dataDumper.getOutputStream();
        try {
            while (scanner.hasNextLine()) {
                out.write((scanner.nextLine() + "\n").getBytes());
            }
            out.flush();
        } catch (IOException e) {
            System.err.println("IO Exception");
            e.printStackTrace();
        }
    }
}
