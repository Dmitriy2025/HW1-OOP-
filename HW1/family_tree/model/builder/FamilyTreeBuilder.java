package family_tree.model.builder;

import family_tree.model.data.*;

import java.time.LocalDate;

public class FamilyTreeBuilder<T extends FamilyMember> {
    private FamilyTree<T> familyTree;
    private Class<T> type;

    public FamilyTreeBuilder(Class<T> type) {
        this.familyTree = new FamilyTree<>(type);
        this.type = type;
    }

    public void populateFamilyTree (Class<T> type) {
        FamilyTreePopulator.populateFamilyTree(familyTree, type);;
    }

    public void addPerson(String name, Gender gender, LocalDate birthDate, LocalDate deathDate) {
        try {
            familyTree.addPerson(name, gender, birthDate, deathDate, type);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка: нельзя смешивать разные типы существ в одном генеалогическом древе.");
        }
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

