package collectionUnit;

public enum Color {
    GREEN,
    RED,
    BLACK;

    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var forms : values()) {
            nameList.append(forms.name()).append("\n");
        }
        return nameList.substring(0, nameList.length()-1);
    }

    public static Color getByColor(String name){
        return switch (name) {
            case "green" -> GREEN;
            case "red" -> RED;
            case "black" -> BLACK;
            default -> throw new RuntimeException("Цвет может быть green, или red, или black");
        };
    }

    public static String getByName(Color color) {
        return switch (color) {
            case GREEN -> "green";
            case RED -> "red";
            case BLACK -> "black";
            default -> "";
        };
    }
}
