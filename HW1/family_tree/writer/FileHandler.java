package family_tree.writer;

import family_tree.FamilyTree;
import java.io.*;

public class FileHandler {

    public static void saveFamilyTree(FamilyTree familyTree, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(familyTree);
            System.out.println("Генеалогическое древо сохранено в файл: " + filename);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении генеалогического древа: " + e.getMessage());
        }
    }

    public static FamilyTree loadFamilyTree(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            FamilyTree familyTree = (FamilyTree) ois.readObject();
            System.out.println("Генеалогическое древо загружено из файла: " + filename);
            return familyTree;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при загрузке генеалогического древа: " + e.getMessage());
            return null;
        }
    }
}

