package managers;

import collectionUnit.*;
import commandUnit.Console;
import commandUnit.Printable;
import exception.ExitException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;


public class FileManager {
    private String text;
    private final Printable console;
    private final CollectionManager collectionManager;

    public FileManager(Console console, CollectionManager collectionManager) {
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public void findFile(String filename) throws ExitException {
        String file_path = System.getenv(filename);
        if (file_path == null || file_path.isEmpty()) {
            console.println("Путь должен быть в переменных окружения в перменной 'file_path'");
            throw new ExitException();
        }
        else console.println("Путь получен успешно");

        File file = new File(file_path);
        if (!file.canRead()) {
            console.println("Невозможно прочитать файл");
            throw new ExitException();
        }
        BufferedInputStream bis;
        FileInputStream fis;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            while (bis.available() > 0) {
                stringBuilder.append((char) bis.read());
            }
            fis.close();
            bis.close();
            if (stringBuilder.isEmpty()) {
                console.println("Файл пустой");
                this.text = "";
                return;
            }
            this.text = stringBuilder.toString();
        } catch (FileNotFoundException fnfe) {
            console.println("Такого файла не найдено");
            throw new ExitException();
        } catch (IOException ioe) {
            console.println("Ошибка ввода/вывода" + ioe);
            throw new ExitException();
        }
    }


    public void createObjects(){
        try{
            String[] array = this.text.split("\n");
            for (int i = 1; i < array.length; i++) {
                String value = array[i];
                String[] element = value.split(",");
                Coordinates coordinates;
                if (element[4].isEmpty()) {
                    coordinates = new Coordinates(Integer.valueOf(element[3]), null);
                } else {
                    coordinates = new Coordinates(Integer.valueOf(element[3]), Float.valueOf(element[4]));
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(element[5], formatter);
                TicketType type = TicketType.getByType(element[8]);
                Color color = Color.getByColor(element[10]);
                Country country = Country.getByCountry(element[11]);
                Location location;
                if (element[12].isEmpty()) {
                    location = new Location(null, Long.valueOf(element[13]), element[14]);
                } else {
                    location = new Location(Double.valueOf(element[12]), Long.valueOf(element[13]), element[14]);
                }
                Person person = new Person(Integer.valueOf(element[9]), color, country, location);
                Ticket ticket = new Ticket(Integer.valueOf(element[1]), element[2], coordinates, dateTime,
                        Float.valueOf(element[6]), element[7], type, person);
                Integer key = Integer.valueOf(element[0]);
                this.collectionManager.addElement(key, ticket);
                this.collectionManager.setLastDate();
                this.collectionManager.sortedByPrise();
            }
            console.println("Ключи и объекты загружены");
        } catch (Exception e) {
            console.println("Ключи или объекты в файле не валидны");
        }
    }

    public void saveObjects(){
        String file_outpath = System.getenv("file_outpath");
        if (file_outpath == null || file_outpath.isEmpty())
            console.println("Путь должен быть в переменных окружения в перменной 'file_outpath'");
        else {
            console.println("Путь получен успешно");
            File file = new File(file_outpath);
            if (!file.canWrite()) {
                console.println("Невозможно записать в файл");
            } else {
                try {
                    ArrayList<String> strings = new ArrayList<>();
                    ArrayList<Integer> keys = new ArrayList<>();
                    ArrayList<Ticket> tickets = new ArrayList<>();
                    for (Map.Entry<Integer, Ticket> entry : collectionManager.getCollection().entrySet()) {
                        keys.add(entry.getKey());
                        tickets.add(entry.getValue());
                    }
                    strings.add("key,id,name,c_x,c_y,creationDate,price,comment,type,years,hairColor,nationality,l_x,l_y,l_name");
                    for (int i = 0; i < tickets.size(); i++) {
                        Integer key = keys.get(i);
                        Ticket ticket = tickets.get(i);
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

                        strings.add(key + "," + ticket.getId() + "," + ticket.getName() + "," + ticket.getCoordinates().getX() +
                                "," + c_y + "," + ticket.getCreationDate() + "," + ticket.getPrice() +
                                "," + ticket.getComment() + "," + TicketType.getByName(ticket.getType()) + "," +
                                ticket.getPerson().getBirthday() + "," + Color.getByName(ticket.getPerson().getHairColor()) +
                                "," + Country.getByName(ticket.getPerson().getNationality()) + "," +
                                l_x + "," + ticket.getPerson().getLocation().getY() +
                                "," + ticket.getPerson().getLocation().getName());
                    }
                    FileOutputStream out = new FileOutputStream(file_outpath);
                    for (String s : strings) {
                        out.write((s + "\n").getBytes(StandardCharsets.UTF_8));
                    }
                    out.close();
                    console.println("Данные сохранены");
                } catch (FileNotFoundException e) {
                    console.println("Файл не существует");
                } catch (IOException e) {
                    console.println("Ошибка ввода вывода");
                }
            }
        }
    }

    public void autoSaveObjects() {
        String tmp_path = System.getenv("tmp_path");
        if (tmp_path == null || tmp_path.isEmpty())
            console.println("Файл для автосохрания не найден");
        else {
            File file = new File(tmp_path);
            if (file.canWrite()) {
                try {
                    ArrayList<String> strings = new ArrayList<>();
                    ArrayList<Integer> keys = new ArrayList<>();
                    ArrayList<Ticket> tickets = new ArrayList<>();
                    for (Map.Entry<Integer, Ticket> entry : collectionManager.getCollection().entrySet()) {
                        keys.add(entry.getKey());
                        tickets.add(entry.getValue());
                    }
                    strings.add("key,id,name,c_x,c_y,creationDate,price,comment,type,years,hairColor,nationality,l_x,l_y,l_name");
                    for (int i = 0; i < tickets.size(); i++) {
                        Integer key = keys.get(i);
                        Ticket ticket = tickets.get(i);
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

                        strings.add(key + "," + ticket.getId() + "," + ticket.getName() + "," + ticket.getCoordinates().getX() +
                                "," + c_y + "," + ticket.getCreationDate() + "," + ticket.getPrice() +
                                "," + ticket.getComment() + "," + TicketType.getByName(ticket.getType()) + "," +
                                ticket.getPerson().getBirthday() + "," + Color.getByName(ticket.getPerson().getHairColor()) +
                                "," + Country.getByName(ticket.getPerson().getNationality()) + "," +
                                l_x + "," + ticket.getPerson().getLocation().getY() +
                                "," + ticket.getPerson().getLocation().getName());
                    }
                    FileOutputStream out = new FileOutputStream(tmp_path);
                    for (String s : strings) {
                        out.write((s + "\n").getBytes(StandardCharsets.UTF_8));
                    }
                    out.close();
                } catch (FileNotFoundException e) {
                    console.println("Файл для автосохранения не существует");
                } catch (IOException e) {
                    console.println("Ошибка ввода вывода");
                }
            }
        }
    }
}