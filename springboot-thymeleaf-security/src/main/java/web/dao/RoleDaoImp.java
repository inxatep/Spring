package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleByName(String name) {
        return  entityManager.createQuery("Select u from Role u where u.name =:name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> getAll() {
        String query = "from Role order by id";
        TypedQuery<Role> typedQuery = entityManager.createQuery(query, Role.class);
        return typedQuery.getResultList();
    }

    @Override
    public Role getById(Long id) {
        return entityManager.find(Role.class, id);
    }
}
