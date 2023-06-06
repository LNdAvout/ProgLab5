package collectionUnit.collections;

import collectionUnit.*;
import collectionUnit.Location;
import collectionUnit.Person;
import commandUnit.*;
import exception.InFileException;


public class PersonForm extends Form<Person>{
    private final Printable console;
    private final UserInputtable scanner;
    public PersonForm(Printable console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new FilesExecuteManager()
                : new ConsoleInput();
    }

    @Override
    public Person build(){
        return new Person(
                askBirthday(),
                askHairColor(),
                askNationality(),
                askLocation());
    }

    private Integer askBirthday(){
        while (true) {
            console.println("Введите возраст");
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                console.println("Возраст должен быть числом типа int");
                if (Console.isFileMode()) throw new InFileException();
            }
        }
    }

    private Color askHairColor(){
        return new ColorForm(console).build();
    }
    private Country askNationality(){
        return new CountryForm(console).build();
    }

    private Location askLocation(){
        return new LocationForm(console).build();
    }
}
