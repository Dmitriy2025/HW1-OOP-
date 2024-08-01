package family_tree;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class FamilyTreeConsole {
    private FamilyTree familyTree;

    public FamilyTreeConsole(FamilyTree familyTree) {
        this.familyTree = familyTree;
    }

    public void addPersonInteractive() {
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
                familyTree.findPersonByName(name).addParent(parent);
            } else {
                System.out.println("Родитель с таким именем не найден.");
            }
        }

        System.out.print("Введите имя ребенка (или оставьте пустым, если нет): ");
        String childName = scanner.nextLine();
        if (!childName.isEmpty()) {
            Human child = familyTree.findPersonByName(childName);
            if (child != null) {
                familyTree.findPersonByName(name).addChild(child);
            } else {
                System.out.println("Ребенок с таким именем не найден.");
            }
        }

        System.out.println("Новый человек добавлен: " + familyTree.findPersonByName(name));
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
            System.out.println("6. Выйти");

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

    private void findChildrenInteractive() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        List<Human> children = familyTree.getChildrenOf(name);
        if (children != null && !children.isEmpty()) {
            System.out.println("Дети " + name + ":");
            for (Human child : children) {
                System.out.println(child);
            }
        } else {
            System.out.println("Дети не найдены или у " + name + " нет детей.");
        }
    }

    private void findParentsInteractive() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        List<Human> parents = familyTree.getParentsOf(name);
        if (parents != null && !parents.isEmpty()) {
            System.out.println("Родители " + name + ":");
            for (Human parent : parents) {
                System.out.println(parent);
            }
        } else {
            System.out.println("У человека по имени " + name + " родители не найдены.");
        }
    }
}

