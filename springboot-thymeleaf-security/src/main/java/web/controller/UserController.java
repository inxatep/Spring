/*package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.service.UserService;


@Controller
@RequestMapping("/index/")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "index")
	public String printUserInfo(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", userService.getUserByName(auth.getName()));

		return "index";
	}
}

 */