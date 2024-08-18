package family_tree.view;

import family_tree.model.data.Gender;
import family_tree.model.data.Human;
import family_tree.model.data.Dog;
import family_tree.model.data.Sortable;
import family_tree.presenter.Presenter;

import java.time.LocalDate;
import java.util.Scanner;

public class FamilyTreeConsole<T extends Sortable> implements View {
    private Presenter<T> presenter;
    private Scanner scanner;
    private boolean work;
    private MainMenu menu;

    public FamilyTreeConsole(Presenter<T> presenter) {
        this.presenter = presenter;
        this.scanner = new Scanner(System.in);
        this.work = true;
        this.menu = new MainMenu(this);
    }

    @Override
    public void start() {
        presenter.populateFamilyTree((Class<T>) Human.class);
        while (work) {
            showMenu();
        }
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
        presenter.exitProgram();
    }

    public void sortByAge() {
        presenter.sortByAge();
    }

    public void sortByName() {
        presenter.sortByName();
    }

    public void showFamilyTree() {
        presenter.showFamilyTree();
    }

    public void findPersonInteractive() {
        System.out.print("Введите имя или ID: ");
        String input = scanner.nextLine();
        presenter.findPerson(input);
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

        if (presenter.getCurrentType() != null && !presenter.getCurrentType().equals(type)) {
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

        presenter.addPerson(name, gender, birthDate, deathDate, type);

        Sortable newPerson = presenter.findPersonByName(name);

        System.out.print("Введите имя родителя (или оставьте пустым, если нет): ");
        String parentName = scanner.nextLine();
        if (!parentName.isEmpty()) {
            Sortable parent = presenter.findPersonByName(parentName);
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
            Sortable child = presenter.findPersonByName(childName);
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
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        presenter.findChildren(name);
    }

    public void findParentsInteractive() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        presenter.findParents(name);
    }

    public void saveFamilyTreeInteractive() {
        System.out.print("Введите имя файла для сохранения: ");
        String filename = scanner.nextLine();
        presenter.saveFamilyTree(filename);
    }

    public void loadFamilyTreeInteractive() {
        System.out.print("Введите имя файла для загрузки: ");
        String filename = scanner.nextLine();
        presenter.loadFamilyTree(filename);
    }

    @Override
    public void printAnswer(String text) {
        System.out.println(text);
    }
}


