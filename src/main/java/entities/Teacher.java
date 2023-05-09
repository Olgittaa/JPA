package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "teacher")
public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "degree")
    private String degree;

    @Column(name = "department")
    private String department;

    public Teacher() {
    }

    public Teacher(String firstname, String lastname, String degree, String department) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.degree = degree;
        this.department = department;
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

    public String getDegree() {
        return degree;
    }

    public String getDepartment() {
        return department;
    }
}
