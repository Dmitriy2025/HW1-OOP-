package family_tree.view.commands;

import family_tree.view.FamilyTreeConsole;
import family_tree.model.data.Sortable;

public abstract class Command<T extends Sortable> {
    protected FamilyTreeConsole<T> console;

    public Command(FamilyTreeConsole<T> console) {
        this.console = console;
    }

    public abstract String getDescription();

    public abstract void execute();
}

