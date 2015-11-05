import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IIS on 29.10.2015.
 */
public class ReservServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> data = new HashMap<>();
        String id = req.getParameter("id");
        Seance seanceById = TestServlet.cinema.findById(Integer.parseInt(id));
        int hallNumber = seanceById.getHallNumber();
        Hall hall = TestServlet.cinema.getHall(hallNumber);

        ArrayList<Row> rows = new ArrayList<>();
        int[] seats = hall.getSeats();
        for (int i = 0; i < seats.length; i++) {
            int numberOfSeats = seats[i];
            ArrayList<Integer> rowSeats = new ArrayList<>();
            for (int j = 0; j < numberOfSeats; j++){
                rowSeats.add(j + 1);
            }
            rows.add(new Row(i + 1, rowSeats));
        }
        data.put("id", id);
        data.put("rows", rows);
        TemplateUtil.render("/reserv.vsl", data, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String seats = req.getParameter("seat");
        System.out.println(seats);
        int p = seats.indexOf(",");
        int row = Integer.parseInt(seats.substring(0, p));
        int seat = Integer.parseInt(seats.substring(p + 1));
        String id = req.getParameter("id");
        Seance seanceById = TestServlet.cinema.findById(Integer.parseInt(id));

        String message;
        if (TestServlet.cinema.reserve(seanceById,row,seat)) {
            message = "Успешно забронировано!";
        } else {
            message = "Место занято";
        }
        Map<String, Object> data = new HashMap<>();
        data.put("message", message);
        TemplateUtil.render("/result.vsl", data, resp.getWriter());
    }
}
