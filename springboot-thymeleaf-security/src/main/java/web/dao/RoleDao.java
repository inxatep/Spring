package web.dao;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface RoleDao {
    Role getRoleByName(String name);

    void addRole(Role role);

    List<Role> getAll();

    Role getById(Long id);
}
