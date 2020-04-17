package marcel.pirlog.licenta.userManagement.repositorys.groups;

import marcel.pirlog.licenta.userManagement.entities.*;
import marcel.pirlog.licenta.userManagement.models.*;
import marcel.pirlog.licenta.userManagement.repositorys.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ProjectRepository repository;

    @Autowired
    public GroupRepository(ProjectRepository repository) {
        this.repository = repository;
    }

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
            if(a == null){
                return null;
            }
        } catch (NoResultException ne) {
            return null;
        }

        TypedQuery<GroupEntity> groupEntityTypedQuery = entityManager.createQuery(
                "select g from GroupEntity g " +
                        " where g.name = :name", GroupEntity.class
        );

        try{
            GroupEntity groupEntity1 = groupEntityTypedQuery.setParameter("name", createGroupModel.getName())
                    .getSingleResult();

            if(groupEntity1 != null){
                return null;
            }
        } catch (NoResultException e){
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

    @Override
    @Transactional
    public String deleteGroup(String groupId) {
        try{
            List<ProjectEntity> p = repository.getAllGroupProject(UUID.fromString(groupId));

            if(p != null){
                for(ProjectEntity p1 : p) {
                    entityManager.createNativeQuery(
                            "delete from versiuni where id_proiect = ?")
                            .setParameter(1, p1.getId())
                            .executeUpdate();
                }

                entityManager.createNativeQuery(
                        "delete from proiect where id_group = ?")
                        .setParameter(1, UUID.fromString(groupId))
                        .executeUpdate();
            }

            entityManager.createNativeQuery(
                    "delete from membri_grup where id_grup = ?")
                    .setParameter(1, UUID.fromString(groupId))
                    .executeUpdate();

            entityManager.createNativeQuery(
                    "delete from grupuri ge where ge.id = :id")
                    .setParameter("id", UUID.fromString(groupId))
                    .executeUpdate();

        } catch (IllegalArgumentException | TransactionRequiredException e) {
            return null;
        }
        return groupId;
    }

    @Override
    @Transactional
    public GroupEntity getGroupById(String id){
        try{
            TypedQuery<GroupEntity> groupEntityTypedQuery = entityManager.createQuery(
                    "select gr from GroupEntity gr "+
                            " where gr.id = :id", GroupEntity.class
            );
            return groupEntityTypedQuery.setParameter("id", UUID.fromString(id))
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public GroupEntity getGroupByName(String name){
        try{
            TypedQuery<GroupEntity> groupEntityTypedQuery = entityManager.createQuery(
                    "select gr from GroupEntity gr " +
                            " where gr.name = :name", GroupEntity.class
            );
            return groupEntityTypedQuery.setParameter("name", name).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<SpecialStudentModel> getAllAvailableStudents(UUID groupId) {
        List<SpecialStudentModel> result = new LinkedList<>();
        try {
            TypedQuery<StudentEntity> groupEntityTypedQuery = entityManager.createQuery(
                    "select ste from StudentEntity ste ", StudentEntity.class
            );
            List<StudentEntity> studenti = groupEntityTypedQuery.getResultList();

            groupEntityTypedQuery = entityManager.createQuery(
                    "select ste from StudentEntity ste join GroupMemberEntity gme on gme.memberId = ste.id where gme.groupId = :groupId", StudentEntity.class
            );
            List<StudentEntity> studenti1 = groupEntityTypedQuery.setParameter("groupId", groupId).getResultList();
            studenti.removeAll(studenti1);
            for (StudentEntity se: studenti) result.add(mapToSSM(se));
            return result;
        } catch (NoResultException e){
            return result;
        }
    }

    @Transactional
    @Override
    public AddMemberModel addMemberModel(AddMemberModel addMemberModel){
        try{
            TypedQuery<GroupMemberEntity> groupEntityTypedQuery = entityManager.createQuery(
                    "select gr from GroupMemberEntity gr where gr.memberId = :stdId and gr.groupId = :grId", GroupMemberEntity.class
            );
            GroupMemberEntity g = groupEntityTypedQuery.setParameter("stdId", UUID.fromString(addMemberModel.getStudentId()))
                    .setParameter("grId", UUID.fromString(addMemberModel.getGroupId()))
                    .getSingleResult();
            if(g == null){
                throw new NoResultException();
            } else {
                return null;
            }
        } catch (NoResultException e){
            try{
                entityManager.createNativeQuery(
                        "insert into membri_grup values (?, ?, ?)")
                        .setParameter(1, UUID.randomUUID())
                        .setParameter(2, UUID.fromString(addMemberModel.getGroupId()))
                        .setParameter(3, UUID.fromString(addMemberModel.getStudentId()))
                        .executeUpdate();
                return addMemberModel;
            } catch (TransactionRequiredException f){
                return null;
            }
        }
    }

    @Transactional
    @Override
    public AddMemberModel removeMemberModel(AddMemberModel addMemberModel){
        try {
            entityManager.createNativeQuery(
                    "delete from membri_grup where id_membru = ? and id_grup = ?")
                    .setParameter(1, UUID.fromString(addMemberModel.getStudentId()))
                    .setParameter(2, UUID.fromString(addMemberModel.getGroupId()))
                    .executeUpdate();
            return addMemberModel;
        } catch (TransactionRequiredException e) {
            return null;
        }
    }

    @Override
    public List<SpecialStudentModel> getGroupMember(UUID groupId) {
        List<SpecialStudentModel> result = new LinkedList<>();
        try {
            TypedQuery<StudentEntity> groupEntityTypedQuery = entityManager.createQuery(
                    "select ste from StudentEntity ste " +
                            " join GroupMemberEntity gme on gme.memberId = ste.id " +
                            " where gme.groupId = :goupId", StudentEntity.class
            );
            List<StudentEntity> studenti = groupEntityTypedQuery.setParameter("goupId", groupId)
                    .getResultList();
            for (StudentEntity se: studenti) result.add(mapToSSM(se));
            return result;
        } catch (NoResultException e){
            return result;
        }
    }

    private SpecialStudentModel mapToSSM(StudentEntity studentEntity){
        return new SpecialStudentModel(studentEntity.getFirstName(),
                studentEntity.getLastName(),
                studentEntity.getYear(),
                studentEntity.getId());
    }
}
