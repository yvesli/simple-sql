package test;

import org.junit.jupiter.api.Test;
import tools.RowParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class testFileReadingAndWriting {
    @Test
    void testFileReading() {
        File file = new File(
                "/Users/pro/Desktop/Courses/CS4321/CS4321-Projects/SQL/Materials/samples/input/db/data/Boats"
        );
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(Objects.requireNonNull(RowParser.parse(data)).toString());
            }
        }catch (FileNotFoundException e) {
            System.out.println("No such file");
        }

    }
}
