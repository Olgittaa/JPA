import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteServlet", urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet {

    @EJB
    JavaBean javaBean;

    private void printHeaderAndIntro(PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Delete record</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div>Delete record</div>");
    }

    private void printFooter(PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            printHeaderAndIntro(out);

            String removeType = request.getParameter("type");
            long personId = Long.parseLong(request.getParameter("id"));

            try {
                javaBean.delete(removeType, personId);
                out.println("removed, <a href=\"./\">home</a>");
            } catch (Exception e) {
                out.println("Exception!");
                e.printStackTrace(out);
            }

            printFooter(out);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }

}
