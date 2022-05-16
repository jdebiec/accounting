package sdacademy.models;

import java.io.*;

public class FileHandler {
    public static void serialize(Object objectToSave, String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(objectToSave);
        oos.close();
        fos.close();
    }

    public static Object deserialize(String filename) throws IOException, ClassNotFoundException {
        Object obj;

        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        obj = ois.readObject();
        ois.close();
        fis.close();

        return obj;
    }
}
