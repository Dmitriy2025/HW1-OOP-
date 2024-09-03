package family_tree.model.builder;

import family_tree.model.data.*;
import java.time.LocalDate;

public class FamilyTreeBuilder<T extends FamilyMember> {
    private FamilyTree<T> familyTree;
    private Class<T> type;
    private int nextId;

    public FamilyTreeBuilder(Class<T> type) {
        this.familyTree = new FamilyTree<>(type);
        this.type = type;
        this.nextId = determineNextId();
    }

    private int determineNextId() {
        int maxId = 0;
        for (T member : familyTree) {
            if (member.getId() > maxId) {
                maxId = member.getId();
            }
        }
        return maxId + 1;
    }


    public void addPerson(String name, Gender gender, LocalDate birthDate, LocalDate deathDate) {
        try {
            T newPerson = type.getConstructor(int.class, String.class, Gender.class, LocalDate.class, LocalDate.class)
                    .newInstance(nextId++, name, gender, birthDate, deathDate);
            this.familyTree.addMember(newPerson);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка: нельзя смешивать разные типы существ в одном генеалогическом древе.");
        }
    }

    public void populateFamilyTree(Class<T> type) {
        if (type.equals(Human.class)) {
            this.familyTree = (FamilyTree<T>) FamilyTreePopulator.populateHumanTree();
        } else if (type.equals(Dog.class)) {
            this.familyTree = (FamilyTree<T>) FamilyTreePopulator.populateDogTree();
        } else {
            System.out.println("Ошибка: неизвестный тип");
        }
        this.nextId = determineNextId();
    }

    public FamilyTreeBuilder<T> addParentTo(String childName, String parentName) {
        T child = familyTree.findPersonByName(childName);
        T parent = familyTree.findPersonByName(parentName);
        if (child != null && parent != null) {
            child.addParent(parent);
        }
        return this;
    }

    public FamilyTreeBuilder<T> addChildTo(String parentName, String childName) {
        T parent = familyTree.findPersonByName(parentName);
        T child = familyTree.findPersonByName(childName);
        if (parent != null && child != null) {
            parent.addChild(child);
        }
        return this;
    }

    public FamilyTreeBuilder<T> sortByName() {
        familyTree.sortByName();
        return this;
    }

    public FamilyTreeBuilder<T> sortByAge() {
        familyTree.sortByAge();
        return this;
    }

    public FamilyTree<T> build() {
        return familyTree;
    }

    @Override
    public String toString() {
        return familyTree.toString();
    }
}

