package family_tree.view.commands;

import family_tree.model.data.Sortable;
import family_tree.view.FamilyTreeConsole;

public class SortByAge<T extends Sortable> extends Command<T> {
    public SortByAge(FamilyTreeConsole<T> console) {
        super(console);
    }

    @Override
    public String getDescription() {
        return "Сортировать по возрасту";
    }

    @Override
    public void execute() {
        console.sortByAge();
    }
}
