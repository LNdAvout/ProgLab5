package collectionUnit;

import java.util.Objects;

public class Location implements Validator {
    private Double x;
    private Long y; //Поле не может быть null
    private String name; //Строка не может быть пустой, Поле не может быть null

    public Location(Double x, Long y,  String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String x) {
        this.name = name;
    }

    @Override
    public boolean validate(){
        if (this.y == null) return false;
        return !this.name.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        if (Double.compare(location.x, x) != 0) return false;
        if (!y.equals(location.y)) return false;
        return Objects.equals(name, location.name);
    }

    @Override
    public String toString() {
        return "Location: " +
                "x = " + x +
                ", y = " + y +
                ", name = " + name;
    }
}
