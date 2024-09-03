package family_tree.model.data;

import java.io.Serializable;
import java.util.*;

public class FamilyTree<T extends FamilyMember> implements Serializable, Iterable<T> {
    private static final long serialVersionUID = 1L;
    private List<T> people;

    public FamilyTree(Class<T> type) {
        this.people = new ArrayList<>();
    }

    public void addMember(T member) {
        if (!people.contains(member)) {
            people.add(member);
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
        Collections.sort(people, Comparator.comparing(FamilyMember::getName));
    }

    public void sortByAge() {
        Collections.sort(people, Comparator.comparing(FamilyMember::getBirthDate));
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
}





