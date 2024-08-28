package family_tree.model.data;

import java.time.LocalDate;

public class Dog extends FamilyMember {
    public Dog(int id, String name, Gender gender, LocalDate birthDate, LocalDate deathDate) {
        super(id, name, gender, birthDate, deathDate);
    }
}
