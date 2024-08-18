package family_tree.presenter;

import family_tree.model.data.FamilyTree;
import family_tree.model.data.Gender;
import family_tree.model.data.Sortable;
import family_tree.model.writer.FileHandler;
import family_tree.model.writer.Writer;
import family_tree.view.View;

import java.time.LocalDate;
import java.util.List;

public class Presenter<T extends Sortable> {
    private View view;
    private final Writer fileHandler;
    private FamilyTree<T> familyTree;

    public Presenter(View view) {
        this.view = view;
        this.fileHandler = new FileHandler();
    }

    public void setView(View view) {
        this.view = view;
    }

    public void populateFamilyTree(Class<T> type) {
        familyTree = new FamilyTree<>(type);
        familyTree.populateFamilyTree(type);
        if (view != null) {
            view.printAnswer("Генеалогическое древо успешно создано и заполнено.");
        }
    }

    public void setFamilyTree(FamilyTree<T> familyTree) {
        this.familyTree = familyTree;
    }

    public void showFamilyTree() {
        if (view != null && familyTree != null) {
            view.printAnswer("Генеалогическое древо:");
            view.printAnswer(familyTree.toString());
        } else {
            if (view != null) {
                view.printAnswer("Генеалогическое древо не доступно.");
            }
        }
    }

    public void sortByName () {
        familyTree.sortByName();
        view.printAnswer("Генеалогическое древо отсортировано по имени:");
        view.printAnswer(familyTree.toString());
    }
    public void sortByAge () {
        familyTree.sortByAge();
        view.printAnswer("Генеалогическое древо отсортировано по возрасту:");
        view.printAnswer(familyTree.toString());
    }

    public void findPerson(String input) {
        Sortable person;
        try {
            int id = Integer.parseInt(input);
            person = familyTree.findPersonById(id);
        } catch (NumberFormatException e) {
            person = familyTree.findPersonByName(input);
        }
        if (person != null) {
            view.printAnswer("Найденный субъект: " + person);
        } else {
            view.printAnswer("Субъект не найден.");
        }
    }

    public void addPerson(String name, Gender gender, LocalDate birthDate, LocalDate deathDate, Class<T> type) {
        familyTree.addPerson(name, gender, birthDate, deathDate, type);
        Sortable newPerson = familyTree.findPersonByName(name);
        view.printAnswer("Новый субъект добавлен: " + newPerson);
    }

    public T findPersonByName(String name) {
        return familyTree.findPersonByName(name);
    }

    public void findChildren(String name) {
        List<T> children = familyTree.getChildrenOf(name);
        if (children != null && !children.isEmpty()) {
            view.printAnswer("Дети субъекта " + name + ":");
            for (T child : children) {
                view.printAnswer(child.toString());
            }
        } else {
            view.printAnswer("Дети не найдены.");
        }
    }

    public void findParents(String name) {
        List<T> parents = familyTree.getParentsOf(name);
        if (parents != null && !parents.isEmpty()) {
            view.printAnswer("Родители субъекта " + name + ":");
            for (T parent : parents) {
                view.printAnswer(parent.toString());
            }
        } else {
            view.printAnswer("Родители не найдены.");
        }
    }

    public Class<T> getCurrentType() {
        return familyTree.getCurrentType();
    }

    public void saveFamilyTree(String filename) {
        if (familyTree != null) {
            fileHandler.setPath(filename);
            boolean success = fileHandler.saveFamilyTree(familyTree);
            if (success) {
                view.printAnswer("Генеалогическое древо сохранено в файл " + filename);
            } else {
                view.printAnswer("Ошибка при сохранении генеалогического древа.");
            }
        }
    }

    public void loadFamilyTree(String filename) {
        fileHandler.setPath(filename);
        FamilyTree<T> loadedFamilyTree = (FamilyTree<T>) fileHandler.read();
        if (loadedFamilyTree != null) {
            this.familyTree = loadedFamilyTree;
            view.printAnswer("Генеалогическое древо загружено из файла " + filename);
        } else {
            view.printAnswer("Ошибка при загрузке генеалогического древа.");
        }
    }

    public void exitProgram() {
        view.printAnswer("Выход...");
        System.exit(0);
    }
}
