package family_tree.console;

import family_tree.data.FamilyTree;
import family_tree.data.Gender;
import family_tree.data.Human;
import family_tree.data.Sortable;
import family_tree.writer.Writer;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class FamilyTreeConsole<T extends Sortable> {
    private FamilyTree<T> familyTree;
    private Writer fileHandler;

    public FamilyTreeConsole(FamilyTree<T> familyTree, Writer fileHandler) {
        this.familyTree = familyTree;
        this.fileHandler = fileHandler;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Показать генеалогическое древо");
            System.out.println("2. Найти субъекта по имени или id");
            System.out.println("3. Добавить субъект в генеалогическое древо");
            System.out.println("4. Найти детей субъекта");
            System.out.println("5. Найти родителей субъекта");
            System.out.println("6. Сохранить генеалогическое древо в файл");
            System.out.println("7. Загрузить генеалогическое древо из файла");
            System.out.println("8. Сортировать по имени");
            System.out.println("9. Сортировать по возрасту");
            System.out.println("0. Выйти");

            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Генеалогическое древо:");
                    System.out.println(familyTree);
                    break;
                case 2:
                    findPersonInteractive();
                    break;
                case 3:
                    addPersonInteractive();
                    break;
                case 4:
                    findChildrenInteractive();
                    break;
                case 5:
                    findParentsInteractive();
                    break;
                case 6:
                    saveFamilyTreeInteractive();
                    break;
                case 7:
                    loadFamilyTreeInteractive();
                    break;
                case 8:
                    familyTree.sortByName();
                    System.out.println("Генеалогическое древо отсортировано по имени:");
                    System.out.println(familyTree);
                    break;
                case 9:
                    familyTree.sortByAge();
                    System.out.println("Генеалогическое древо отсортировано по возрасту:");
                    System.out.println(familyTree);
                    break;
                case 0:
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private void findPersonInteractive() {
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

    private void addPersonInteractive() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите тип (Human/Dog): ");
        String typeInput = scanner.nextLine();
        Class<T> type;

        if ("Human".equalsIgnoreCase(typeInput)) {
            type = (Class<T>) Human.class;
        //} else if ("Dog".equalsIgnoreCase(typeInput)) {
        //    type = (Class<T>) Dog.class;
        } else {
            System.out.println("Неизвестный тип.");
            return;
        }

        System.out.print("Введите пол (мужской/женский): ");
        String genderInput = scanner.nextLine();
        Gender gender = genderInput.equalsIgnoreCase("мужской") ? Gender.Male : Gender.Female;

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
            } else {
                System.out.println("Ребенок с таким именем не найден.");
            }
        }

        System.out.println("Новый " + type + " добавлен: " + newPerson);
    }

    private void findChildrenInteractive() {
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

    private void findParentsInteractive() {
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

    private void saveFamilyTreeInteractive() {
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

    private void loadFamilyTreeInteractive() {
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


