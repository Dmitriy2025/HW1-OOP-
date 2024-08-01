package family_tree;
public class Main {
    public static void main(String[] args) {
        FamilyTree familyTree = new FamilyTree();
        familyTree.populateFamilyTree();

        FamilyTreeConsole familyTreeConsole = new FamilyTreeConsole(familyTree);
        familyTreeConsole.showMenu();
    }
}

