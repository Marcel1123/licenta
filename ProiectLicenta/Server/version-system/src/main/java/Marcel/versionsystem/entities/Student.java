package Marcel.versionsystem.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Student")
@EntityListeners(AuditingEntityListener.class)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "prenume", nullable = false)
    private String prenume;

    @Column(name = "an", nullable = false)
    private int an;

    @Column(name = "grupa", nullable = false)
    private String grupa;

    @Column(name = "id_cont", nullable = false)
    private long id_cont;
}
