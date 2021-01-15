package web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping(value = "index")
    public String viewHomePage(Model model) {
        model.addAttribute("listUsers", userService.getAll());
        return "index";
    }

    @GetMapping(value ="newUser")
    public String showNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    @PostMapping(value = "save")
    public String addNewUser(@RequestParam(value = "name") String name,
                             @RequestParam(value = "password") String password,
                             @RequestParam("role") String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(roleService.getRoleByName(roles));
        }
        userService.update(new User(name, password, roleSet ));
        return "redirect:index";
    }

    @GetMapping("edit")
    public String editPage(@RequestParam("id") Long id, ModelMap model){
        model.addAttribute("user", userService.getById(id));
        return "update_user";
    }

    @PostMapping("editAdd")
    public String editUser(Model model,
                           @RequestParam("id") Long id,
                           @RequestParam("name") String name,
                           @RequestParam("password") String password,
                           @RequestParam("role") String[] role){
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(roleService.getRoleByName(roles));
        }
        userService.update(new User(id, name, password, roleSet ));
        return "redirect:index";
    }

    @GetMapping("delete")
    public String deleteUser(@RequestParam(value = "id") String id) {
        Long userId = Long.parseLong(id);
        userService.delete(userId);
        return "redirect:index";
    }
}
