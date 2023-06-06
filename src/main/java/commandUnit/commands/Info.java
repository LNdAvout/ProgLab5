package commandUnit.commands;

import commandUnit.Console;
import exception.IncorrectArgument;
import managers.CollectionManager;
import managers.FileManager;

public class Info extends Command{
    private CollectionManager collectionManager;
    private Console console;

    public Info(Console console, CollectionManager collectionManager) {
        super("info", ": вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String args) throws IncorrectArgument {
        if (!args.isBlank()) throw new IncorrectArgument();
        if (collectionManager.getLastDate() == null) {
            console.println("Коллекция не инициализирована");
        }
        else console.println("Таблица: ключ - Integer, хранимые данные - Ticket\nДата инициализации: "
                + collectionManager.getLastDate() + "\nКоличество элементов: " + collectionManager.getSize());
    }
}
