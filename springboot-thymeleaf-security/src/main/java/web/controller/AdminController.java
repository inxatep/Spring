package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.*;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;



    @GetMapping(value = "index")
    public String viewHomePage(Model model) {
   /*     model.addAttribute("listRoles", roleService.getAll());
        model.addAttribute("listUsers", userService.getAll());
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authUser", userService.getUserByName(authUser.getName()));

    */
        return "index";
    }
     /*
    @GetMapping(value ="newUser")
    public String showNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }

      */

    @PostMapping(value ="index")
    public String addNewUser(User user,
                             @RequestParam("roles") Long[] roleIds){
    /*    Set<Role> roleSet = new HashSet<>();
        for (Long roles : roleIds) {
            roleSet.add(roleService.getById(roles));
        }
        user.setRoles(roleSet);
        userService.update(user);

     */
        return "redirect:index";
    }

    @GetMapping("edit")
    public String editPage(@RequestParam("id") Long id, ModelMap model){
     //   model.addAttribute("user", userService.getById(id));
        return "redirect:index";
    }

    @PostMapping(value = "editA")
    public String editUser(User user,
                           @RequestParam("roles") Long[] roleIds){
      /*  Set<Role> roleSet = new HashSet<>();
        for (Long roles : roleIds) {
            roleSet.add(roleService.getById(roles));
        }
        user.setRoles(roleSet);
        userService.update(user);

       */
        return "redirect:index";
    }

    @GetMapping("delete")
    public String deleteUser(@RequestParam(value = "id") Long userId) {
    /*    userService.delete(userId);

     */
        return "redirect:index";
    }
}
