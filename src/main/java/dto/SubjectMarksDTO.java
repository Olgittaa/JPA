package dto;

import java.io.Serializable;

public class SubjectMarksDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Long id;
    private Long courseId;
    private String studentFirstName;
    private String studentLastName;
    private String mark;

    public SubjectMarksDTO(Long id, Long courseId, String studentFirstName, String studentLastName, String mark) {
        this.id = id;
        this.courseId = courseId;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.mark = mark;
    }

    public Long getCourseId() {
        return courseId;
    }
    public Long getId() {
        return id;
    }
    public String getStudentFirstName() {
        return studentFirstName;
    }
    public String getStudentLastName() {
        return studentLastName;
    }
    public String getMark() {
        return mark;
    }
}
