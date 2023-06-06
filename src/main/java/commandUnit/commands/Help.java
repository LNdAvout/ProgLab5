package commandUnit.commands;

import commandUnit.Console;
import exception.IncorrectArgument;
import managers.CommandManager;
import managers.FileManager;

public class Help extends Command{
    private CommandManager commandManager;
    private Console console;

    public Help(Console console, CommandManager commandManager) {
        super("help", ": вывести справку по доступным командам");
        this.commandManager = commandManager;
        this.console = console;
    }
    @Override
    public void execute(String args) throws IncorrectArgument {
        if (!args.isBlank()) throw new IncorrectArgument();
        commandManager.getCommands()
                .forEach(command -> console.println(command.getName() + command.getDescription()));
    }
}
