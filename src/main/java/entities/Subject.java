package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "subject")
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "credits")
    private Long credits;

    @Column(name = "name")
    private String name;

    public Subject() {
    }

    public Subject(Long teacherId, Long credits, String name) {
        this.teacherId = teacherId;
        this.credits = credits;
        this.name = name;
    }

    public Long getId() {
        return id;
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
