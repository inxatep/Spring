package web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.Role;
import web.model.User;

import javax.annotation.PostConstruct;
import java.util.Set;

@Service
public class DBInitializer {
    private Logger logger =
            LoggerFactory.getLogger(DBInitializer.class);

    @Autowired
    private UserService userService ;

    @Autowired
    private RoleService roleService;


    @PostConstruct
    public void init() {
        initRoles();
        initUsers();
    }

    void initRoles() {
        logger.info("Starting Database Roles initialization...");
        Role users = new Role();
        users.setName("ROLE_USER");
        roleService.addRole(users);
        Role admins = new Role();
        admins.setName("ROLE_ADMIN");
        roleService.addRole(admins);
    }

    void initUsers(){
        logger.info("Starting Database Users initialization...");
        User user = new User();
        user.setName("user");
        user.setPassword("user");
        Set<Role> role_user = Set.of(roleService.getRoleByName("ROLE_USER"));
        user.setRoles(role_user);
        userService.add(user);
        User admin = new User();
        admin.setName("admin");
        admin.setPassword("admin");
        Set<Role> role_admin = Set.of(roleService.getRoleByName("ROLE_ADMIN"));
        user.setRoles(role_admin);
        userService.add(admin);
    }
}
