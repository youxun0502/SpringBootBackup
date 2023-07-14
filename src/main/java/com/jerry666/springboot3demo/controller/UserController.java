package com.jerry666.springboot3demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jerry666.springboot3demo.model.Users;
import com.jerry666.springboot3demo.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {
	
	@Autowired
	private UserService uService;
	
	@GetMapping("/users/register")
	public String registerPage() {
		return "Users/registerPage";
	}
	
	@PostMapping("/users/register")
	public String postRegister(@RequestParam("username") String username, @RequestParam("pwd") String password, Model model) {
		boolean isExist = uService.checkIfUsernameExist(username);
		
		if(isExist) {
			model.addAttribute("errorMsg", "此帳號已經存在");
		} else {
			Users u1 = new Users();
			u1.setUsername(username);
			u1.setPwd(password);
			
			uService.addUser(u1);
			model.addAttribute("okMsg","註冊成功");
		}
		
		return "users/registerPage";
	}
	
	@GetMapping("/users/login")
	public String loginPage() {
		return "users/loginPage";
	}
	
	@PostMapping("/users/login")
	public String postLogin(@RequestParam("username") String username, @RequestParam("pwd") String inputPwd, HttpSession session, Model model) {
		Users result = uService.checkLogin(username, inputPwd);
		
		if(result != null) {
			System.out.println("登入成功");
			session.setAttribute("loginUser", result);
		}else {
			System.out.println("登入失敗");
			model.addAttribute("loginFail", "帳號密碼錯誤");
		}
		return "users/loginPage";
	}
	
	@GetMapping("/users/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "users/logoutSuccess";
	}
}
