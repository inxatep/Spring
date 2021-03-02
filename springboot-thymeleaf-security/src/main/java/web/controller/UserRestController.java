package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class UserRestController {

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public UserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/all")
    public List<User> getAllUsers() {
        return userService.getAll();
    }
    @GetMapping(value = "/role")
    public List<Role> getAllRoles() {
        return roleService.getAll();
    }

    @GetMapping(value = "/auth")
    public ResponseEntity<User> authUser() {
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(userService.getUserByName(authUser.getName()));
    }


    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }


    @PostMapping(value = "/add")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        userService.add(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/editSave")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok().body(user);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id)  {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}