package managers;

import commandUnit.commands.Command;
import exception.CommandNoSearch;
import exception.CommandRuntime;
import exception.ExitException;
import exception.IncorrectArgument;

import java.util.HashMap;
import java.util.*;
import java.util.stream.Collectors;

public class CommandManager {
    private final HashMap<String, Command> commands = new HashMap<>();

    private final List<String> commandHistory = new ArrayList<>();

    public void addCommand(Command command){
        this.commands.put(command.getName(), command);
    }

    public void addCommand(Collection<Command> commands){
        this.commands.putAll(commands.stream()
                .collect(Collectors.toMap(Command::getName, s -> s)));
    }

    public Collection<Command> getCommands(){
        return commands.values();
    }

    public void addToHistory(String line){
        if(line.isBlank()) return;
        this.commandHistory.add(line);
    }

    public List<String> getCommandHistory(){return commandHistory;}

    public void execute(String name, String args) throws CommandNoSearch, IncorrectArgument,
            CommandRuntime, ExitException {
        Command command = commands.get(name);
        if (command == null) throw new CommandNoSearch();
        command.execute(args);
    }
}
