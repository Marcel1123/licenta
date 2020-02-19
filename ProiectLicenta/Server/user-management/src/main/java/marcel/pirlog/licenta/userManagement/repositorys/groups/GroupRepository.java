package marcel.pirlog.licenta.userManagement.repositorys.groups;

import marcel.pirlog.licenta.userManagement.entities.GroupEntity;
import marcel.pirlog.licenta.userManagement.models.StudentGroupModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Repository
public class GroupRepository implements IGroupRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<StudentGroupModel> findStudentGroups(UUID studentId) {
        TypedQuery<GroupEntity> groupEntityTypedQuery = entityManager.createQuery(
                "select gr from GroupMemberEntity g, GroupEntity gr " +
                        "where gr.id = g.groupId and g.memberId = :studentId",
                GroupEntity.class
        );
        try {
            List<GroupEntity> studentGroups = groupEntityTypedQuery.setParameter("studentId", studentId).getResultList();
            List<StudentGroupModel> studentGroupModels = new LinkedList<>();
            for(GroupEntity g : studentGroups){
                studentGroupModels.add(new StudentGroupModel(g.getId(), g.getName()));
            }
            return studentGroupModels;
        } catch (NoResultException ne){
            return null;
        }
    }
}
