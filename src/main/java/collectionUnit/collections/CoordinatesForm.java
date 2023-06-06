package collectionUnit.collections;

import collectionUnit.Coordinates;
import commandUnit.*;
import exception.InFileException;
import exception.IncorrectArgument;

public class CoordinatesForm extends Form<Coordinates>{
    private final Printable console;
    private final UserInputtable scanner;
    public CoordinatesForm(Printable console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new FilesExecuteManager()
                : new ConsoleInput();
    }

    @Override
    public Coordinates build(){
        return new Coordinates(askX(), askY());
    }

    private Integer askX(){
        while (true) {
            console.println("Введите координату X");
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                console.println("X должно быть числом типа int");
                if (Console.isFileMode()) throw new InFileException();
            }
        }
    }

    private Float askY(){
        while (true) {
            console.println("Введите координату Y");
            String input = scanner.nextLine().trim();
            try {
                if (input.isEmpty()) {
                    return null;
                } else {
                    return Float.parseFloat(input);
                }
            } catch (NumberFormatException exception) {
                console.println("X должно быть числом типа float");
                if (Console.isFileMode()) throw new InFileException();
            }
        }
    }

}
