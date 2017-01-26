package ws2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ws2.model.User;
import ws2.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user/new", method = RequestMethod.GET)
	public String form(@ModelAttribute User user) {
		return "/user/new";
	}

	@RequestMapping(value = "/user/new", method = RequestMethod.POST)
	public String create(@ModelAttribute User user) {
		user.setRole("ROLE_USER");
		userService.create(user);

		return "redirect:/";
	}

}
