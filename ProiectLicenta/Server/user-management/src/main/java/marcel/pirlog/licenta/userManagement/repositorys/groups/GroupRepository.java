package marcel.pirlog.licenta.userManagement.repositorys.groups;

import marcel.pirlog.licenta.userManagement.entities.*;
import marcel.pirlog.licenta.userManagement.entities.person.PersonEntity;
import marcel.pirlog.licenta.userManagement.entities.person.TeacherEntity;
import marcel.pirlog.licenta.userManagement.exceptions.DuplicateException;
import marcel.pirlog.licenta.userManagement.exceptions.ObjectNotFoundException;
import marcel.pirlog.licenta.userManagement.models.AddMemberModel;
import marcel.pirlog.licenta.userManagement.models.CreateGroupModel;
import marcel.pirlog.licenta.userManagement.repositorys.person.PersonRepository;
import marcel.pirlog.licenta.userManagement.repositorys.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class GroupRepository implements IGroupRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private final ProjectRepository repository;
    private final PersonRepository personRepository;

    @Autowired
    public GroupRepository(ProjectRepository repository, PersonRepository personRepository) {
        this.repository = repository;
        this.personRepository = personRepository;
    }

    @Override
    public List<GroupEntity> findStudentGroups(UUID studentId) {
        TypedQuery<GroupEntity> groupEntityTypedQuery = entityManager.createQuery(
                "select gr from GroupEntity gr " +
                        " join gr.groupMember gm " +
                        " where gm.id = :studentId",
                GroupEntity.class
        );
        try {
            List<GroupEntity> ge = groupEntityTypedQuery.setParameter("studentId", studentId).getResultList();
            for(GroupEntity g : ge){
                g.setGroupMember(new LinkedList<>());
                g.setCreator(new TeacherEntity());
            }
            return ge;
        } catch (NoResultException ne){
            return null;
        }
    }

    @Override
    public List<GroupEntity> findTeacherGroups(UUID teacherId) {
        TypedQuery<GroupEntity> groupEntityTypedQuery = entityManager.createQuery(
                "select gr from GroupEntity gr "+
                        " where gr.creator.id = :creator",
                GroupEntity.class
        );
        List<GroupEntity> ge = groupEntityTypedQuery.setParameter("creator", teacherId)
                .getResultList();
        for(GroupEntity g : ge){
            for(PersonEntity p:g.getGroupMember()){
                hideAccount(p);
            }
        }
        return ge;
    }

    @Override
    public List<PersonEntity> getGroupMember(UUID groupId) {
        List<PersonEntity> result = new LinkedList<>();
        try {
            TypedQuery<GroupEntity> groupEntityTypedQuery1 = entityManager.createQuery(
                    "select gr from GroupEntity gr" +
                            " where gr.id = :groupId",
                    GroupEntity.class
            );
            List<PersonEntity> p = groupEntityTypedQuery1.setParameter("groupId", groupId)
                    .getSingleResult()
                    .getGroupMember();
            for(PersonEntity p1 : p){
                hideAccount(p1);
            }
            return p;
        } catch (NoResultException e){
            return result;
        }
    }

    @Override
    public List<PersonEntity> getAllAvailableStudents(UUID groupId) {
        List<PersonEntity> result = new LinkedList<>();
        try {
            TypedQuery<PersonEntity> groupEntityTypedQuery = entityManager.createQuery(
                    "select pe from PersonEntity pe " +
                            "join StudentEntity se on se.person = pe.id",
                    PersonEntity.class
            );
            List<PersonEntity> studenti = groupEntityTypedQuery.getResultList();
            for (PersonEntity se: studenti) hideAccount(se);
            return result;
        } catch (NoResultException e){
            return result;
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
            GroupEntity g = groupEntityTypedQuery.setParameter("name", name)
                    .getSingleResult();
            for(PersonEntity e: g.getGroupMember()){
                hideAccount(e);
            }
            return g;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public GroupEntity getGroupById(String id){
        try{
            TypedQuery<GroupEntity> groupEntityTypedQuery = entityManager.createQuery(
                    "select gr from GroupEntity gr "+
                            " where gr.id = :id", GroupEntity.class
            );
            GroupEntity g = groupEntityTypedQuery.setParameter("id", UUID.fromString(id))
                    .getSingleResult();
            g.setGroupMember(null);
            return g;
        } catch (NoResultException e){
            return null;
        }
    }

    @Transactional
    @Override
    public AddMemberModel addMemberModel(AddMemberModel addMemberModel){
        try{
            TypedQuery<GroupEntity> groupEntityTypedQuery = entityManager.createQuery(
                    "select gr from GroupEntity gr " +
                            "where gr.id = :grId", GroupEntity.class
            );
            GroupEntity g = groupEntityTypedQuery
                    .setParameter("grId", UUID.fromString(addMemberModel.getGroupId()))
                    .getSingleResult();

            List<PersonEntity> pe = g.getGroupMember().stream()
                    .filter(a -> a.getId().equals(UUID.fromString(addMemberModel.getStudentId())))
                    .collect(Collectors.toList());

            if(pe.size() > 0){
                throw new DuplicateException("This student already exist");
            }

            try{
                entityManager.createNativeQuery(
                        "insert into group_memger values (?, ?)")
                        .setParameter(1, UUID.fromString(addMemberModel.getGroupId()))
                        .setParameter(2, UUID.fromString(addMemberModel.getStudentId()))
                        .executeUpdate();
                return addMemberModel;
            } catch (TransactionRequiredException f){
                return null;
            }
        } catch (NoResultException | DuplicateException e){
            return null;
        }
    }

    @Override
    @Transactional
    public GroupEntity addGroup(CreateGroupModel createGroupModel) {
        GroupEntity groupEntity = getGroupByName(createGroupModel.getName());
        TeacherEntity teacherEntity = personRepository.getTeacherById(createGroupModel.getTeacherId());
        try{
            if(groupEntity != null){
                throw new DuplicateException("This group already exists");
            }

            if(teacherEntity == null){
                throw new ObjectNotFoundException("This teacher not exist");
            }

            groupEntity = new GroupEntity();

            groupEntity.setId(UUID.randomUUID());
            groupEntity.setName(createGroupModel.getName());

            entityManager.createNativeQuery(
                    "insert into grupuri values (?, ?, ?)")
                    .setParameter(1, groupEntity.getId())
                    .setParameter(2, groupEntity.getName())
                    .setParameter(3, createGroupModel.getTeacherId())
                    .executeUpdate();

        } catch (DuplicateException | ObjectNotFoundException e){
            return null;
        }

        return getGroupById(groupEntity.getId().toString());
    }

    @Transactional
    @Override
    public AddMemberModel removeMemberModel(AddMemberModel addMemberModel){
        try {
            entityManager.createNativeQuery(
                    "delete from group_memger where memgru_id = ? and group_id = ?")
                    .setParameter(1, UUID.fromString(addMemberModel.getStudentId()))
                    .setParameter(2, UUID.fromString(addMemberModel.getGroupId()))
                    .executeUpdate();
            return addMemberModel;
        } catch (TransactionRequiredException e) {
            return null;
        }
    }


    @Override
    @Transactional
    public String deleteGroup(String groupId) {
        try{
            List<ProjectEntity> p = repository.getAllGroupProject(UUID.fromString(groupId));

            if(p != null){
                for(ProjectEntity ps : p){
                    entityManager.createNativeQuery("delete from projet_version where proj_id = ?")
                            .setParameter(1, ps.getId())
                            .executeUpdate();

                    for(SubVersionEntity s : ps.getVersionEntities()){
                        entityManager.createNativeQuery("delete from vers_content where vers_id = ?")
                                .setParameter(1, s.getId())
                                .executeUpdate();
                        for(SubVersionContentEntity sc : s.getContent()){
                            entityManager.createNativeQuery("delete from content where id = ?")
                                    .setParameter(1, sc.getId())
                                    .executeUpdate();
                        }
                        entityManager.createNativeQuery("delete from versiuni where id = ?")
                                .setParameter(1, s.getId())
                                .executeUpdate();
                    }
                }
            }
            entityManager.createNativeQuery("delete from group_memger where group_id = ?")
                    .setParameter(1, UUID.fromString(groupId))
                    .executeUpdate();

            entityManager.createNativeQuery("delete from grupuri where id = ?")
                    .setParameter(1, UUID.fromString(groupId))
                    .executeUpdate();

        } catch (IllegalArgumentException | TransactionRequiredException e) {
            return null;
        }
        return groupId;
    }


    private void hideAccount(PersonEntity pe){
        pe.setAccountId(new AccountEntity());
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

}
