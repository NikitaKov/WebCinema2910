import java.util.ArrayList;

public class Cinema {

    private Hall[] halls;
    private Seance[] seances;

    public Cinema(Hall[] halls, Seance[] seances) {
        this.halls = halls;
        this.seances = seances;
    }

    public ArrayList<Seance> find(String search) {
        ArrayList<Seance> found = new ArrayList<Seance>();
        for (Seance seance : seances) {
            if (seance.getName().contains(search)) {
                found.add(seance);
            }
        }
        return found;
    }

    public Seance findById(int searchId) {
        for (Seance seance : seances) {
            if (seance.getId() == searchId) {
                return seance;
            }
        }
        return null;
    }

    public Hall getHall(int number) {
        return halls[number - 1];
    }

    public boolean reserve(Seance seance, int row, int seat) {
        for (Point point : seance.getReserved()) {
            if ((point.row == row) && (point.seat == seat))
                return false;
        }
        Point point = new Point (seance.getId(), row, seat);
        seance.getReserved().add(point);
        return  true;
    }




}