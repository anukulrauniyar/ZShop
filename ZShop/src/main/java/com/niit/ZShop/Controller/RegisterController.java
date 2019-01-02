package com.niit.ZShop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.ZShopBackend.DAO.UserDAO;
import com.niit.ZShopBackend.model.User;

@Controller
public class RegisterController {
	
	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value="/register")
	public String showRegisterPage(Model m)
	{
		User user = new User();
		m.addAttribute(user);
		return "Register";
	}
	
	@RequestMapping(value="/userInsert", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user,Model m)
	{
		userDAO.saveUser(user);
		User user2 = new User();
		m.addAttribute(user2);
		return"Register";
	}
	
	
}
