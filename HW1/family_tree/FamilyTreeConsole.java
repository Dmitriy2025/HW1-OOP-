package family_tree;

import family_tree.writer.Writer;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class FamilyTreeConsole {
    private FamilyTree familyTree;
    private Writer fileHandler;

    public FamilyTreeConsole(FamilyTree familyTree, Writer fileHandler) {
        this.familyTree = familyTree;
        this.fileHandler = fileHandler;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Показать генеалогическое древо");
            System.out.println("2. Найти человека по имени или id");
            System.out.println("3. Добавить человека в генеалогическое древо");
            System.out.println("4. Найти детей человека");
            System.out.println("5. Найти родителей человека");
            System.out.println("6. Сохранить генеалогическое древо в файл");
            System.out.println("7. Загрузить генеалогическое древо из файла");
            System.out.println("8. Выйти");

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
        Human person;
        try {
            int id = Integer.parseInt(input);
            person = familyTree.findPersonById(id);
        } catch (NumberFormatException e) {
            person = familyTree.findPersonByName(input);
        }
        if (person != null) {
            System.out.println("Найденный человек: " + person);
        } else {
            System.out.println("Человек не найден.");
        }
    }

    private void addPersonInteractive() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите пол (мужчина/женщина): ");
        String genderInput = scanner.nextLine();
        Gender gender = genderInput.equalsIgnoreCase("мужчина") ? Gender.Male : Gender.Female;

        System.out.print("Введите дату рождения (ГГГГ-ММ-ДД): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Введите дату смерти (ГГГГ-ММ-ДД) или оставьте пустым: ");
        String deathDateInput = scanner.nextLine();
        LocalDate deathDate = deathDateInput.isEmpty() ? null : LocalDate.parse(deathDateInput);

        familyTree.addPerson(name, gender, birthDate, deathDate);

        System.out.print("Введите имя родителя (или оставьте пустым, если нет): ");
        String parentName = scanner.nextLine();
        if (!parentName.isEmpty()) {
            Human parent = familyTree.findPersonByName(parentName);
            if (parent != null) {
                Human newPerson = familyTree.findPersonByName(name);
                newPerson.addParent(parent);
            } else {
                System.out.println("Родитель с таким именем не найден.");
            }
        }

        System.out.print("Введите имя ребенка (или оставьте пустым, если нет): ");
        String childName = scanner.nextLine();
        if (!childName.isEmpty()) {
            Human child = familyTree.findPersonByName(childName);
            if (child != null) {
                Human newPerson = familyTree.findPersonByName(name);
                newPerson.addChild(child);
            } else {
                System.out.println("Ребенок с таким именем не найден.");
            }
        }

        System.out.println("Новый человек добавлен: " + familyTree.findPersonByName(name));
    }

    private void findChildrenInteractive() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        List<Human> children = familyTree.getChildrenOf(name);
        if (children != null && !children.isEmpty()) {
            System.out.println("Дети человека " + name + ":");
            for (Human child : children) {
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
        List<Human> parents = familyTree.getParentsOf(name);
        if (parents != null && !parents.isEmpty()) {
            System.out.println("Родители человека " + name + ":");
            for (Human parent : parents) {
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


