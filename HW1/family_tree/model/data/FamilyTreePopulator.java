package family_tree.model.data;

import family_tree.model.builder.FamilyTreeBuilder;

import java.time.LocalDate;

public class FamilyTreePopulator {
    public static FamilyTree<Human> populateHumanTree() {
        FamilyTreeBuilder<Human> builder = new FamilyTreeBuilder<>(Human.class);

        builder.addPerson("Иван", Gender.Male, LocalDate.of(1954, 1, 1), null);
        builder.addPerson("Мария", Gender.Female, LocalDate.of(1956, 2, 2), null);
        builder.addPerson("Павел", Gender.Male, LocalDate.of(1979, 3, 3), null);
        builder.addPerson("Людмила", Gender.Female, LocalDate.of(1982, 4, 4), null);
        builder.addPerson("Михаил", Gender.Male, LocalDate.of(2004, 5, 5), null);
        builder.addPerson("Елена", Gender.Female, LocalDate.of(2006, 6, 6), null);
        builder.addPerson("Дмитрий", Gender.Male, LocalDate.of(2008, 7, 7), null);
        builder.addPerson("Анна", Gender.Female, LocalDate.of(1995, 8, 8), null);
        builder.addPerson("Сергей", Gender.Male, LocalDate.of(2019, 9, 9), null);
        builder.addPerson("Лариса", Gender.Female, LocalDate.of(2021, 10, 10), null);

        builder.addParentTo("Павел", "Иван")
                .addParentTo("Павел", "Мария")
                .addParentTo("Людмила", "Иван")
                .addParentTo("Людмила", "Мария")
                .addParentTo("Михаил", "Павел")
                .addParentTo("Елена", "Павел")
                .addParentTo("Дмитрий", "Людмила")
                .addParentTo("Анна", "Людмила")
                .addParentTo("Сергей", "Анна")
                .addParentTo("Лариса", "Анна");

        return builder.build();

    }
    public static FamilyTree<Dog> populateDogTree() {
        FamilyTreeBuilder<Dog> builder = new FamilyTreeBuilder<>(Dog.class);
        builder.addPerson("Рекс", Gender.Male, LocalDate.of(2015, 1, 1), null);
        builder.addPerson("Лайка", Gender.Female, LocalDate.of(2018, 5, 5), null);
        builder.addPerson("Шарик", Gender.Male, LocalDate.of(2023, 3, 4), null);

        builder.addParentTo("Шарик", "Рекс")
                .addParentTo("Шарик", "Лайка");

        return builder.build();
    }
}

