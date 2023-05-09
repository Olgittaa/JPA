package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "department")
    private String department;

    @Column(name = "grade")
    private Long grade;

    public Student() {
    }

    public Student(String firstname, String lastname, String department, Long grade) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getDepartment() {
        return department;
    }

    public Long getGrade() {
        return grade;
    }
}
