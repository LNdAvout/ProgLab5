package commandUnit.commands;


import exception.ExitException;

public interface Interface {
    void execute() throws ExitException;

    String getName();

    String getDescription();

    boolean showArgument(Object argument);
}
