package family_tree.view.commands;

import family_tree.model.data.FamilyMember;
import family_tree.view.FamilyTreeConsole;
public class LoadFamilyTree<T extends FamilyMember> extends Command<T> {

    public LoadFamilyTree(FamilyTreeConsole<T> console) {
        super(console);
    }

    @Override
    public String getDescription() {
        return "Загрузить генеалогическое древо из файла";
    }

    @Override
    public void execute() {
        console.loadFamilyTreeInteractive();
    }
}
