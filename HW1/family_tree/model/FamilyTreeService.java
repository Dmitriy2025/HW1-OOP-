package family_tree.model;

import family_tree.model.builder.FamilyTreeBuilder;
import family_tree.model.data.*;
import family_tree.model.writer.Writer;

import java.time.LocalDate;
import java.util.List;

public class FamilyTreeService<T extends FamilyMember> {
    private FamilyTreeBuilder<T> builder;
    private Writer writer;
    private Class<T> type;

    public FamilyTreeService(Class<T> type, Writer writer) {
        this.builder = new FamilyTreeBuilder<>(type);
        this.writer = writer;
        this.type = type;
    }

    public void populateFamilyTree(Class<T> type) {
        this.builder = new FamilyTreeBuilder<>(type);
        this.builder.populateFamilyTree(type);
    }

    public void addPerson(String name, Gender gender, LocalDate birthDate, LocalDate deathDate) {
        builder.addPerson(name, gender, birthDate, deathDate);
    }

    public T findPersonByName(String name) {
        return builder.build().findPersonByName(name);
    }

    public T findPersonById(int id) {
        return builder.build().findPersonById(id);
    }

    public List<T> getChildrenOf(String name) {
        return builder.build().getChildrenOf(name);
    }

    public List<T> getParentsOf(String name) {
        return builder.build().getParentsOf(name);
    }

    public void sortByName() {
        builder.sortByName();
    }

    public void sortByAge() {
        builder.sortByAge();
    }

    public boolean saveFamilyTree() {
        return writer.saveFamilyTree(builder.build());
    }

    public FamilyTree<T> loadFamilyTree() {
        return (FamilyTree<T>) writer.read();
    }

    public Class<T> getCurrentType() {
        return type;
    }

    public Writer getWriter() {
        return writer;
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}



