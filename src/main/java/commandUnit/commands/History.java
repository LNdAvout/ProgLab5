package commandUnit.commands;

import commandUnit.Console;
import exception.IncorrectArgument;
import managers.CommandManager;
import managers.FileManager;

import java.util.List;

public class History extends Command{
    private CommandManager commandManager;
    private Console console;
;

    public History(Console console, CommandManager commandManager) {
        super("history", ": вывести последние 7 команд (без их аргументов)");
        this.commandManager = commandManager;
        this.console = console;
    }

    @Override
    public void execute(String args) throws IncorrectArgument {
        if (!args.isBlank()) throw new IncorrectArgument();
        List<String> history = commandManager.getCommandHistory();
        for (String command:history.subList(Math.max(history.size() - 5, 0), history.size())){
            console.println(command);
        }
    }
}
