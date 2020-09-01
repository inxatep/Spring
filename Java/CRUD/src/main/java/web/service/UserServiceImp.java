package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.Dao.UserDao;
import web.Dao.UserDaoImp;
import web.model.User;

import java.util.List;

@Transactional
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
}
