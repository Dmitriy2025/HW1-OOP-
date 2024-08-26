package family_tree;

import family_tree.model.FamilyTreeService;
import family_tree.model.data.Dog;
import family_tree.model.data.Human;
import family_tree.model.writer.FileHandler;
import family_tree.view.FamilyTreeConsole;
import family_tree.presenter.Presenter;


public class Main {
    public static void main(String[] args) {

        FileHandler fileHandler = new FileHandler();
        fileHandler.setPath("family_tree.dat");

        FamilyTreeService<Dog> familyTreeService = new FamilyTreeService<>(Dog.class, fileHandler);


        Presenter<Dog> presenter = new Presenter<>(familyTreeService, null);

        FamilyTreeConsole<Dog> view = new FamilyTreeConsole<>(presenter);

        presenter.setView(view);

        view.start();
    }
}