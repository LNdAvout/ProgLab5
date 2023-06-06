package collectionUnit.collections;

import collectionUnit.Country;
import commandUnit.*;
import exception.InFileException;

import java.util.Locale;

public class CountryForm extends Form<Country>{
    private final Printable console;
    private final UserInputtable scanner;
    public CountryForm(Printable console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new FilesExecuteManager()
                : new ConsoleInput();
    }

    @Override
    public Country build() {
        console.println("Страны: ");
        console.println(Country.names());
        while (true){
            console.println("Введите страну: ");
            String input = scanner.nextLine().trim();
            try{
                return Country.valueOf(input.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException exception){
                console.println("Такой страны нет в списке");
                if (Console.isFileMode()) throw new InFileException();
            }
        }
    }

}
