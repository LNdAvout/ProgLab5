import commandUnit.Console;
import commandUnit.commands.*;
import exception.ExitException;
import managers.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager = new FileManager(console, collectionManager);
        CommandManager commandManager = new CommandManager();
        try{
            fileManager.findFile("file_path");
            fileManager.createObjects();
            console.println("Для загрузки прошлого автосохранения введите любой символ");
            Scanner userScanner = ScannerManager.getUserScanner();
            String userInput = userScanner.nextLine();
            if (!userInput.isEmpty()){
                fileManager.findFile("tmp_path");
                fileManager.createObjects();
            } else {
                console.println("Автосохранение не загружено");
            }
        } catch (ExitException e){
            console.println("Завершение программы.");
            return;
        }
        commandManager.addCommand(List.of(
                new Clear(console, collectionManager, fileManager),
                new Help(console, commandManager),
                new Insert(console, collectionManager, fileManager),
                new UpdateId(console, collectionManager, fileManager),
                new RemoveKey(console, collectionManager, fileManager),
                new RemoveLowerKey(console, collectionManager, fileManager),
                new ReplaceIfLowe(console, collectionManager, fileManager),
                new Show(console, collectionManager),
                new History(console, commandManager),
                new Info(console, collectionManager),
                new ExecuteScript(console, fileManager, commandManager),
                new FilterByPerson(console, collectionManager),
                new FilterStartsWithName(console, collectionManager),
                new PrintFieldAscendingType(console, collectionManager),
                new Save(console, fileManager),
                new Exit()
        ));
        new RuntimeManager(console, commandManager).interactiveMode();
    }
}