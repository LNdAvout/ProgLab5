package commandUnit.commands;

import collectionUnit.*;
import commandUnit.Console;
import exception.InFileException;
import exception.IncorrectArgument;
import exception.IncorrectForm;
import managers.CollectionManager;
import managers.FileManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReplaceIfLowe extends Command{
    private final CollectionManager collectionManager;
    private final Console console;
    private final FileManager fileManager;
    public ReplaceIfLowe(Console console, CollectionManager collectionManager, FileManager fileManager){
        super("replace_if_lowe", " null {element} : заменить значение по ключу, если новое значение меньше старого");
        this.console = console;
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String args) throws IncorrectArgument {
        if (args.isBlank()) throw new IncorrectArgument();
        try {
            String[] element = args.split(",");
            Coordinates coordinates;
            if (element[4].isEmpty()) {
                coordinates = new Coordinates(Integer.valueOf(element[3]), null);
            } else {
                coordinates = new Coordinates(Integer.valueOf(element[3]), Float.valueOf(element[4]));
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(element[5], formatter);
            TicketType type = TicketType.getByType(element[8]);
            Color color = Color.getByColor(element[10]);
            Country country = Country.getByCountry(element[11]);
            Location location;
            if (element[12].isEmpty()) {
                location = new Location(null, Long.valueOf(element[13]), element[14]);
            } else {
                location = new Location(Double.valueOf(element[12]), Long.valueOf(element[13]), element[14]);
            }
            Person person = new Person(Integer.valueOf(element[9]), color, country, location);
            Ticket ticket = new Ticket(Integer.valueOf(element[1]), element[2], coordinates, dateTime,
                    Float.valueOf(element[6]), element[7], type, person);
            Integer key = Integer.valueOf(element[0]);
            if (!collectionManager.checkKey(key)) {
                    console.println("Элемента с таким ключём не существует");
                } else {
                collectionManager.replace_if_lowe(key, ticket);
                collectionManager.sortedByPrise();
                console.println("Замена элемента по ключу выполнена");
                fileManager.autoSaveObjects();
            }
        } catch (RuntimeException e) {
            console.println("Элемент не валиден");
        } catch (IncorrectForm incorrectForm) {
            console.println("Поля объектов не валидны, объекты не удалёны!");
        }
    }
}
