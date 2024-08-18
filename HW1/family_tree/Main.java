package family_tree;

import family_tree.model.data.Human;
import family_tree.presenter.Presenter;
import family_tree.view.FamilyTreeConsole;

public class Main {
    public static void main(String[] args) {

        Presenter<Human> presenter = new Presenter<>(null);

        FamilyTreeConsole<Human> familyTreeConsole = new FamilyTreeConsole<>(presenter);
        presenter.setView(familyTreeConsole);

        familyTreeConsole.start();
    }
}