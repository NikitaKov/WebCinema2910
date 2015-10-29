import java.util.ArrayList;

public class Seance {

    private int id;
    private String name;
    private String time;
    private int hallNumber;
    private ArrayList<Point> reserved = new ArrayList<Point>();

    public Seance(int id, String name, String time, int hallNumber) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.hallNumber = hallNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int getHallNumber() {
        return hallNumber;
    }

    public ArrayList<Point> getReserved() {
        return reserved;
    }

    @Override
    public String toString() {
        return id + ". " + name + ", " + time + ", зал №" + hallNumber;
    }
}