import entities.Marks;
import entities.Student;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AddMark", urlPatterns = {"/addmark"})
public class AddMark extends HttpServlet {

    @EJB
    JavaBean javaBean;

    private void printHeaderAndIntro(PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>List of records</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<body><a href=\"./\">home</a>");
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

            out.println("<form action=\"addtocourse\" method=\"POST\">");

            List<Student> studentList = javaBean.getAllStudents();

            out.println("<select name=\"student\">");

            for (Student student : studentList) {
                out.println("<option value=\"" + student.getId() + "\">"
                        + student.getFirstname() + " " + student.getLastname() + "</option>");
            }

            out.println("</select>");
            out.println("<input type=\"submit\" name=\"submit\" value=\"INSERT STUDENT\"/>");
            out.println("</form>");

            printFooter(out);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        processRequestPost(request, response);
    }

    private void processRequestPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            printHeaderAndIntro(out);
            out.println("<a href=\"listof?tab=subject\">Subjects</a>");

            String insert = request.getParameter("insert");
            Marks objectToInsert = new Marks(request.getParameter("mark"),
                    Long.parseLong(request.getParameter("course")));
            try {
                if (insert.equals("INSERT")) {
                    javaBean.savePerson(objectToInsert);
                    out.println("record inserted");
                } else if (insert.equals("UPDATE")) {
                    javaBean.updateMark(objectToInsert, Long.valueOf(request.getParameter("id")));
                    out.println("record updated");
                }
            } catch (Exception e) {
                out.println("Exception!");
                e.printStackTrace(out);
            }

            printFooter(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
