package marcel.pirlog.licenta.userManagement.entities;

import javax.persistence.*;
import java.io.File;
import java.sql.Array;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "Versiuni")
@Entity(name = "ProjectEntity")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genname")
    @SequenceGenerator(name = "genname", sequenceName = "seqname", allocationSize = 1)
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Id_student")
    private UUID studentId;

    @Column(name = "Id_materie")
    private UUID materieId;

    @Column(name = "Id_profesor")
    private UUID teacherId;

//    @Column(name = "Cod_sura")
//    private File[] fileList;

    @Column(name = "Data_incarcarii")
    private LocalDateTime uploadDate;

    public ProjectEntity(){
    }

    public ProjectEntity(UUID id, UUID studentId, UUID materieId, UUID teacherId,/* File fileList,*/ LocalDateTime uploadDate) {
        this.id = id;
        this.studentId = studentId;
        this.materieId = materieId;
        this.teacherId = teacherId;
//        this.fileList = fileList;
        this.uploadDate = uploadDate;
    }
}
