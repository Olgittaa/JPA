package dto;

import java.io.Serializable;

public class StudentMarksDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Long id;
    private final String subjectName;

    private final String mark;

    public StudentMarksDTO(Long id, String subjectName, String mark) {
        this.id = id;
        this.subjectName = subjectName;
        this.mark = mark;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Long getId() {
        return id;
    }
    public String getMark() {
        return mark;
    }

}
