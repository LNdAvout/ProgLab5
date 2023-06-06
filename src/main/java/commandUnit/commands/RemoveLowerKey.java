package commandUnit.commands;

import commandUnit.Console;
import exception.InFileException;
import exception.IncorrectArgument;
import exception.IncorrectForm;
import managers.CollectionManager;
import managers.FileManager;

public class RemoveLowerKey extends Command{
    private final CollectionManager collectionManager;
    private final Console console;
    private final FileManager fileManager;
    public RemoveLowerKey(Console console, CollectionManager collectionManager, FileManager fileManager){
        super("remove_lower_key", " null : удалить из коллекции все элементы, ключ которых меньше, чем заданный");
        this.console = console;
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String args) throws IncorrectArgument {
        if (args.isBlank()) throw new IncorrectArgument();
        try {
            int key = Integer.parseInt(args.trim());
            console.println("Удаление элементов");
            collectionManager.remove_lower_key(key);
            collectionManager.sortedByPrise();
            console.println("Элементы удалёны");
            fileManager.autoSaveObjects();
        } catch (IncorrectForm incorrectForm) {
            console.println("Поля объектов не валидны, объекты не удалёны!");
        } catch (NumberFormatException e) {
            console.println("Ключ должен быть числом типа int!");
        } catch (InFileException inFileException) {
            console.println("Поля в файле не валидны, объект не создан!");
        }
    }

}
