package web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import web.model.Role;
import web.model.User;
import javax.annotation.PostConstruct;
import java.util.Set;

@Service
public class DBInitializer {
    private Logger logger =
            LoggerFactory.getLogger(DBInitializer.class);

    private final UserService userService ;

    private final RoleService roleService;


    public DBInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


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
        user.setRoles(Set.of(roleService.getRoleByName("ROLE_USER")));
        userService.add(user);
        User admin = new User();
        admin.setName("admin");
        admin.setPassword("admin");
        admin.setRoles(Set.of(roleService.getRoleByName("ROLE_ADMIN")));
        userService.add(admin);
    }
}
