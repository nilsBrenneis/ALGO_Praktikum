package nbcn.termin1.aufgabe1;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Parser {

    public static byte[] readFile(File f) throws IOException {
        FileInputStream fw = new FileInputStream(f);
        BufferedInputStream bis = new BufferedInputStream(fw);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int b;


        while ((b = bis.read()) != -1) {
            bos.write(b);
        }
        bos.close();
        bis.close();

        return bos.toByteArray();
    }

    public static byte[] readFile(String path, String file) throws IOException {
        return readFile(new File(path, file));
    }

    public static byte[] readFile(String file) throws IOException {
        return readFile(new File(file));
    }
}