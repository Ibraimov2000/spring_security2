package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    public RoleDaoImpl() {
    }

    @Override
    public void addRole(Role role) {
        Role managed = entityManager.merge(role);
        entityManager.persist(managed);
    }

    @Override
    public void deleteRole(long id) {
        entityManager.remove(getRoleById(id));
    }

    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Role getRoleById(long id) {
        TypedQuery<Role> q = entityManager.createQuery(
                "select role from Role role where role.id = :id", Role.class);
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public Role getRoleByName(String rolename) {
        try{
            Role role = entityManager.createQuery("SELECT r FROM Role r where r.name = :name", Role.class)
                    .setParameter("name", rolename)
                    .getSingleResult();
            return role;
        } catch (NoResultException ex){
            return null;
        }
    }

    @Override
    public Role getRoleById(Long id) {
        try{
            Role role = entityManager.createQuery("SELECT r FROM Role r where r.id = :id", Role.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return role;
        } catch (NoResultException ex){
            return null;
        }
    }

    @Override
    public Role createRoleIfNotFound(long id, String name) {
        Role role = getRoleByName(name);
        if (role == null) {
            role = new Role(id, name);
            Role managed = entityManager.merge(role);
            entityManager.persist(managed);
        }
        return role;
    }
}
