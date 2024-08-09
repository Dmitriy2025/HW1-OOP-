package family_tree.data;

import java.time.LocalDate;
import java.util.List;

public interface Sortable {
    String getName();
    LocalDate getBirthDate();
    void addParent(Sortable parent);
    void addChild(Sortable child);
    List<Sortable> getChildren();
    List<Sortable> getParents();
    int getId();
}

