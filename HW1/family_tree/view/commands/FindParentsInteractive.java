package family_tree.view.commands;

import family_tree.model.data.FamilyMember;
import family_tree.view.FamilyTreeConsole;

public class FindParentsInteractive<T extends FamilyMember> extends Command<T> {

    public FindParentsInteractive(FamilyTreeConsole<T> console) {
        super(console);
    }

    @Override
    public String getDescription() {
        return "Найти родителей субъекта";
    }

    @Override
    public void execute() {
        console.findParentsInteractive();
    }
}
