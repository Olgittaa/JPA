import dto.StudentMarksDTO;
import dto.SubjectMarksDTO;
import dto.SubjectDTO;
import entities.Marks;
import entities.Student;
import entities.Subject;
import entities.Teacher;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class JavaBean {

    @PersistenceContext(unitName = "default")
    EntityManager entityManager;

    public void savePerson(Object person) {
        entityManager.persist(person);
    }

    public List<Student> getAllStudents() {
        Query q = entityManager.createQuery("SELECT s FROM Student s");
        return (List<Student>) q.getResultList();
    }

    public List<Teacher> getAllTeachers() {
        Query q = entityManager.createQuery("SELECT t FROM Teacher t");
        return (List<Teacher>) q.getResultList();
    }

    public List<Subject> getAllSubjectsByTeacher(Long teacherId) {
        Query q = entityManager.createQuery("SELECT t FROM Subject t where t.teacherId = :idParam");
        q.setParameter("idParam", teacherId);
        return (List<Subject>) q.getResultList();
    }

    public List<SubjectDTO> getAllSubjectsWithTeacherNames() {
        TypedQuery<SubjectDTO> q = entityManager.createQuery("""
                SELECT new dto.SubjectDTO(s.id,t.firstname,t.lastname, s.credits,s.name)
                FROM Subject s join Teacher t on s.teacherId = t.id
                """, SubjectDTO.class);
        return q.getResultList();
    }

    public List<SubjectMarksDTO> getMarksBySubject(long subjectId) {
        TypedQuery<SubjectMarksDTO> q = entityManager.createQuery("""
                SELECT new dto.SubjectMarksDTO(m.id, cl.id, s.firstname, s.lastname, m.mark)
                FROM Marks m right outer join CoursesList cl on m.courseId = cl.id
                join Student s on cl.studentId=s.id where cl.subjectId = :subjectId
                """, SubjectMarksDTO.class);
        q.setParameter("subjectId", subjectId);
        return q.getResultList();
    }

    public List<StudentMarksDTO> getMarksByStudent(long studentId) {
        TypedQuery<StudentMarksDTO> q = entityManager.createQuery("""
                SELECT new dto.StudentMarksDTO(m.id, s.name, m.mark)
                FROM Marks m right outer join CoursesList cl on m.courseId = cl.id
                join Subject s on cl.subjectId=s.id where cl.studentId = :studentId
                """, StudentMarksDTO.class);
        q.setParameter("studentId", studentId);
        return q.getResultList();
    }

    public void updateMark(Marks objectToInsert, Long id) {
        Query query = entityManager.createQuery("UPDATE Marks m SET m.mark = :markParam where m.id = :idParam");
        query.setParameter("markParam", "" + objectToInsert.getMark());
        query.setParameter("idParam", id);
        query.executeUpdate();
    }

    public void delete(String removeType, Long id) {
        switch (removeType) {
            case "student" -> {
                Student s = entityManager.find(Student.class, id);
                entityManager.remove(s);
            }
            case "teacher" -> {
                Teacher t = entityManager.find(Teacher.class, id);
                entityManager.remove(t);
            }
            case "subject" -> {
                Subject s = entityManager.find(Subject.class, id);
                entityManager.remove(s);
            }
        }
    }
}