package commandUnit.commands;
import exception.IncorrectArgument;
import managers.FileManager;
import commandUnit.Console;


public class Save extends Command{
    private FileManager fileManager;
    private Console console;

    public Save(Console console, FileManager fileManager) {
        super("save", ": сохранить коллекцию в файл");
        this.fileManager = fileManager;
        this.console = console;
    }

    @Override
    public void execute(String args) throws IncorrectArgument {
        if (!args.isBlank()) throw new IncorrectArgument();
        fileManager.saveObjects();
        console.println("Объекты сохранены успешно");
    }
}
