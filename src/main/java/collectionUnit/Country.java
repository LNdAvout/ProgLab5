package collectionUnit;

public enum Country {
    UNITED_KINGDOM,
    FRANCE,
    CHINA,
    VATICAN;

    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var forms : values()) {
            nameList.append(forms.name()).append("\n");
        }
        return nameList.substring(0, nameList.length()-1);
    }

    public static Country getByCountry(String name){
        return switch (name) {
            case "united_kingdom" -> UNITED_KINGDOM;
            case "france" -> FRANCE;
            case "china" -> CHINA;
            case "vatican" -> VATICAN;
            default -> throw new RuntimeException("Страна может быть united_kingdom, или france, или china, или vatican");
        };
    }

    public static String getByName(Country country) {
        return switch (country) {
            case UNITED_KINGDOM -> "united_kingdom";
            case FRANCE -> "france";
            case CHINA -> "china";
            case VATICAN -> "vatican";
            default -> "";
        };
    }
}
