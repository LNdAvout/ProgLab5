package collectionUnit;

import collectionUnit.Color;
import collectionUnit.Country;
import collectionUnit.Location;

import java.util.Date;

public class Person implements Validator {
    private Integer birthday; //Поле не может быть null
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле не может быть null
    private Location location; //Поле не может быть null

    public Person(Integer birthday, Color hairColor, Country nationality, Location location) {
        this.birthday = birthday;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean validate(){
        if (this.birthday == null) return false;
        if (this.hairColor == null) return false;
        if (this.nationality == null) return false;
        return this.location != null;
    }

    @Override
    public String toString() {
        return "Person: " +
                "birthday = " + birthday.toString() +
                ", hairColor = " + hairColor +
                ", nationality = " + nationality +
                ", location = " + location.toString();
    }

}