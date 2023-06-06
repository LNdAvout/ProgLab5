package commandUnit.commands;

import commandUnit.Console;
import commandUnit.FilesExecuteManager;
import exception.CommandNoSearch;
import exception.CommandRuntime;
import exception.ExitException;
import exception.IncorrectArgument;
import managers.CommandManager;
import managers.FileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

public class ExecuteScript extends Command{
    private final FileManager fileManager;
    private final Console console;
    private final CommandManager commandManager;
    public ExecuteScript(Console console, FileManager fileManager, CommandManager commandManager) {
        super("execute_script", " file_name: считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.fileManager = fileManager;
        this.console = console;
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String args) throws CommandRuntime, ExitException, IncorrectArgument {
        if (args == null || args.isEmpty()) {
            console.println("Путь не распознан");
            return;
        } else console.println("Путь распознан");
        Console.setFileMode(true);
        if (!new File(args).canRead()) {
            console.println("Файл невозможно прочитать");
        } else {
            try {
                FilesExecuteManager.pushFile(args);
                for (String line = FilesExecuteManager.readLine(); line != null; line = FilesExecuteManager.readLine()) {
                    try {
                        commandManager.addToHistory(line);
                        String[] cmd = (line + " ").split(" ", 2);
                        if (cmd[0].isBlank()) return;
                        if (cmd[0].equals("execute_script")) {
                            if (FilesExecuteManager.fileRepeat(cmd[1])) {
                                console.println("Найдена рекурсия по пути " + new File(cmd[1]).getAbsolutePath());
                                continue;
                            }
                        }
                        console.println("Выполнение команды " + cmd[0]);
                        commandManager.execute(cmd[0], cmd[1]);
                        if (cmd[0].equals("execute_script")) {
                            FilesExecuteManager.popFile();
                        }
                    } catch (NoSuchElementException exception) {
                        console.println("Пользовательский ввод не обнаружен!");
                    } catch (CommandNoSearch commandNoSearch) {
                        console.println("Такой команды нет в списке");
                    } catch (IncorrectArgument e) {
                        console.println("Введены неправильные аргументы команды");
                    } catch (CommandRuntime e) {
                        console.println("Ошибка при исполнении команды");
                    }
                }
                FilesExecuteManager.popFile();
            } catch (FileNotFoundException fileNotFoundException) {
                console.println("Такого файла не существует");
            } catch (CommandNoSearch commandNoSearch) {
                console.println("Такой команды не существует");
            } catch (IOException e) {
                console.println("Ошибка ввода вывода");
            }
        }
    }
}
