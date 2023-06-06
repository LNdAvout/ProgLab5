package commandUnit.commands;

import commandUnit.Console;
import exception.IncorrectArgument;
import managers.CollectionManager;
import managers.FileManager;

public class Clear extends Command{
    private final CollectionManager collectionManager;
    private final Console console;
    private final FileManager fileManager;

    public Clear(Console console, CollectionManager collectionManager, FileManager fileManager) {
        super("clear", ": очистить коллекцию");
        this.collectionManager = collectionManager;
        this.console = console;
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String args) throws IncorrectArgument {
        if (!args.isBlank()) throw new IncorrectArgument();
        collectionManager.clear();
        console.println("Всё удалено");
        fileManager.autoSaveObjects();
    }
}
