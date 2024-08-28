package family_tree.model.data;

import java.time.LocalDate;

public class Human extends FamilyMember {

    public Human(int id, String name, Gender gender, LocalDate birthDate, LocalDate deathDate) {
        super(id, name, gender, birthDate, deathDate);
    }
}


