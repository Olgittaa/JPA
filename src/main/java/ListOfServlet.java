import dto.StudentMarksDTO;
import dto.SubjectDTO;
import dto.SubjectMarksDTO;
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

@WebServlet(name = "ListOfServlet", urlPatterns = {"/listof"})
public class ListOfServlet extends HttpServlet {

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
        out.println("<h1>List of records</h1>");
    }

    private void printFooter(PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
    }

    private void renderStudentTab(PrintWriter out) {
        out.println("<table><thead><tr>");
        out.println("<th>action</th><th>first name</th><th>last name</th>");
        out.println("<th>study direction</th><th>grade</th><th>List of subjects</th></thead><tbody>");
        List<Student> studentList = javaBean.getAllStudents();
        for (Student student : studentList) {
            out.println("<tr><td><a href=\"delete?type=student&id=" + student.getId() + "\">delete</a></td>");
            out.println("<td>" + student.getFirstname() + "</td><td>" + student.getLastname() + "</td>");
            out.println("<td>" + student.getDepartment() + "</td>");
            out.println("<td>" + student.getGrade() + "</td>");
            out.println("<td><a href=\"listof?tab=studentMarks&student=" + student.getId() + "\">List</a></td>");
            out.println("</tr>\n");
        }
        out.println("</tbody></table>");
        out.println("<br><br><a href=\"insertstudent.html\">Insert a new student</a>");
    }

    private void renderTeacherTab(PrintWriter out) {
        out.println("<table><thead><tr>");
        out.println("<th>action</th><th>first name</th><th>last name</th>");
        out.println("<th>degree</th><th>department</th><th>List of subjects</th></thead><tbody>");
        List<Teacher> teacherList = javaBean.getAllTeachers();
        for (Teacher teacher : teacherList) {
            out.println("<tr><td><a href=\"delete?type=teacher&id=" + teacher.getId() + "\">delete</a></td>");
            out.println("<td>" + teacher.getFirstname() + "</td><td>" + teacher.getLastname() + "</td>");
            out.println("<td>" + teacher.getDegree() + "</td>");
            out.println("<td>" + teacher.getDepartment() + "</td>");
            out.println("<td><a href=\"listof?tab=teachersubjects&teacher=" + teacher.getId() + "\">List</a></td>");
            out.println("</tr>");
        }
        out.println("</tbody></table>");
        out.println("<br><br><a href=\"insertteacher.html\">Insert a new teacher</a>");
    }

    private void renderStudentMarksTab(PrintWriter out, HttpServletRequest request) {
        out.println("<table><thead><tr>");
        out.println("<th>name</th><th>mark</th></thead><tbody>");
        List<StudentMarksDTO> marks = javaBean.getMarksByStudent(Long.parseLong(request.getParameter("student")));
        for (StudentMarksDTO mark : marks) {
            out.println("<td>" + mark.getSubjectName());
            out.println("<td>" + ((mark.getMark() == null) ? "" : mark.getMark()) + "</td>");
            out.println("</tr>\n");
        }
        out.println("</tbody></table>");
    }

    private void renderSubjectMarksTab(PrintWriter out, HttpServletRequest request) {
        out.println("<table><thead><tr>");
        out.println("<th>name</th><th>surname</th><th>mark</th><th>Insert mark</th></thead><tbody>");
        List<SubjectMarksDTO> marks = javaBean.getMarksBySubject(Long.parseLong(request.getParameter("subject")));
        for (SubjectMarksDTO mark : marks) {
            out.println("<form action=\"addmark?course="
                    + mark.getCourseId() + "&id=" + mark.getId() + "\" method=\"POST\">");
            out.println("<td>" + mark.getStudentFirstName() + "</td><td>" + mark.getStudentLastName() + "</td>");
            out.println("<td><input name=\"mark\" value=\"" +
                    ((mark.getMark() == null) ? "" : mark.getMark()) + "\"</td>");
            if (mark.getMark() == null || mark.getMark().equals("")) {
                out.println("<input type=\"submit\" name=\"insert\" value=\"INSERT\"/>");
            } else {
                out.println("<input type=\"submit\" name=\"insert\" value=\"UPDATE\"/>");
            }
            out.println("</tr>\n");
            out.println("</form>");
        }
        out.println("</tbody></table>");
    }

    private void renderSubjectTab(PrintWriter out) {
        out.println("<table><thead><tr>");
        out.println("<th>action</th><th>name</th><th>teacher</th><th>credits</th><th>Add student</th><th>List of students</th></thead><tbody>");
        List<SubjectDTO> subjectList = javaBean.getAllSubjectsWithTeacherNames();
        for (SubjectDTO subject : subjectList) {
            out.println("<tr><td><a href=\"delete?type=subject&id=" + subject.getId() + "\">delete</a></td>");
            out.println("<td>" + subject.getName() + "</td>");
            out.println("<td>" + subject.getTeacherFirstName() + " " + subject.getTeacherLastName() + "</td>");
            out.println("<td>" + subject.getCredits() + "</td>");
            out.println("<td><a href=\"addtocourse?subject=" + subject.getId() + "\">Add</a></td>");
            out.println("<td><a href=\"listof?tab=subjectMarks&subject=" + subject.getId() + "\">List</a></td>");
            out.println("</tr>\n");
        }
        out.println("</tbody></table>");
        out.println("<br><br><a href=\"insertservlet\">Insert a new subject</a>");
    }

    private void renderTeacherSubjectsTab(PrintWriter out, HttpServletRequest request) {
        out.println("<table><thead><tr>");
        out.println("<th>action</th><th>name</th><th>credits</th><th>Marks</th></thead><tbody>");
        List<Subject> subjectList = javaBean.getAllSubjectsByTeacher(Long.valueOf(request.getParameter("teacher")));
        for (Subject subject : subjectList) {
            out.println("<tr><td><a href=\"delete?type=subject&id=" + subject.getId() + "\">delete</a></td>");
            out.println("<td>" + subject.getName() + "</td>");
            out.println("<td>" + subject.getCredits() + "</td>");
            out.println("<td><a href=\"listof?tab=subjectMarks&subject=" + subject.getId() + "\">Manage</a></td>");
            out.println("</tr>\n");
        }
        out.println("</tbody></table>");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            printHeaderAndIntro(out);

            String tab = request.getParameter("tab");

            switch (tab) {
                case "student" -> renderStudentTab(out);
                case "teacher" -> renderTeacherTab(out);
                case "subject" -> renderSubjectTab(out);
                case "subjectMarks" -> renderSubjectMarksTab(out, request);
                case "studentMarks" -> renderStudentMarksTab(out, request);
                case "teachersubjects" -> renderTeacherSubjectsTab(out, request);
            }

            printFooter(out);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

}
