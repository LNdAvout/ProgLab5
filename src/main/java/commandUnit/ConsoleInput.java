package commandUnit;

import managers.ScannerManager;

import java.util.Scanner;

public class ConsoleInput implements UserInputtable {
    private static final Scanner userScanner = ScannerManager.getUserScanner();

    @Override
    public String nextLine(){
        return userScanner.nextLine();
    }
}
