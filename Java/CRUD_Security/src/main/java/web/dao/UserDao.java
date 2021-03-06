package web.dao;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserDao {

    void add(User user);

    void delete(int id);

    void update(User user);

    List<User> getAll();

    User getById(int id);

    User getUserByName(String name);

    Role getRoleByName(String name);

    void addRole(Role role);
}