package web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;




@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
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

    /*@RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editEmployeeById(Model model, @PathVariable("id") Optional<Integer> id) {
        if (id.isPresent()) {
            User user = userService.getById(id.get());
            model.addAttribute("user", user);
        } else {
            model.addAttribute("user", new User());
        }
        return "new_user";
    }

     */

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("user") User user){
        if(user.getId() == 0){
            userService.add(user);
        }else {
            userService.update(user);
        }

        return "new_user";
    }

    @RequestMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable(name = "id") int id) {
        User user = userService.getById(id);
        ModelAndView model = new ModelAndView("update_user");
        model.addObject("user",user);
        return model;
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        userService.delete(id);
        return "redirect:/";
    }

}
