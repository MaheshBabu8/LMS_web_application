package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.Users;
import com.example.project.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	UserRepository userRepository;
	
	@Autowired
	public UserServiceImplementation(UserRepository userRepository)
	{
		this.userRepository=userRepository;
		
	}
	
	
	@Override
	public String addUser(Users users) {
		userRepository.save(users);
		return "Student Registerd successfully...";
	}

	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.existsByEmail(email);
	}


	@Override
	public boolean validate(String email, String password) {
		if(userRepository.existsByEmail(email)) {

			Users u=userRepository.getByEmail(email);

			String dbPassword=u.getPassword();

			if(password.equals(dbPassword)) {

			return true;

			}

			else {

			return false;

			}

			}

			else {

			return false;

			}
	}


	@Override
	public Users getUser(String email) {
		// TODO Auto-generated method stub
		return userRepository.getByEmail(email);
	}


	@Override
	public String getUserRole(String email) {
		Users users = userRepository.getByEmail(email);
		return users.getRole();
	}


	@Override
	public String updateUser(Users user) {
		// TODO Auto-generated method stub
		return null;
	}

}
