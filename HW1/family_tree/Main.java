package family_tree;

import family_tree.console.FamilyTreeConsole;
import family_tree.data.FamilyTree;
import family_tree.data.Human;
import family_tree.writer.FileHandler;
import family_tree.writer.Writer;

public class Main {
    public static void main(String[] args) {
        FamilyTree familyTree = new FamilyTree();
        familyTree.populateFamilyTree(Human.class);

        Writer fileHandler = new FileHandler();

        FamilyTreeConsole familyTreeConsole = new FamilyTreeConsole(familyTree, fileHandler);
        familyTreeConsole.showMenu();
    }
}