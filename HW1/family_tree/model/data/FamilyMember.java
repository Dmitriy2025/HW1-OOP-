package family_tree.model.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public abstract class FamilyMember implements Serializable {
    private static final long serialVersionUID = 1L;

    protected int id;
    protected String name;
    protected Gender gender;
    protected LocalDate birthDate;
    protected LocalDate deathDate;
    protected List<FamilyMember> parents;
    protected List<FamilyMember> children;

    public FamilyMember(int id, String name, Gender gender, LocalDate birthDate, LocalDate deathDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void addParent(FamilyMember parent) {
        if (!parents.contains(parent)) {
            this.parents.add(parent);
            parent.addChild(this);
        }
    }

    public void addChild(FamilyMember child) {
        if (!children.contains(child)) {
            this.children.add(child);
            child.addParent(this);
        }
    }

    public List<FamilyMember> getChildren() {
        return new ArrayList<>(children);
    }

    public List<FamilyMember> getParents() {
        return new ArrayList<>(parents);
    }

    @Override
    public String toString() {
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
            for (FamilyMember parent : parents) {
                result.append(parent.getName()).append(", ");
            }
            if (result.length() > 0) {
                result.setLength(result.length() - 2);
            }
        }

        if (!children.isEmpty()) {
            result.append("; дети: ");
            for (FamilyMember child : children) {
                result.append(child.getName()).append(", ");
            }
            if (result.length() > 0) {
                result.setLength(result.length() - 2);
            }
        }

        return result.toString();
    }
}
