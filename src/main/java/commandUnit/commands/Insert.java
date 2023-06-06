package commandUnit.commands;
import collectionUnit.Ticket;
import collectionUnit.collections.TicketForm;
import commandUnit.Console;
import exception.InFileException;
import exception.IncorrectArgument;
import exception.IncorrectForm;
import managers.CollectionManager;
import managers.FileManager;

public class Insert extends Command{
    private final CollectionManager collectionManager;
    private final Console console;
    private final FileManager fileManager;

    public Insert(Console console, CollectionManager collectionManager, FileManager fileManager){
        super("insert", " {element} : добавить новый элемент с заданным ключом");
        this.console = console;
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String args) throws IncorrectArgument {
        if (args.isBlank()) throw new IncorrectArgument();
        try {
            int key = Integer.parseInt(args.trim());
            if (collectionManager.checkKey(key)) {
                console.println("Элемент с таким ключём уже существует");
            } else {
                console.println("Добавление нового элемента");
                Ticket ticket = new TicketForm(console).build();
                collectionManager.addElement(key, ticket);
                collectionManager.sortedByPrise();
                fileManager.autoSaveObjects();
            }
        } catch (IncorrectForm incorrectForm) {
            console.println("Поля объекта не валидны, объект не создан!");
        } catch (NumberFormatException e) {
            console.println("Ключ должен быть числом типа int!");
        } catch (InFileException inFileException) {
            console.println("Поля в файле не валидны, объект не создан!");
        }
    }
}
