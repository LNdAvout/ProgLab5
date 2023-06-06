package collectionUnit.collections;

import collectionUnit.Location;
import commandUnit.*;
import exception.InFileException;

public class LocationForm extends Form<Location>{
    private final Printable console;
    private final UserInputtable scanner;
    public LocationForm(Printable console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new FilesExecuteManager()
                : new ConsoleInput();
    }

    @Override
    public Location build(){
        return new Location(
                askX(),
                askY(),
                askName());
    }

    private double askX(){
        while (true) {
            console.println("Введите координату X");
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                console.println("X должно быть числом типа double");
                if (Console.isFileMode()) throw new InFileException();
            }
        }
    }

    private Long askY(){
        while (true) {
            console.println("Введите координату Y");
            String input = scanner.nextLine().trim();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException exception) {
                console.println("Y должно быть числом типа long");
                if (Console.isFileMode()) throw new InFileException();
            }
        }
    }

    private String askName(){
        while (true) {
            console.println("Введите название локации");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                console.println("Название локации не может быть пустым");
            if (Console.isFileMode()) throw new InFileException();
            } else {
                return name;
            }
        }
    }
}
