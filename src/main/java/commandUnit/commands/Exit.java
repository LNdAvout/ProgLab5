package commandUnit.commands;

import exception.ExitException;

public class Exit extends Command{
    public Exit(){
        super("exit", ": завершить программу (без сохранения в файл)");
    }

    @Override
    public void execute(String args) throws ExitException{
        throw new ExitException();
    }
}
