package commandUnit.commands;

import collectionUnit.*;
import commandUnit.Console;
import exception.InFileException;
import exception.IncorrectArgument;
import exception.IncorrectForm;
import managers.CollectionManager;
import managers.FileManager;

import java.util.ArrayList;

public class FilterByPerson extends Command{
    private final CollectionManager collectionManager;
    private final Console console;

    public FilterByPerson(Console console, CollectionManager collectionManager){
        super("filter_by_person", " person : вывести элементы, значение поля person которых равно заданному");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args) throws IncorrectArgument {
        if (args.isBlank()) throw new IncorrectArgument();
        try {
            console.println("Поиск элементов");
            ArrayList<String> strings = collectionManager.filter_by_person(args);
            if (strings.isEmpty()) {
                console.println("");
            } else {
                for (String string : strings) {
                    console.println(string);
                }
            }
            console.println("Это все найденные элементы");
        } catch (RuntimeException e) {
            console.println("Person не валиден");
        }
    }
}
