package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleByName(String name) {
        return  entityManager.createQuery("Select u from Role u where u.name =:name",Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
