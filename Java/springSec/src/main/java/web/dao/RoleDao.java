package web.dao;

import web.model.Role;

public interface RoleDao {
    Role getRoleByName(String name);

    void addRole(Role role);
}
