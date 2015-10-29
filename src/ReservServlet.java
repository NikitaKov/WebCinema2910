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
        data.put("id", id);
        TemplateUtil.render("/reserv.vsl", data, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
