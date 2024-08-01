package family_tree;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Human implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private Gender gender;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private List<Human> parents;
    private List<Human> children;

    public Human(int id, String name, Gender gender, LocalDate birthDate, LocalDate deathDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    // Геттеры и сеттеры

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public List<Human> getParents() {
        return new ArrayList<>(parents);
    }

    public List<Human> getChildren() {
        return new ArrayList<>(children);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public void addParent(Human parent) {
        if (!parents.contains(parent)) {
            this.parents.add(parent);
            parent.addChild(this);
        }
    }

    public void addChild(Human child) {
        if (!children.contains(child)) {
            this.children.add(child);
            child.addParent(this);
        }
    }

    @Override
    public String toString() {
        String genderString = gender == Gender.Male ? "мужчина" : "женщина";
        StringBuilder result = new StringBuilder();
        result.append(id).append(": ").append(name).append(" (").append(genderString)
                .append(", родился ").append(birthDate);
        if (deathDate != null) {
            result.append(", умер ").append(deathDate);
        }
        result.append(")");

        if (!parents.isEmpty()) {
            result.append("; родители: ");
            for (Human parent : parents) {
                result.append(parent.getName()).append(", ");
            }
            if (result.length() > 0) {
                result.setLength(result.length() - 2);
            }
        }

        if (!children.isEmpty()) {
            result.append("; дети: ");
            for (Human child : children) {
                result.append(child.getName()).append(", ");
            }
            if (result.length() > 0) {
                result.setLength(result.length() - 2);
            }
        }

        return result.toString();
    }
}


