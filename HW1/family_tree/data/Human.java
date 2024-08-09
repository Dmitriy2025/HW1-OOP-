package family_tree.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Human implements Serializable, Sortable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private Gender gender;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private List<Sortable> parents;
    private List<Sortable> children;

    public Human(int id, String name, Gender gender, LocalDate birthDate, LocalDate deathDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public void addParent(Sortable parent) {
        if (parent instanceof Human) {
            if (!parents.contains(parent)) {
                this.parents.add(parent);
                ((Human) parent).addChild(this);
            }
        }
    }

    @Override
    public void addChild(Sortable child) {
        if (child instanceof Human) {
            if (!children.contains(child)) {
                this.children.add(child);
                ((Human) child).addParent(this);
            }
        }
    }

    @Override
    public List<Sortable> getChildren() {
        return new ArrayList<>(children);
    }

    @Override
    public List<Sortable> getParents() {
        return new ArrayList<>(parents);
    }

    @Override
    public String toString() {
        // Обновленный toString метод
        String genderString = gender == Gender.Male ? "пол: мужской" : "пол: женский";
        StringBuilder result = new StringBuilder();
        result.append(id).append(": ").append(name).append(" (").append(genderString)
                .append(", родился ").append(birthDate);
        if (deathDate != null) {
            result.append(", умер ").append(deathDate);
        }
        result.append(")");

        if (!parents.isEmpty()) {
            result.append("; родители: ");
            for (Sortable parent : parents) {
                result.append(parent.getName()).append(", ");
            }
            if (result.length() > 0) {
                result.setLength(result.length() - 2);
            }
        }

        if (!children.isEmpty()) {
            result.append("; дети: ");
            for (Sortable child : children) {
                result.append(child.getName()).append(", ");
            }
            if (result.length() > 0) {
                result.setLength(result.length() - 2);
            }
        }

        return result.toString();
    }
}


