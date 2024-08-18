package family_tree.view.commands;

import family_tree.model.data.Sortable;
import family_tree.view.FamilyTreeConsole;
public class FindPersonInteractive<T extends Sortable> extends Command<T> {

    public FindPersonInteractive(FamilyTreeConsole<T> console) {
        super(console);
    }

    @Override
    public String getDescription() {
        return "Найти субъекта по имени или ID";
    }

    @Override
    public void execute() {
        console.findPersonInteractive();
    }
}

