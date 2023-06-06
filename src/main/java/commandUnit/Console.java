package commandUnit;

public class Console implements Printable{
    public static boolean fileMode = false;
    public static boolean isFileMode() {
        return fileMode;
    }

    public static void setFileMode(boolean fileMode) {
        Console.fileMode = fileMode;
    }

    @Override
    public void println(String str){
        System.out.println(str);
    }

    @Override
    public void print(String str){
        System.out.print(str);
    }
}
