package family_tree.view.commands;

import family_tree.model.data.FamilyMember;
import family_tree.view.FamilyTreeConsole;

public class FindChildrenInteractive<T extends FamilyMember> extends Command<T> {

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
