package commandUnit.commands;

import commandUnit.Console;
import exception.InFileException;
import exception.IncorrectArgument;
import exception.IncorrectForm;
import managers.CollectionManager;
import managers.FileManager;

public class RemoveKey extends Command{
    private final CollectionManager collectionManager;
    private final Console console;
    private final FileManager fileManager;

    public RemoveKey(Console console, CollectionManager collectionManager, FileManager fileManager){
        super("remove_key", " null : удалить элемент из коллекции по его ключу");
        this.console = console;
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String args) throws IncorrectArgument {
        if (args.isBlank()) throw new IncorrectArgument();
        try {
            int key = Integer.parseInt(args.trim());
            if (!collectionManager.checkKey(key)) {
                console.println("Элемента с таким ключём не существует");
            } else {
                console.println("Удаление элемента");
                collectionManager.remove_key(key);
                collectionManager.sortedByPrise();
                console.println("Элемент удалён");
                fileManager.autoSaveObjects();
            }
        } catch (IncorrectForm incorrectForm) {
            console.println("Поля объекта не валидны, объект не удалён!");
        } catch (NumberFormatException e) {
            console.println("Ключ должен быть числом типа int!");
        } catch (InFileException inFileException) {
            console.println("Поля в файле не валидны, объект не создан!");
        }
    }

}
