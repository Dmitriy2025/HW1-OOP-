package family_tree.view.commands;

import family_tree.model.data.Sortable;
import family_tree.view.FamilyTreeConsole;
public class SaveFamilyTree<T extends Sortable> extends Command<T> {

    public SaveFamilyTree(FamilyTreeConsole<T> console) {
        super(console);
    }

    @Override
    public String getDescription() {
        return "Сохранить генеалогическое древо в файл";
    }

    @Override
    public void execute() {
        console.saveFamilyTreeInteractive();
    }
}
