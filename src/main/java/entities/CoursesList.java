package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "courses_list")
public class CoursesList implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "subject_id")
    private Long subjectId;

    public Long getId() {
        return id;
    }

    public CoursesList() {
    }

    public CoursesList(Long studentId, Long subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
    }
}
