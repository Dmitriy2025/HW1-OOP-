package family_tree;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FamilyTree familyTree = new FamilyTree();

        Human ivan = new Human(1, "Иван", Gender.Male, LocalDate.of(1954, 1, 1), LocalDate.of(2010, 10, 9));
        Human maria = new Human(2, "Мария", Gender.Female, LocalDate.of(1956, 2, 2), null);
        Human pavel = new Human(3, "Павел", Gender.Male, LocalDate.of(1979, 3, 3), null);
        Human lyudmila = new Human(4, "Людмила", Gender.Female, LocalDate.of(1982, 4, 4), null);
        Human mikhail = new Human(5, "Михаил", Gender.Male, LocalDate.of(2004, 5, 5), null);
        Human elena = new Human(6, "Елена", Gender.Female, LocalDate.of(2006, 6, 6), null);
        Human dmitry = new Human(7, "Дмитрий", Gender.Male, LocalDate.of(2008, 7, 7), null);
        Human anna = new Human(8, "Анна", Gender.Female, LocalDate.of(1995, 8, 8), null);
        Human sergey = new Human(9, "Сергей", Gender.Male, LocalDate.of(2019, 9, 9), null);
        Human larisa = new Human(10, "Лариса", Gender.Female, LocalDate.of(2021, 10, 10), null);

        familyTree.addPerson(ivan);
        familyTree.addPerson(maria);
        familyTree.addPerson(pavel);
        familyTree.addPerson(lyudmila);
        familyTree.addPerson(mikhail);
        familyTree.addPerson(elena);
        familyTree.addPerson(dmitry);
        familyTree.addPerson(anna);
        familyTree.addPerson(sergey);
        familyTree.addPerson(larisa);

        ivan.addChild(pavel);
        ivan.addChild(lyudmila);
        maria.addChild(pavel);
        maria.addChild(lyudmila);

        pavel.addChild(mikhail);
        pavel.addChild(elena);
        lyudmila.addChild(dmitry);
        lyudmila.addChild(anna);

        anna.addChild(sergey);
        anna.addChild(larisa);

        System.out.println("Генеалогическое древо:");
        System.out.println(familyTree);

        System.out.println("Дети Ивана:");
        List<Human> ivansChildren = familyTree.getChildrenOf("Иван");
        for (Human child : ivansChildren) {
            System.out.println(child);
        }
    }
}

