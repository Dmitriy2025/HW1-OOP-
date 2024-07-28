package family_tree;

import java.util.ArrayList;
import java.util.List;

public class FamilyTree {
    private List<Human> people;

    public FamilyTree() {
        this.people = new ArrayList<>();
    }

    public void addPerson(Human person) {
        this.people.add(person);
    }

    public Human findPersonByName(String name) {
        for (Human person : people) {
            if (person.getName().equals(name)) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Human person : people) {
            sb.append(person).append("\n");
        }
        return sb.toString();
    }
}


