package family_tree.view.commands;

import family_tree.model.data.FamilyMember;
import family_tree.view.FamilyTreeConsole;
public class SortByName<T extends FamilyMember> extends Command<T> {
    public SortByName(FamilyTreeConsole<T> console) {
        super(console);
    }

    @Override
    public String getDescription() {
        return "Сортировать по имени";
    }

    @Override
    public void execute() {
        console.sortByName();
    }
}
