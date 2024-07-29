package family_tree;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FamilyTree {
    private List<Human> people;
    private int nextId;

    public FamilyTree() {
        this.people = new ArrayList<>();
        this.nextId = 1;
    }

    public void addPerson(Human person) {
        this.people.add(person);
        this.nextId++;
    }

    public Human findPersonByName(String name) {
        for (Human person : people) {
            if (person.getName().equalsIgnoreCase(name)) {
                return person;
            }
        }
        return null;
    }

    public Human findPersonById(int id) {
        for (Human person : people) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    public List<Human> getChildrenOf(String name) {
        Human person = findPersonByName(name);
        if (person != null) {
            return person.getChildren();
        }
        return null;
    }

    public List<Human> getParentsOf(String name) {
        Human person = findPersonByName(name);
        if (person != null) {
            return person.getParents();
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Human person : people) {
            sb.append(person).append("\n");
        }
        return sb.toString();
    }

    public void populateFamilyTree() {
        Human ivan = new Human(nextId++, "Иван", Gender.Male, LocalDate.of(1954, 1, 1), null);
        Human maria = new Human(nextId++, "Мария", Gender.Female, LocalDate.of(1956, 2, 2), null);
        Human pavel = new Human(nextId++, "Павел", Gender.Male, LocalDate.of(1979, 3, 3), null);
        Human lyudmila = new Human(nextId++, "Людмила", Gender.Female, LocalDate.of(1982, 4, 4), null);
        Human mikhail = new Human(nextId++, "Михаил", Gender.Male, LocalDate.of(2004, 5, 5), null);
        Human elena = new Human(nextId++, "Елена", Gender.Female, LocalDate.of(2006, 6, 6), null);
        Human dmitry = new Human(nextId++, "Дмитрий", Gender.Male, LocalDate.of(2008, 7, 7), null);
        Human anna = new Human(nextId++, "Анна", Gender.Female, LocalDate.of(1995, 8, 8), null);
        Human sergey = new Human(nextId++, "Сергей", Gender.Male, LocalDate.of(2019, 9, 9), null);
        Human larisa = new Human(nextId++, "Лариса", Gender.Female, LocalDate.of(2021, 10, 10), null);

        addPerson(ivan);
        addPerson(maria);
        addPerson(pavel);
        addPerson(lyudmila);
        addPerson(mikhail);
        addPerson(elena);
        addPerson(dmitry);
        addPerson(anna);
        addPerson(sergey);
        addPerson(larisa);

        ivan.addChild(pavel);
        ivan.addChild(lyudmila);
        maria.addChild(pavel);
        maria.addChild(lyudmila);

        pavel.addChild(mikhail);
        pavel.addChild(elena);
        lyudmila.addChild(dmitry);
        lyudmila.addChild(anna);

        anna.addChild(sergey);
        anna.addChild(larisa);
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

        Human newPerson = new Human(nextId++, name, gender, birthDate, deathDate);
        addPerson(newPerson);

        System.out.print("Введите имя родителя (или оставьте пустым, если нет): ");
        String parentName = scanner.nextLine();
        if (!parentName.isEmpty()) {
            Human parent = findPersonByName(parentName);
            if (parent != null) {
                newPerson.addParent(parent);
            } else {
                System.out.println("Родитель с таким именем не найден.");
            }
        }

        System.out.print("Введите имя ребенка (или оставьте пустым, если нет): ");
        String childName = scanner.nextLine();
        if (!childName.isEmpty()) {
            Human child = findPersonByName(childName);
            if (child != null) {
                newPerson.addChild(child);
            } else {
                System.out.println("Ребенок с таким именем не найден.");
            }
        }

        System.out.println("Новый человек добавлен: " + newPerson);
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
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Генеалогическое древо:");
                    System.out.println(this);
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
            person = findPersonById(id);
        } catch (NumberFormatException e) {
            person = findPersonByName(input);
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
        List<Human> children = getChildrenOf(name);
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
        List<Human> parents = getParentsOf(name);
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



