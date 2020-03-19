package marcel.pirlog.licenta.userManagement.repositorys.groups;

import marcel.pirlog.licenta.userManagement.entities.GroupEntity;
import marcel.pirlog.licenta.userManagement.entities.StudentEntity;
import marcel.pirlog.licenta.userManagement.entities.TeacherEntity;
import marcel.pirlog.licenta.userManagement.models.CreateGroupModel;
import marcel.pirlog.licenta.userManagement.models.StudentGroupModel;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
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

    @Override
    public List<GroupEntity> findTeacherGroups(UUID teacherId) {
        TypedQuery<GroupEntity> groupEntityTypedQuery = entityManager.createQuery(
                "select gr from GroupEntity gr "+
                        " where gr.creatorId = :creator", GroupEntity.class
        );
        return groupEntityTypedQuery.setParameter("creator", teacherId)
                                                .getResultList();
    }

    @Override
    @Transactional
    public GroupEntity addGroup(CreateGroupModel createGroupModel) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setId(UUID.randomUUID());
        groupEntity.setCreatorId(createGroupModel.getTeacherId());
        groupEntity.setName(createGroupModel.getName());

        TypedQuery<TeacherEntity> accountEntityTypedQuery = entityManager.createQuery(
                "select t from TeacherEntity t" +
                        " where t.id = :id", TeacherEntity.class
        );
        try{
            TeacherEntity a = accountEntityTypedQuery
                    .setParameter("id", createGroupModel.getTeacherId())
                    .getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }

        try {
            entityManager.createNativeQuery("insert  into grupuri values (?,?,?)")
                    .setParameter(1, groupEntity.getId())
                    .setParameter(2, groupEntity.getCreatorId())
                    .setParameter(3, groupEntity.getName())
                    .executeUpdate();
        } catch (TransactionRequiredException e){
            return null;
        }
        return groupEntity;
    }
}
