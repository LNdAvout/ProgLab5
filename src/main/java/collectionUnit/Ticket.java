package collectionUnit;

import managers.CollectionManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class Ticket implements Validator, Comparable<Ticket>{
    private Integer id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float price; //Значение поля должно быть больше 0
    private String comment; //Длина строки не должна быть больше 527, Поле не может быть null
    private TicketType type; //Поле может быть null
    private Person person; //Поле не может быть null

    private static int nextId = 5;

    public Ticket(String name, Coordinates coordinates, LocalDateTime creationDate, Float price,
                  String comment, TicketType type, Person person) {
        this.id = incNextId();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.comment = comment;
        this.type = type;
        this.person = person;
    }

    public Ticket(Integer id, String name, Coordinates coordinates, LocalDateTime creationDate, Float price,
                  String comment, TicketType type, Person person) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.comment = comment;
        this.type = type;
        this.person = person;
    }

    private static int incNextId(){
        return nextId++;
    }

    public void setNextId(int nId){nextId = nId;}

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCreationDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dtf.format(creationDate);
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Float getPrice(){
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TicketType getType(){
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public Person getPerson(){
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public int compareTo(Ticket o) {
        if (Objects.isNull(o)) return 1;
        return Float.compare(price, o.getPrice());
    }

    @Override
    public boolean validate(){
        if (this.id != null) if (this.id < 0) return false;
        if (this.name == null || this.name.isEmpty()) return false;
        if (this.coordinates == null) return false;
        if (this.creationDate == null) return false;
        if (this.price <= 0) return false;
        if (this.comment.length() > 528) return false;
        if (this.type == null) return false;
        return this.person != null;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket that = (Ticket) o;
        if (price != that.price) return false;
        if (!name.equals(that.name)) return false;
        if (!coordinates.equals(that.coordinates)) return false;
        if (!creationDate.equals(that.creationDate)) return false;
        if (!comment.equals(that.comment)) return false;
        if (!type.equals(that.type)) return false;
        return person.equals(that.person);
    }

    @Override
    public String toString() {
        return "Ticket : " +
                "id = " + id +
                ", name = '" + name + "'" +
                ", coordinates = " + coordinates.toString() +
                ", creationDate = " + creationDate +
                ", price = " + price +
                ", comment = " + comment +
                ", person = " + person;
    }
}
