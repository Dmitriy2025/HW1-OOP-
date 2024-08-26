package family_tree.view.commands;

import family_tree.model.data.FamilyMember;
import family_tree.view.FamilyTreeConsole;

public abstract class Command<T extends FamilyMember> {
    protected FamilyTreeConsole<T> console;

    public Command(FamilyTreeConsole<T> console) {
        this.console = console;
    }

    public abstract String getDescription();

    public abstract void execute();
}

