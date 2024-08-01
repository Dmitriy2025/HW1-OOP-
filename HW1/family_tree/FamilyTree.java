package family_tree;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FamilyTree {
    private List<Human> people;
    private int nextId;

    public FamilyTree() {
        this.people = new ArrayList<>();
        this.nextId = 1;
    }

    public void addPerson(String name, Gender gender, LocalDate birthDate, LocalDate deathDate) {
        Human newPerson = new Human(nextId++, name, gender, birthDate, deathDate);
        this.people.add(newPerson);
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
        addPerson("Иван", Gender.Male, LocalDate.of(1954, 1, 1), null);
        addPerson("Мария", Gender.Female, LocalDate.of(1956, 2, 2), null);
        addPerson("Павел", Gender.Male, LocalDate.of(1979, 3, 3), null);
        addPerson("Людмила", Gender.Female, LocalDate.of(1982, 4, 4), null);
        addPerson("Михаил", Gender.Male, LocalDate.of(2004, 5, 5), null);
        addPerson("Елена", Gender.Female, LocalDate.of(2006, 6, 6), null);
        addPerson("Дмитрий", Gender.Male, LocalDate.of(2008, 7, 7), null);
        addPerson("Анна", Gender.Female, LocalDate.of(1995, 8, 8), null);
        addPerson("Сергей", Gender.Male, LocalDate.of(2019, 9, 9), null);
        addPerson("Лариса", Gender.Female, LocalDate.of(2021, 10, 10), null);

        Human ivan = findPersonByName("Иван");
        Human maria = findPersonByName("Мария");
        Human pavel = findPersonByName("Павел");
        Human lyudmila = findPersonByName("Людмила");
        Human mikhail = findPersonByName("Михаил");
        Human elena = findPersonByName("Елена");
        Human dmitry = findPersonByName("Дмитрий");
        Human anna = findPersonByName("Анна");
        Human sergey = findPersonByName("Сергей");
        Human larisa = findPersonByName("Лариса");

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
}






