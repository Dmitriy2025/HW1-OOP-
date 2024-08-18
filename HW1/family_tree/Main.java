package family_tree;

import family_tree.view.FamilyTreeConsole;
import family_tree.model.data.FamilyTree;
import family_tree.model.data.Human;
import family_tree.model.writer.FileHandler;
import family_tree.model.writer.Writer;

public class Main {
    public static void main(String[] args) {
        FamilyTree<Human> familyTree = new FamilyTree(Human.class);
        familyTree.populateFamilyTree(Human.class);

        Writer fileHandler = new FileHandler();

        FamilyTreeConsole familyTreeConsole = new FamilyTreeConsole(familyTree, fileHandler);
        familyTreeConsole.showMenu();
    }
}