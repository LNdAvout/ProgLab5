package collectionUnit;



import java.util.Objects;


public class Coordinates implements Validator {
    private Integer x; //Максимальное значение поля: 391, Поле не может быть null
    private Float  y;

    public Coordinates(Integer x, Float y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    @Override
    public boolean validate(){
        return !(this.x == null || this.x > 391);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(that.y, y) == 0 && x.equals(that.x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
