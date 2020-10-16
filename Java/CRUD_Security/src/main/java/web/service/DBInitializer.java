package web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Set;

@Service
public class DBInitializer {
    private Logger logger =
            LoggerFactory.getLogger(DBInitializer.class);
    @Autowired
    private UserDao userDao;

    @PostConstruct
    void init(){
        logger.info("Starting Database initialization...");
        User user = new User();
        user.setName("user");
        user.setPassword("user");
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        userDao.add(user);
        User admin = new User();
        admin.setName("admin");
        admin.setPassword("admin");
        admin.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
        userDao.add(admin);
    }
}
