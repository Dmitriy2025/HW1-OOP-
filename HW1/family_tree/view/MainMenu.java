package family_tree.view;

import family_tree.view.commands.*;
import family_tree.model.data.Sortable;

import java.util.ArrayList;
import java.util.List;

public class MainMenu<T extends Sortable> {
    private final List<Command<T>> commandList;

    public MainMenu(FamilyTreeConsole<T> familyTC) {
        commandList = new ArrayList<>();
        commandList.add(new ShowFamilyTree<>(familyTC));
        commandList.add(new FindPersonInteractive<>(familyTC));
        commandList.add(new AddPersonInteractive<>(familyTC));
        commandList.add(new FindChildrenInteractive<>(familyTC));
        commandList.add(new FindParentsInteractive<>(familyTC));
        commandList.add(new SaveFamilyTree<>(familyTC));
        commandList.add(new LoadFamilyTree<>(familyTC));
        commandList.add(new SortByName<>(familyTC));
        commandList.add(new SortByAge<>(familyTC));
        commandList.add(new ExitProgram<>(familyTC));

    }

    public String menu(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < commandList.size(); i++) {
            stringBuilder.append(i+1);
            stringBuilder.append(". ");
            stringBuilder.append(commandList.get(i).getDescription());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void execute(int choice){
        Command<T> command = commandList.get(choice-1);
        command.execute();
    }

    public int getSize(){
        return commandList.size();
    }
}
