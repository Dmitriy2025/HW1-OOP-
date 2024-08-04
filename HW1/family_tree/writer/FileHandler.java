package family_tree.writer;

import java.io.*;

public class FileHandler implements Writer {
    private String filePath;

    @Override
    public void setPath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean saveFamilyTree(Serializable serializable) {
        if (filePath == null) {
            System.out.println("Путь к файлу не установлен.");
            return false;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(serializable);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Object read() {
        if (filePath == null) {
            System.out.println("Путь к файлу не установлен.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

