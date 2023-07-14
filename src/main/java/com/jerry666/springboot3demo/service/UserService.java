package com.jerry666.springboot3demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jerry666.springboot3demo.model.UserRepository;
import com.jerry666.springboot3demo.model.Users;

@Service
public class UserService {

	@Autowired
	private PasswordEncoder pwdEncoder;

	@Autowired
	private UserRepository userRepo;

//	@Autowired
//	public UserService(UserRepository userRepo) {
//		this.userRepo = userRepo;
//	}

	public Users addUser(Users users) {

		users.setPwd(pwdEncoder.encode(users.getPwd()));
		return userRepo.save(users);
	}

	public boolean checkIfUsernameExist(String username) {
		Users dbUser = userRepo.findByUsername(username);

		if (dbUser != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public Users checkLogin(String username, String inputPwd) {
		Users dbUser = userRepo.findByUsername(username);
		
		if(dbUser != null) {
			if(pwdEncoder.matches(inputPwd, dbUser.getPwd())) {
				return dbUser;
			}else {
				return null;
			}
		}
		
		return null;
	}
}
