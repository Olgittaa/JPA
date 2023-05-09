package dto;

import java.io.Serializable;

public class SubjectDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Long id;
    private final String teacherFirstName;
    private final String teacherLastName;
    private final Long credits;
    private String name;

    public SubjectDTO(Long id, String teacherFirstName, String teacherLastName, Long credits, String name) {
        this.id = id;
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
        this.credits = credits;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public Long getCredits() {
        return credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
