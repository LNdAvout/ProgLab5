package commandUnit.commands;

import collectionUnit.Ticket;
import collectionUnit.collections.TicketForm;
import commandUnit.Console;
import exception.InFileException;
import exception.IncorrectArgument;
import exception.IncorrectForm;
import managers.CollectionManager;
import managers.FileManager;

public class UpdateId extends Command {
    private final CollectionManager collectionManager;
    private final Console console;
    private final FileManager fileManager;

    public UpdateId(Console console, CollectionManager collectionManager, FileManager fileManager) {
        super("update", " id {element}: обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.console = console;
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String args) throws IncorrectArgument {
        if (args.isBlank()) throw new IncorrectArgument();
        class NoSuchId extends RuntimeException {

        }
        try {
            int id = Integer.parseInt(args.trim());
            if (!collectionManager.checkId(id)) throw new NoSuchId();
            console.println("Создание нового объекта Ticket");
            Ticket newElement = new TicketForm(console).build();
            collectionManager.editById(id, newElement);
            collectionManager.sortedByPrise();
            console.println("Обновление объекта Ticket прошло успешно!");
            fileManager.autoSaveObjects();
        } catch (NoSuchId err) {
            console.println("В коллекции нет элемента с таким id");
        } catch (IncorrectForm invalidForm) {
            console.println("Поля объекта не валидны! Объект не создан!");
        } catch (NumberFormatException exception) {
            console.println("id должно быть числом типа int");
        } catch (InFileException e) {
            console.println("Поля в файле не валидны! Объект не создан");
        }
    }
}
