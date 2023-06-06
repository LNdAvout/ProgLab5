package collectionUnit.collections;

import collectionUnit.TicketType;
import commandUnit.*;
import exception.InFileException;

import java.util.Locale;

public class TicketTypeForm extends Form<TicketType>{
    private final Printable console;
    private final UserInputtable scanner;
    public TicketTypeForm(Printable console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new FilesExecuteManager()
                : new ConsoleInput();
    }

    @Override
    public TicketType build() {
        console.println("Типы билетов: ");
        console.println(TicketType.names());
        while (true){
            console.println("Введите тип билета: ");
            String input = scanner.nextLine().trim();
            try{
                return TicketType.valueOf(input.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException exception){
                console.println("Такого типа билета нет в списке");
                if (Console.isFileMode()) throw new InFileException();
            }
        }
    }
}
