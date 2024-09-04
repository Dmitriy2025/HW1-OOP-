package family_tree;

import family_tree.model.FamilyTreeService;
import family_tree.model.builder.FamilyTreeBuilder;
import family_tree.model.data.Dog;
import family_tree.model.data.FamilyTree;
import family_tree.model.data.FamilyTreePopulator;
import family_tree.model.data.Human;
import family_tree.model.writer.FileHandler;
import family_tree.view.FamilyTreeConsole;
import family_tree.presenter.Presenter;


public class Main {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();

        FamilyTreeBuilder<Human> familyTreeBuilder = new FamilyTreeBuilder<>(Human.class);
        FamilyTreeService<Human> familyTreeService = new FamilyTreeService<>(familyTreeBuilder, fileHandler);


        Presenter<Human> presenter = new Presenter<>(familyTreeService, null);
        FamilyTreeConsole<Human> view = new FamilyTreeConsole<>(presenter);

        presenter.setView(view);
        view.start();
    }
}