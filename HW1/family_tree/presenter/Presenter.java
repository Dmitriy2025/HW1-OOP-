package family_tree.presenter;

import family_tree.model.FamilyTreeService;
import family_tree.model.data.*;
import family_tree.model.writer.FileHandler;
import family_tree.model.writer.Writer;
import family_tree.view.View;

import java.time.LocalDate;
import java.util.List;

public class Presenter<T extends FamilyMember> {
    private FamilyTreeService<T> familyTreeService;
    private View view;

    public Presenter(FamilyTreeService<T> familyTreeService, View view) {
        this.familyTreeService = familyTreeService;
        this.view = view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void populateFamilyTree(Class<T> type) {
        familyTreeService.populateFamilyTree(type);
        view.printAnswer("Семейное древо инициализировано для типа: " + type.getSimpleName());
    }

    public void addPerson(String name, Gender gender, LocalDate birthDate, LocalDate deathDate, Class<T> type) {
        if (!type.equals(familyTreeService.getCurrentType())) {
            view.printAnswer("Ошибка: нельзя смешивать различные типы существ в одном генеалогическом древе.");
            return;
        }
        familyTreeService.addPerson(name, gender, birthDate, deathDate);
        view.printAnswer("Добавлен новый " + (type.equals(Human.class) ? "человек" : "питомец") + ": " + name);
    }

    public FamilyMember findPersonByName(String name) {
        FamilyMember member = familyTreeService.findPersonByName(name);
        if (member != null) {
            view.printAnswer("Субъект найден:\n " + member);
            return member;
        } else {
            view.printAnswer("Субъект с именем " + name + " не найден.");
            return null;
        }
    }

    public void findPersonById(int id) {
        FamilyMember member = familyTreeService.findPersonById(id);
        if (member != null) {
            view.printAnswer("Субъект найден:\n " + member);
        } else {
            view.printAnswer("Субъект с ID " + id + " не найден.");
        }
    }

    public void findChildren(String name) {
        List<T> children = familyTreeService.getChildrenOf(name);
        if (children != null && !children.isEmpty()) {
            StringBuilder childrenStr = new StringBuilder();
            for (T child : children) {
                if (childrenStr.length() > 0) {
                    childrenStr.append(";\n ");
                }
                childrenStr.append(child);
            }
            view.printAnswer("Дети субъекта " + name + ":\n " + childrenStr.toString());
        } else {
            view.printAnswer("У субъекта с именем " + name + " нет детей.");
        }
    }

    public void findParents(String name) {
        List<T> parents = familyTreeService.getParentsOf(name);
        if (parents != null && !parents.isEmpty()) {
            StringBuilder parentsStr = new StringBuilder();
            for (T parent : parents) {
                if (parentsStr.length() > 0) {
                    parentsStr.append(";\n ");
                }
                parentsStr.append(parent);
            }
            view.printAnswer("Родители субъекта " + name + ":\n " + parentsStr.toString());
        } else {
            view.printAnswer("У субъекта с именем " + name + " нет родителей.");
        }
    }

    public void sortByAge() {
        familyTreeService.sortByAge();
        view.printAnswer("Семейное древо отсортировано по возрасту.");
        showFamilyTree();
    }

    public void sortByName() {
        familyTreeService.sortByName();
        view.printAnswer("Семейное древо отсортировано по имени.");
        showFamilyTree();
    }

    public void showFamilyTree() {
        view.printAnswer(familyTreeService.toString());
    }

    public void saveFamilyTree(String filename) {
        boolean success = familyTreeService.saveFamilyTree(filename);
        if (success) {
            view.printAnswer("Семейное древо успешно сохранено в файл " + filename);
        } else {
            view.printAnswer("Не удалось сохранить семейное древо.");
        }
    }

    public void loadFamilyTree(String filename) {
        FamilyTree<T> tree = familyTreeService.loadFamilyTree(filename);
        if (tree != null) {
            view.printAnswer("Семейное древо успешно загружено из файла " + filename);
            showFamilyTree();
        } else {
            view.printAnswer("Не удалось загрузить семейное древо.");
        }
    }

    public Class<T> getCurrentType() {
        return familyTreeService.getCurrentType();
    }

    public void exitProgram() {
        view.printAnswer("Выход...");
        System.exit(0);
    }
}

