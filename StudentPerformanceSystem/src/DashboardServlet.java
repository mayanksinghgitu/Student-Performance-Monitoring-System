import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is logged in
        HttpSession session = request.getSession(false); // false ensures no new session is created
        if (session == null || session.getAttribute("email") == null) {
            // Redirect to login page if not logged in
            response.sendRedirect("templates/login.html?error=unauthorized");
            return;
        }

        // Retrieve the user email from the session
        String email = (String) session.getAttribute("email");

        // Generate the dashboard HTML response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Dashboard</title>");
        out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css'>");
        out.println("<link rel='stylesheet' href='css/styles.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container mt-5'>");
        out.println("<h1>Welcome to the Dashboard</h1>");
        out.println("<p>Hello, <strong>" + email + "</strong>! Welcome to the Student Performance Monitoring System.</p>");
        out.println("<p>Here are some features you can explore:</p>");
        out.println("<ul>");
        out.println("<li>View your grades</li>");
        out.println("<li>Track your attendance</li>");
        out.println("<li>Update your profile</li>");
        out.println("</ul>");
        out.println("<a href='logout' class='btn btn-danger'>Logout</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
