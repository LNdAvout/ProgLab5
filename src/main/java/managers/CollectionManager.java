package managers;

import collectionUnit.*;
import exception.IncorrectForm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CollectionManager {
    private final LinkedHashMap<Integer, Ticket> collection = new LinkedHashMap<>();
    private java.time.LocalDateTime lastDate;

    public LinkedHashMap<Integer, Ticket> getCollection() {
        return collection;
    }
    public void setLastDate(){
        this.lastDate =  LocalDateTime.now();
    }

    public String getLastDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (lastDate == null) {
            return null;
        }
        return dtf.format(lastDate);
    }

    public int getSize(){
        return collection.size();
    }
    public String collectionType() {
        return collection.getClass().getName();
    }

    public int collectionSize() {
        return collection.size();
    }

    public void clear() {
        this.collection.clear();
        this.lastDate = null;
    }

    public Ticket getById(int id) {
        for (Ticket element : collection.values()) {
            if (element.getId() == id) return element;
        }
        return null;
    }

    public Integer getKey(int id) {
        for (Map.Entry<Integer,Ticket> entry : collection.entrySet()) {
            if (entry.getValue().getId() == id) return entry.getKey();
        }
        return null;
    }
    public void editById(int id, Ticket newElement) throws IncorrectForm{
        Integer key = this.getKey(id);
        collection.replace(key, newElement);
    }
    public boolean checkId(int id){
        return collection.values().stream().anyMatch((x) -> x.getId() == id);
    }

    public boolean checkKey(int key){
        return collection.containsKey(key);
    }

    public void addElement(Integer key, Ticket ticket) throws IncorrectForm {
        if (!ticket.validate()) throw new IncorrectForm();
        collection.put(key, ticket);
    }

    public void remove_key(Integer key) {
            this.collection.remove(key);
    }

    public void remove_lower_key(Integer key) {
        ArrayList<Integer> keys = new ArrayList<>();
        for(Map.Entry<Integer, Ticket> entry : this.getCollection().entrySet()){
            if (key >= entry.getKey()) keys.add((entry.getKey()));
        }
        for (Integer k : keys) {
            this.remove_key(k);
        }
    }

    public ArrayList<String> transf(ArrayList<Ticket> tickets) {
        ArrayList<String> strings = new ArrayList<>();
        for (Ticket ticket : tickets) {
            String c_y;
            if (ticket.getCoordinates().getY() == null) {
                c_y = "";
            } else {
                c_y = ticket.getCoordinates().getY().toString();
            }

            String l_x;
            if (ticket.getPerson().getLocation().getX() == null) {
                l_x = "";
            } else {
                l_x = ticket.getPerson().getLocation().getX().toString();
            }
            strings.add(ticket.getId() + "," + ticket.getName() + "," + ticket.getCoordinates().getX() +
                    "," + c_y + "," + ticket.getCreationDate() + "," + ticket.getPrice() +
                    "," + ticket.getComment() + "," + TicketType.getByName(ticket.getType()) + "," +
                    ticket.getPerson().getBirthday() + "," + Color.getByName(ticket.getPerson().getHairColor()) +
                    "," + Country.getByName(ticket.getPerson().getNationality()) + "," +
                    l_x + "," + ticket.getPerson().getLocation().getY() +
                    "," + ticket.getPerson().getLocation().getName());
        }
        return strings;
    }
    public ArrayList<String> filter_starts_with_name(String name) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        for (Map.Entry<Integer, Ticket> entry : this.getCollection().entrySet()) {
            tickets.add(entry.getValue());
        }
        String serchName = " " + name;
        ArrayList<Ticket> ntickets = new ArrayList<>();
        for (Ticket ticket : tickets) {
            String osnName = " " + ticket.getName();
            if (osnName.contains(serchName)) ntickets.add(ticket);
        }
        return transf(ntickets);
    }

    public ArrayList<String> filter_by_person(String person) {
        String[] element = person.split(" ");
        ArrayList<Ticket> tickets = new ArrayList<>();
        ArrayList<Ticket> ptickets = new ArrayList<>();
        for(Map.Entry<Integer, Ticket> entry : this.getCollection().entrySet()){
                tickets.add(entry.getValue());
        }
        for (Ticket ticket : tickets){
        if (Objects.equals(element[0], ticket.getPerson().getBirthday().toString()) &&
                Objects.equals(element[1], ticket.getPerson().getHairColor().toString()) &&
                Objects.equals(element[2], ticket.getPerson().getNationality().toString()) &&
                Objects.equals(element[3], ticket.getPerson().getLocation().getX().toString()) &&
                Objects.equals(Long.valueOf(element[4]), ticket.getPerson().getLocation().getY())) {
            ptickets.add(ticket);
            }
        }
        return transf(ptickets);
    }

    public ArrayList<String> print_field_ascending_type() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        for(Map.Entry<Integer, Ticket> entry : this.getCollection().entrySet()){
            tickets.add(entry.getValue());
        }
        ArrayList<String> strings = new ArrayList<>();
        for (Ticket ticket : tickets) {
            strings.add(ticket.getPerson().getHairColor().toString());
        }
        return strings;
    }

    public void replace_if_lowe(Integer key, Ticket ticket) {
        Ticket t = this.collection.get(key);
        if (ticket.getPrice() < t.getPrice()) collection.replace(key, ticket);
    }

    public void sortedByPrise() throws IncorrectForm {
        ArrayList<Ticket> tickets = new ArrayList<>();
        for(Map.Entry<Integer, Ticket> entry : this.getCollection().entrySet()){
            tickets.add(entry.getValue());
        }
        ArrayList<Integer> keys = new ArrayList<>();
        Collections.sort(tickets);
        for (Ticket ticket : tickets) {
            keys.add(this.getKey(ticket.getId()));
        }
        this.collection.clear();
        for (int i = 0; i < keys.size(); i++) {
            this.addElement(keys.get(i), tickets.get(i));
        }
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";
        var last = collection.entrySet().toArray()[collection.size() -1];

        StringBuilder info = new StringBuilder();
        for (Ticket ticket : collection.values()) {
            info.append(ticket);
            if (ticket != last) info.append("\n\n");
        }
        return info.toString();
    }
}
