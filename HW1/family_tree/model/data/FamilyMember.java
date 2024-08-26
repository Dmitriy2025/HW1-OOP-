package family_tree.model.data;

import java.time.LocalDate;
import java.util.List;

public interface FamilyMember {
    String getName();
    LocalDate getBirthDate();
    void addParent(FamilyMember parent);
    void addChild(FamilyMember child);
    List<FamilyMember> getChildren();
    List<FamilyMember> getParents();
    int getId();
}

