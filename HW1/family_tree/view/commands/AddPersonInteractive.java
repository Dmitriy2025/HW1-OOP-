package family_tree.view.commands;

import family_tree.model.data.Sortable;
import family_tree.view.FamilyTreeConsole;

public class AddPersonInteractive<T extends Sortable> extends Command<T> {

    public AddPersonInteractive(FamilyTreeConsole<T> console) {
        super(console);
    }

    @Override
    public String getDescription() {
        return "Добавить субъект в генеалогическое древо";
    }

    @Override
    public void execute() {
        console.addPersonInteractive();
    }
}