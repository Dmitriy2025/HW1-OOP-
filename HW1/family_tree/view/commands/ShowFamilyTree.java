package family_tree.view.commands;

import family_tree.view.FamilyTreeConsole;
import family_tree.model.data.Sortable;

public class ShowFamilyTree<T extends Sortable> extends Command<T> {

    public ShowFamilyTree(FamilyTreeConsole<T> console) {
        super(console);
    }

    @Override
    public String getDescription() {
        return "Показать генеалогическое древо";
    }

    @Override
    public void execute() {
        console.showFamilyTree();
    }
}

