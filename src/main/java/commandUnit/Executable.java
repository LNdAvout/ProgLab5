package commandUnit;

import exception.CommandRuntime;
import exception.ExitException;
import exception.IncorrectArgument;

public interface Executable {
    void execute(String args) throws CommandRuntime, IncorrectArgument, ExitException;
}
