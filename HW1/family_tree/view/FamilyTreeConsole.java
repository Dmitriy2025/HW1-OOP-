package family_tree.view;

import family_tree.model.data.FamilyTree;
import family_tree.model.data.Gender;
import family_tree.model.data.Human;
import family_tree.model.data.Dog;
import family_tree.model.data.Sortable;
import family_tree.model.writer.Writer;
import family_tree.presenter.Presenter;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class FamilyTreeConsole<T extends Sortable> {
    public FamilyTree<T> getFamilyTree() {
        return familyTree;
    }

    private FamilyTree<T> familyTree;
    private Writer fileHandler;
    private Scanner scanner;
    private Presenter presenter;
    private boolean work;
    private MainMenu menu;

    public FamilyTreeConsole(FamilyTree<T> familyTree, Writer fileHandler) {
        this.familyTree = familyTree;
        this.fileHandler = fileHandler;
        scanner = new Scanner(System.in);
        presenter = new Presenter(this);
        work = true;
        menu = new MainMenu(this);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nМеню:");
            System.out.println(menu.menu());

            System.out.print("Выберите опцию: ");
            int choice;
            try {
                String strChoice = scanner.nextLine();
                choice = Integer.parseInt(strChoice);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: необходимо ввести число.");
                continue;
            }

            if (choice < 1 || choice > menu.getSize()) {
                System.out.println("Неверный выбор. Выберете числа от 1 до " + menu.getSize());
            } else {
                menu.execute(choice);
                if (choice == menu.getSize()) {
                    break;
                }
            }
        }
    }

    public void exitProgram() {
        System.out.println("Выход...");
        work = false;
    }

    public void sortByAge() {
        familyTree.sortByAge();
        System.out.println("Генеалогическое древо отсортировано по возрасту:");
        System.out.println(familyTree);
    }

    public void sortByName() {
        familyTree.sortByName();
        System.out.println("Генеалогическое древо отсортировано по имени:");
        System.out.println(familyTree);
    }

    public void showFamilyTree() {
        System.out.println("Генеалогическое древо:");
        System.out.println(familyTree);
    }

    public void findPersonInteractive() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя или ID: ");
        String input = scanner.nextLine();
        Sortable person;
        try {
            int id = Integer.parseInt(input);
            person = familyTree.findPersonById(id);
        } catch (NumberFormatException e) {
            person = familyTree.findPersonByName(input);
        }
        if (person != null) {
            System.out.println("Найденный субъект: " + person);
        } else {
            System.out.println("Субъект не найден.");
        }
    }

    public void addPersonInteractive() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите тип (Человек/Собака): ");
        String typeInput = scanner.nextLine();
        Class<T> type;
        if ("Человек".equalsIgnoreCase(typeInput)) {
            type = (Class<T>) Human.class;
        } else if ("Собака".equalsIgnoreCase(typeInput)) {
            type = (Class<T>) Dog.class;
        } else {
            System.out.println("Неизвестный тип.");
            return;
        }

        if (familyTree.getCurrentType() != null && !familyTree.getCurrentType().equals(type)) {
            System.out.println("Ошибка: нельзя смешивать типы ЧЕЛОВЕК и СОБАКА в одном генеалогическом древе.");
            return;
        }

        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите пол (мужской/женский): ");
        Gender gender = null;
        while (gender == null) {
            String genderInput = scanner.nextLine().trim().toLowerCase();
            if (genderInput.equals("мужской")) {
                gender = Gender.Male;
            } else if (genderInput.equals("женский")) {
                gender = Gender.Female;
            } else {
                System.out.print("Некорректный ввод. Пожалуйста, введите 'мужской' или 'женский': ");
            }
        }

        System.out.print("Введите дату рождения (ГГГГ-ММ-ДД): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Введите дату смерти (ГГГГ-ММ-ДД) или оставьте пустым: ");
        String deathDateInput = scanner.nextLine();
        LocalDate deathDate = deathDateInput.isEmpty() ? null : LocalDate.parse(deathDateInput);

        familyTree.addPerson(name, gender, birthDate, deathDate, type);

        Sortable newPerson = familyTree.findPersonByName(name);

        System.out.print("Введите имя родителя (или оставьте пустым, если нет): ");
        String parentName = scanner.nextLine();
        if (!parentName.isEmpty()) {
            Sortable parent = familyTree.findPersonByName(parentName);
            if (parent != null) {
                newPerson.addParent(parent);
                System.out.println();
            } else {
                System.out.println("Родитель с таким именем не найден.");
            }
        }

        System.out.print("Введите имя ребенка (или оставьте пустым, если нет): ");
        String childName = scanner.nextLine();
        if (!childName.isEmpty()) {
            Sortable child = familyTree.findPersonByName(childName);
            if (child != null) {
                newPerson.addChild(child);
                System.out.println();
            } else {
                System.out.println("Ребенок с таким именем не найден.");
            }
        }

        String typeName = type.equals(Human.class) ? "человек" : "собака";
        System.out.println("Новый субъект " + typeName + " добавлен: " + newPerson);
    }

    public void findChildrenInteractive() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        List<T> children = familyTree.getChildrenOf(name);
        if (children != null && !children.isEmpty()) {
            System.out.println("Дети субъекта " + name + ":");
            for (T child : children) {
                System.out.println(child);
            }
        } else {
            System.out.println("Дети не найдены.");
        }
    }

    public void findParentsInteractive() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        List<T> parents = familyTree.getParentsOf(name);
        if (parents != null && !parents.isEmpty()) {
            System.out.println("Родители субъекта " + name + ":");
            for (T parent : parents) {
                System.out.println(parent);
            }
        } else {
            System.out.println("Родители не найдены.");
        }
    }

    public void saveFamilyTreeInteractive() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя файла для сохранения: ");
        String filename = scanner.nextLine();
        fileHandler.setPath(filename);
        if (fileHandler.saveFamilyTree(familyTree)) {
            System.out.println("Генеалогическое древо сохранено в файл " + filename);
        } else {
            System.out.println("Ошибка при сохранении генеалогического древа.");
        }
    }

    public void loadFamilyTreeInteractive() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя файла для загрузки: ");
        String filename = scanner.nextLine();
        fileHandler.setPath(filename);
        FamilyTree loadedFamilyTree = (FamilyTree) fileHandler.read();
        if (loadedFamilyTree != null) {
            familyTree = loadedFamilyTree;
            System.out.println("Генеалогическое древо загружено из файла " + filename);
        } else {
            System.out.println("Ошибка при загрузке генеалогического древа.");
        }
    }
}


