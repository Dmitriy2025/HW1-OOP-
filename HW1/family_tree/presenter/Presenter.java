package family_tree.presenter;

import family_tree.view.FamilyTreeConsole;
import family_tree.model.data.FamilyTree;
import family_tree.model.data.Sortable;

public class Presenter<T extends Sortable> {
    private final FamilyTreeConsole<T> console;
    private final FamilyTree<T> familyTree;

    public Presenter(FamilyTreeConsole<T> console) {
        this.console = console;
        this.familyTree = console.getFamilyTree();
    }

    public void showFamilyTree() {
        console.showFamilyTree();
    }

    public void findPersonInteractive(String input) {
    }
}