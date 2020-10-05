package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Override

    public void add(User user) {
        userDao.add(user);
    }

    @Override

    public void delete(int id) {
        userDao.delete(id);
    }

    @Override

    public void update(User user) {
        userDao.update(user);
    }

    @Override

    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override

    public User getById(int id) {
        return userDao.getById(id);
    }

    @Transactional
    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Transactional
    @Override
    public Role getRoleByName(String name) {

        return userDao.getRoleByName(name);
    }

    @Transactional
    @Override
    public void addRole(Role role) {
        userDao.addRole(role);
    }
}

