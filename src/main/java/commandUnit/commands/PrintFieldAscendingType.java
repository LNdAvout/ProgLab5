package commandUnit.commands;

import commandUnit.Console;
import exception.IncorrectArgument;
import managers.CollectionManager;

import java.util.ArrayList;

public class PrintFieldAscendingType extends Command{
    private final CollectionManager collectionManager;
    private final Console console;

    public PrintFieldAscendingType(Console console, CollectionManager collectionManager){
        super("print_field_ascending_type", " : вывести значения поля type всех элементов в порядке возрастания");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args) throws IncorrectArgument {
        if (!args.isBlank()) throw new IncorrectArgument();
        try {
            console.println("Вывод значений");
            ArrayList<String> strings = collectionManager.print_field_ascending_type();
            for (String string : strings) {
                console.println(string);
            }
            console.println("Это все найденные значения");
        } catch (RuntimeException e) {
            console.println("Person не валиден");
        }
    }
}
