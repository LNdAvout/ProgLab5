package commandUnit.commands;

import collectionUnit.Ticket;
import commandUnit.Console;
import exception.IncorrectArgument;
import managers.CollectionManager;
import managers.FileManager;


import java.util.ArrayList;
import java.util.Map;

public class Show extends Command{
    private CollectionManager collectionManager;
    private Console console;

    public Show(Console console, CollectionManager collectionManager) {
        super("show", ": вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String args) throws IncorrectArgument {
        if (!args.isBlank()) throw new IncorrectArgument();
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Integer> keys = new ArrayList<>();
        ArrayList<Ticket> tickets = new ArrayList<>();
        for(Map.Entry<Integer, Ticket> entry : collectionManager.getCollection().entrySet()){
            keys.add(entry.getKey());
            tickets.add(entry.getValue());
        }
        for(int i = 0; i < tickets.size(); i++) {
            Integer key = keys.get(i);
            Ticket ticket = tickets.get(i);
            strings.add(key + " " + ticket);
        }
        if (strings.isEmpty()) {
            console.println("Коллекцмя еще не инициализирована");
            return;
        }
        for(String s : strings){
            console.println(s);
        }
    }
}
