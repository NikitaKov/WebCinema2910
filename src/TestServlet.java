import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestServlet extends HttpServlet {

    static Cinema cinema;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        Hall[] halls = {new Hall(10, 20, 30), new Hall(20, 20, 20)};
        Seance[] seances = {new Seance(1, "Фильм 1", "18:00", 1), new Seance(2, "Фильм 2", "18:00", 2)};
        cinema = new Cinema(halls, seances);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> data = new HashMap<>();
        ArrayList<Seance> empty = new ArrayList<>();
        data.put("list", empty);
        TemplateUtil.render("/index.vsl", data, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> data = new HashMap<>();
        data.put("list", cinema.find(req.getParameter("film")));
        TemplateUtil.render("/index.vsl", data, resp.getWriter());
    }
}
