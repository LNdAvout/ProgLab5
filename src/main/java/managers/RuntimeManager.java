package managers;

import commandUnit.Console;
import commandUnit.Printable;
import exception.CommandNoSearch;
import exception.CommandRuntime;
import exception.ExitException;
import exception.IncorrectArgument;
;
import java.util.*;

public class RuntimeManager {
    private final Printable console;
    private final CommandManager commandManager;

    public RuntimeManager(Console console, CommandManager commandManager){
        this.console = console;
        this.commandManager = commandManager;
    }

    public void interactiveMode(){
        Scanner userScanner = ScannerManager.getUserScanner();
        while (true) {
            try{
                if (!userScanner.hasNext()) throw new ExitException();
                String userCommand = userScanner.nextLine().trim() + " ";
                this.start(userCommand.split(" ", 2));
                commandManager.addToHistory(userCommand);
            } catch (NoSuchElementException exception) {
                console.println("Пользовательский ввод не обнаружен!");
            } catch (CommandNoSearch commandNoSuch) {
                console.println("Такой команды нет в списке");
            } catch (IncorrectArgument e) {
                console.println("Введены неправильные аргументы команды");
            } catch (CommandRuntime e) {
                console.println("Ошибка при исполнении команды");
            } catch (ExitException exitObliged){
                console.println("До свидания!");
                return;
            }
        }
    }

    public void start(String[] userCommand) throws CommandNoSearch, ExitException, IncorrectArgument, CommandRuntime {
        if (userCommand[0].equals("")) return;
        commandManager.execute(userCommand[0], userCommand[1]);
    }
}
