import entities.CoursesList;
import entities.Student;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AddToCourse", urlPatterns = {"/addtocourse"})
public class AddToCourse extends HttpServlet {

    @EJB
    JavaBean javaBean;
    private String tab;

    private void printHeaderAndIntro(PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>List of records</title>");
        out.println("</head>");
        out.println("<body>");
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

            tab = request.getParameter("subject");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequestPost(request, response);
    }

    private void processRequestPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            printHeaderAndIntro(out);
            out.println("<a href=\"listof?tab=subject\">Subjects</a>");

            Object objectToInsert = null;
            String submit = request.getParameter("submit");
            if (submit.equals("INSERT STUDENT")) {
                objectToInsert = new CoursesList(Long.parseLong(request.getParameter("student")),
                        Long.parseLong(tab));
            }
            try {
                javaBean.savePerson(objectToInsert);
                out.println("record inserted");
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
