package web.service;

import web.model.Role;

import java.util.List;

public interface RoleService {

    Role getRoleByName(String name);

    void addRole(Role role);

    List<Role> getAll();

    Role getById(Long id);

}
