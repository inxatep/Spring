package web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin")
    public String viewHomePage(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("listUsers", userService.getAll());
        return "index";
    }

    @GetMapping("/newUser")
    public String showNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    @PostMapping(value = "/save")
    public String addBook(@ModelAttribute("user") User user){
        if(user.getId() == 0){
            userService.add(user);
        }else {
            userService.update(user);
        }
        return "new_user";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "update_user";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@ModelAttribute(name = "id") int id) {
        userService.delete(id);
        return "redirect:/";
    }
}
