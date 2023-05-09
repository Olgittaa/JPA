package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "marks")
public class Marks implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mark")
    private String mark;

    @Column(name = "course_id")
    private Long courseId;

    public Marks() {
    }

    public Marks(String mark, Long courseId) {
        this.mark = mark;
        this.courseId = courseId;
    }

    public Long getId() {
        return id;
    }

    public String getMark() {
        return mark;
    }
}
