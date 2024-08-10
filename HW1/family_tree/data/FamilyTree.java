package family_tree.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class FamilyTree<T extends Sortable> implements Serializable, Iterable<T> {
    private static final long serialVersionUID = 1L;

    private List<T> people;
    private int nextId;
    private Class<T> currentType;

    public FamilyTree() {
        this.people = new ArrayList<>();
        this.nextId = 1;
        this.currentType = null;
    }

    public Class<T> getCurrentType() {
        return currentType;
    }

    public void addPerson(String name, Gender gender, LocalDate birthDate, LocalDate deathDate, Class<T> type) {
        if (currentType == null) {
            currentType = type;
        } else if (!currentType.equals(type)) {
            System.out.println("Ошибка: нельзя смешивать типы ЧЕЛОВЕК и СОБАКА в одном генеалогическом древе.");
            return;
        }

        try {
            T newPerson = type.getConstructor(int.class, String.class, Gender.class, LocalDate.class, LocalDate.class)
                    .newInstance(nextId++, name, gender, birthDate, deathDate);
            this.people.add(newPerson);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Не удалось добавить нового субъекта.");
        }
    }

    public T findPersonByName(String name) {
        for (T person : people) {
            if (person.getName().equalsIgnoreCase(name)) {
                return person;
            }
        }
        return null;
    }

    public T findPersonById(int id) {
        for (T person : people) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    public List<T> getChildrenOf(String name) {
        T person = findPersonByName(name);
        if (person != null) {
            return (List<T>) person.getChildren();
        }
        return null;
    }

    public List<T> getParentsOf(String name) {
        T person = findPersonByName(name);
        if (person != null) {
            return (List<T>) person.getParents();
        }
        return null;
    }

    public void sortByName() {
        Collections.sort(people, Comparator.comparing(Sortable::getName));
    }

    public void sortByAge() {
        Collections.sort(people, Comparator.comparing(Sortable::getBirthDate));
    }

    @Override
    public Iterator<T> iterator() {
        return people.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T person : people) {
            sb.append(person).append("\n");
        }
        return sb.toString();
    }

    public void populateFamilyTree(Class<T> type) {
        if (type == Human.class) {
            addPerson("Иван", Gender.Male, LocalDate.of(1954, 1, 1), null, type);
            addPerson("Мария", Gender.Female, LocalDate.of(1956, 2, 2), null, type);
            addPerson("Павел", Gender.Male, LocalDate.of(1979, 3, 3), null, type);
            addPerson("Людмила", Gender.Female, LocalDate.of(1982, 4, 4), null, type);
            addPerson("Михаил", Gender.Male, LocalDate.of(2004, 5, 5), null, type);
            addPerson("Елена", Gender.Female, LocalDate.of(2006, 6, 6), null, type);
            addPerson("Дмитрий", Gender.Male, LocalDate.of(2008, 7, 7), null, type);
            addPerson("Анна", Gender.Female, LocalDate.of(1995, 8, 8), null, type);
            addPerson("Сергей", Gender.Male, LocalDate.of(2019, 9, 9), null, type);
            addPerson("Лариса", Gender.Female, LocalDate.of(2021, 10, 10), null, type);

            T ivan = findPersonByName("Иван");
            T maria = findPersonByName("Мария");
            T pavel = findPersonByName("Павел");
            T lyudmila = findPersonByName("Людмила");
            T mikhail = findPersonByName("Михаил");
            T elena = findPersonByName("Елена");
            T dmitry = findPersonByName("Дмитрий");
            T anna = findPersonByName("Анна");
            T sergey = findPersonByName("Сергей");
            T larisa = findPersonByName("Лариса");

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
        } else if (type == Dog.class) {
            addPerson("Рекс", Gender.Male, LocalDate.of(2015, 1, 1), null, type);
            addPerson("Лайка", Gender.Female, LocalDate.of(2018, 5, 5), null, type);
        }
    }
}





