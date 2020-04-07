package operators;

import catalogs.DatabaseCatalog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class dataDumper {

    public static OutputStream getOutputStream() {
        String path = DatabaseCatalog.getInstance().getRootPath();
        File file = new File(path + "/cache/trash");
        file.getParentFile().mkdirs();
        try {
            return new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            return System.out;
        }
    }

}
