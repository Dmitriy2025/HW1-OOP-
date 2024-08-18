package family_tree.view.commands;

import family_tree.model.data.Sortable;
import family_tree.view.FamilyTreeConsole;

public class FindChildrenInteractive<T extends Sortable> extends Command<T> {

    public FindChildrenInteractive(FamilyTreeConsole<T> console) {
        super(console);
    }

    @Override
    public String getDescription() {
        return "Найти детей субъекта";
    }

    @Override
    public void execute() {
        console.findChildrenInteractive();
    }
}
