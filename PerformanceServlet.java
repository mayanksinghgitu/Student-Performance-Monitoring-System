import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

@WebServlet("/PerformanceServlet")
public class PerformanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("student_id");

        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM performance WHERE student_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(studentId));
            ResultSet rs = ps.executeQuery();

            ArrayList<Performance> performanceList = new ArrayList<>();
            while (rs.next()) {
                Performance performance = new Performance();
                performance.setPerformanceId(rs.getInt("performance_id"));
                performance.setStudentId(rs.getInt("student_id"));
                performance.setSubjectId(rs.getInt("subject_id"));
                performance.setGrade(rs.getString("grade"));
                performance.setSemester(rs.getString("semester"));
                performance.setYear(rs.getInt("year"));
                performanceList.add(performance);
            }
            con.close();

            String json = new Gson().toJson(performanceList);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
