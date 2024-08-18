package family_tree.view.commands;

import family_tree.model.data.Sortable;
import family_tree.view.FamilyTreeConsole;

public class ExitProgram<T extends Sortable> extends Command<T> {
    public ExitProgram(FamilyTreeConsole<T> console) {
        super(console);
    }

    @Override
    public String getDescription() {
        return "Выйти";
    }

    @Override
    public void execute() {
        console.exitProgram();
    }
}