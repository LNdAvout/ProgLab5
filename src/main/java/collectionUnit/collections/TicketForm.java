package collectionUnit.collections;

import collectionUnit.*;
import commandUnit.*;
import exception.InFileException;

import java.time.LocalDateTime;

public class TicketForm extends Form<Ticket>{
    private final Printable console;
    private final UserInputtable scanner;
    public TicketForm(Printable console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new FilesExecuteManager()
                : new ConsoleInput();
    }

    @Override
    public Ticket build(){
        return new Ticket(
                askName(),
                askCoordinates(),
                LocalDateTime.now(),
                askPrise(),
                askComment(),
                askType(),
                askPerson());
    }

    private String askName(){
        String name;
        while (true) {
            console.println("Введите название билета");
            name = scanner.nextLine().trim();
            if (name.isEmpty()){
                console.println("Название билета не может быть пустым");
                if (Console.isFileMode()) throw new InFileException();
            } else {
                return name;
            }
        }
    }

    private Coordinates askCoordinates(){
        return new CoordinatesForm(console).build();
    }

    private Float askPrise(){
        while (true) {
            console.println("Введите цену билета");
            String input = scanner.nextLine().trim();
            try {
                return Float.parseFloat(input);
            } catch (NumberFormatException exception) {
                console.println("Цена должна быть числом типа float");
                if (Console.isFileMode()) throw new InFileException();
            }
        }
    }

    private String askComment(){
        String name;
        while (true) {
            console.println("Введите комментарий к билету");
            name = scanner.nextLine().trim();
            if (name.isEmpty()){
                console.println("Комментарий билета не может быть пустым");
                if (Console.isFileMode()) throw new InFileException();
            } else {
                return name;
            }
        }
    }

    private TicketType askType(){
        return new TicketTypeForm(console).build();
    }

    private Person askPerson(){
        return new PersonForm(console).build();
    }
}
