package commandUnit.commands;

import commandUnit.Console;
import exception.IncorrectArgument;
import managers.CollectionManager;

import java.util.ArrayList;

public class FilterStartsWithName extends Command{
    private final CollectionManager collectionManager;
    private final Console console;

    public FilterStartsWithName(Console console, CollectionManager collectionManager){
        super("filter_starts_with_name", " name : вывести элементы, значение поля name которых начинается с заданной подстроки");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args) throws IncorrectArgument {
        if (args.isBlank()) throw new IncorrectArgument();
        try {
            console.println("Поиск элементов");
            ArrayList<String> strings = collectionManager.filter_starts_with_name(args.trim());
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
