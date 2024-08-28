package family_tree.model.data;

import java.time.LocalDate;

public class FamilyTreePopulator {

    public static <T extends FamilyMember> void populateFamilyTree(FamilyTree<T> familyTree, Class<T> type) {
        if (type == Human.class) {
            familyTree.addPerson("Иван", Gender.Male, LocalDate.of(1954, 1, 1), null, type);
            familyTree.addPerson("Мария", Gender.Female, LocalDate.of(1956, 2, 2), null, type);
            familyTree.addPerson("Павел", Gender.Male, LocalDate.of(1979, 3, 3), null, type);
            familyTree.addPerson("Людмила", Gender.Female, LocalDate.of(1982, 4, 4), null, type);
            familyTree.addPerson("Михаил", Gender.Male, LocalDate.of(2004, 5, 5), null, type);
            familyTree.addPerson("Елена", Gender.Female, LocalDate.of(2006, 6, 6), null, type);
            familyTree.addPerson("Дмитрий", Gender.Male, LocalDate.of(2008, 7, 7), null, type);
            familyTree.addPerson("Анна", Gender.Female, LocalDate.of(1995, 8, 8), null, type);
            familyTree.addPerson("Сергей", Gender.Male, LocalDate.of(2019, 9, 9), null, type);
            familyTree.addPerson("Лариса", Gender.Female, LocalDate.of(2021, 10, 10), null, type);

            FamilyMember ivan = familyTree.findPersonByName("Иван");
            FamilyMember maria = familyTree.findPersonByName("Мария");
            FamilyMember pavel = familyTree.findPersonByName("Павел");
            FamilyMember lyudmila = familyTree.findPersonByName("Людмила");
            FamilyMember mikhail = familyTree.findPersonByName("Михаил");
            FamilyMember elena = familyTree.findPersonByName("Елена");
            FamilyMember dmitry = familyTree.findPersonByName("Дмитрий");
            FamilyMember anna = familyTree.findPersonByName("Анна");
            FamilyMember sergey = familyTree.findPersonByName("Сергей");
            FamilyMember larisa = familyTree.findPersonByName("Лариса");

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
        } else if (type == Dog.class) {
            familyTree.addPerson("Рекс", Gender.Male, LocalDate.of(2015, 1, 1), null, type);
            familyTree.addPerson("Лайка", Gender.Female, LocalDate.of(2018, 5, 5), null, type);
            familyTree.addPerson("Шарик", Gender.Male, LocalDate.of(2023, 3, 4), null, type);

            FamilyMember rex = familyTree.findPersonByName("Рекс");
            FamilyMember laika = familyTree.findPersonByName("Лайка");
            FamilyMember sharik = familyTree.findPersonByName("Шарик");

            rex.addChild(sharik);
            laika.addChild(sharik);
        }
    }
}

