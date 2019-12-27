package Marcel.versionsystem.entities;

import javax.persistence.*;

@Entity(name = "Student")
@Table(name = "Studenti")
public class Student {

    @Id
    @Column(name = "Id")
    public long id;

    @Column(name = "Nume")
    public String nume;

    @Column(name = "Prenume")
    public String prenume;

    @Column(name = "An")
    public int an;

    @Column(name = "Grupa")
    public String grupa;

    @Column(name = "Id_cont")
    public long id_cont;
}
