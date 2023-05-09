import entities.Student;
import entities.Subject;
import entities.Teacher;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "InsertServlet", urlPatterns = {"/insertservlet"})
public class InsertServlet extends HttpServlet {

    @EJB
    JavaBean javaBean;

    private void printHeaderAndIntro(PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Inserting a new record</title>");
        out.println("</head>");
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

            Object objectToInsert = null;
            String submit = request.getParameter("submit");
            switch (submit) {
                case "INSERT STUDENT" -> {
                    out.println("<a href=\"listof?tab=student\">Students</a>");
                    objectToInsert = new Student(request.getParameter("firstname"),
                            request.getParameter("lastname"),
                            request.getParameter("department"),
                            Long.parseLong(request.getParameter("grade")));
                }
                case "INSERT TEACHER" -> {
                    out.println("<a href=\"listof?tab=teacher\">Teachers</a>");
                    objectToInsert = new Teacher(request.getParameter("firstname"),
                            request.getParameter("lastname"),
                            request.getParameter("degree"),
                            request.getParameter("department"));
                }
                case "INSERT SUBJECT" -> {
                    out.println("<a href=\"listof?tab=subject\">Subjects</a>");
                    objectToInsert = new Subject(Long.parseLong(request.getParameter("teacher")),
                            Long.parseLong(request.getParameter("credits")),
                            request.getParameter("name"));
                }
            }


            try {
                javaBean.savePerson(objectToInsert);
                out.println("record inserted");
            } catch (Exception e) {
                out.println("Exception!");
                e.printStackTrace(out);
            }

            printFooter(out);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Teacher> teacherList = javaBean.getAllTeachers();
        request.setAttribute("teachers", teacherList);
        request.getRequestDispatcher("insertsubject.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }
}
