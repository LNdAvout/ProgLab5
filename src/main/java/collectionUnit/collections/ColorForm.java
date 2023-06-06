package collectionUnit.collections;

import collectionUnit.Color;
import commandUnit.*;
import java.util.Locale;

import exception.InFileException;

public class ColorForm extends Form<Color> {
    private final Printable console;
    private final UserInputtable scanner;

    public ColorForm(Printable console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new FilesExecuteManager()
                : new ConsoleInput();
    }

    @Override
    public Color build() {
        console.println("Цвета: ");
        console.println(Color.names());
        while (true){
            console.println("Введите цвет: ");
            String input = scanner.nextLine().trim();
            try{
                return Color.valueOf(input.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException exception){
                console.println("Такого цвета нет в списке");
                if (Console.isFileMode()) throw new InFileException();
            }
        }
    }
}
