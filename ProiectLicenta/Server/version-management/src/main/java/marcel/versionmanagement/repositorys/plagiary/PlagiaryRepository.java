package marcel.versionmanagement.repositorys.plagiary;

import marcel.versionmanagement.algorithms.Count;
import marcel.versionmanagement.algorithms.Plagiary;
import marcel.versionmanagement.entities.ProjectEntity;
import marcel.versionmanagement.entities.SubVersionEntity;
import marcel.versionmanagement.repositorys.versions.IVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.TransactionalException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Component
@Repository
public class PlagiaryRepository implements IPlagiaryRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private IVersionRepository versionRepository;

    @Override
    public void plagiary(ProjectEntity[] uuids){
        List<SubVersionEntity> finalVers = new LinkedList<>();
        for(ProjectEntity p : uuids){
            finalVers.add(versionRepository.getSubById(UUID.fromString(p.getIsFinal())));
        }
        SubVersionEntity[] finalV = finalVers.toArray(new SubVersionEntity[0]);
        for(int i = 0; i < finalV.length - 1; i++){
            double[] globalResult = new double[finalV.length - i];
            for(int j = i + 1; j < finalV.length; j++){
                List<Count> cout = new LinkedList<>();
                for(int k = 0; k < finalV[i].getContent().size(); k++){
                    Count c = new Count(Integer.MAX_VALUE, 0, 0);
                    for(int t = 0; t < finalV[j].getContent().size(); t++){
                        int distanta = new Plagiary(finalV[i].getContent().get(k).getFile(), finalV[j].getContent().get(t).getFile()).calculateDistance();
                        if(c.mutari > distanta){
                            c.mutari = distanta;
                            c.fileSize = finalV[i].getContent().get(k).getFile().length();
                            c.fileTestSize = finalV[j].getContent().get(t).getFile().length();
                        }
                    }
                    cout.add(c);
                }
                globalResult[i] = getFinalResult(cout);
            }
            double m = -Double.MAX_VALUE;
            for(int j = 0; j < globalResult.length; j++){
                if(m < globalResult[j])
                    m = globalResult[j];
            }
            System.out.println(m + " %");
            update(finalV[i].getProject().getId(), m + " %");
        }
    }

    private double getFinalResult(List<Count> counts){
        double d = 0;   
        double mutari_totale = 0;
        double F_L = 0;
        double F_R = 0;
        for(Count c : counts){
            mutari_totale += c.mutari;
            F_L += c.fileSize;
            F_R += c.fileTestSize;
        }
        DecimalFormat d1 = new DecimalFormat("#.####");
        return Double.parseDouble(d1.format((1 - (mutari_totale / (F_L + F_R))) * 100));
    }

    @Transactional
    public void update(UUID prj, String value){
        try{
            entityManager.createNativeQuery("update proiect set status_plagiere = ? where id = ?")
                    .setParameter(1, value)
                    .setParameter(2, prj)
                    .executeUpdate();
        } catch (TransactionalException e){
//            throw new TransactionalException("Exceptie", e);
        }
    }
}
