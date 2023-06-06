package collectionUnit;

public enum TicketType {
    VIP,
    USUAL,
    BUDGETARY,
    CHEAP;

    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var forms : values()) {
            nameList.append(forms.name()).append("\n");
        }
        return nameList.substring(0, nameList.length()-1);
    }

    public static TicketType getByType(String name) {
        return switch (name) {
            case "vip" -> VIP;
            case "usual" -> USUAL;
            case "budgetary" -> BUDGETARY;
            case "cheap" -> CHEAP;
            default -> throw new RuntimeException("Тип может быть vip, или usual, или budgetary, или cheap");
        };
    }
    public static String getByName(TicketType type) {
        return switch (type) {
            case VIP -> "vip";
            case USUAL -> "usual";
            case BUDGETARY -> "budgetary";
            case CHEAP -> "cheap";
            default -> "";
        };
    }
}
