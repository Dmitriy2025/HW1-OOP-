package family_tree.model;

import family_tree.model.builder.FamilyTreeBuilder;
import family_tree.model.data.*;
import family_tree.model.writer.Writer;

import java.time.LocalDate;
import java.util.List;

public class FamilyTreeService<T extends FamilyMember> {
    private FamilyTreeBuilder<T> builder;
    private FamilyTree<T> familyTree;
    private Writer writer;

    public FamilyTreeService(FamilyTreeBuilder<T> builder, Writer writer) {
        this.builder = builder;
        this.familyTree = builder.build();
        this.writer = writer;
    }

    public void populateFamilyTree(Class<T> type) {
        this.familyTree = builder.populateFamilyTree(type);
    }

    public void addPerson(String name, Gender gender, LocalDate birthDate, LocalDate deathDate) {
        T newPerson = builder.addPerson(name, gender, birthDate, deathDate);
        this.familyTree.addMember(newPerson);
    }

    public T findPersonByName(String name) {
        return familyTree.findPersonByName(name);
    }

    public T findPersonById(int id) {
        return familyTree.findPersonById(id);
    }

    public List<T> getChildrenOf(String name) {
        return familyTree.getChildrenOf(name);
    }

    public List<T> getParentsOf(String name) {
        return familyTree.getParentsOf(name);
    }

    public void sortByName() {
        familyTree.sortByName();
    }

    public void sortByAge() {
        familyTree.sortByAge();
    }

    public boolean saveFamilyTree(String filename) {
        writer.setPath(filename);
        return writer.saveFamilyTree(familyTree);
    }

    public FamilyTree<T> loadFamilyTree(String filename) {
        writer.setPath(filename);
        return (FamilyTree<T>) writer.read();
    }

    public FamilyTree<T> getFamilyTree() {
        return familyTree;
    }

    public Class<T> getMemberType() {
        return builder.getType();
    }

    public Writer getWriter() {
        return writer;
    }

    @Override
    public String toString() {
        return getFamilyTree().toString();
    }
}



