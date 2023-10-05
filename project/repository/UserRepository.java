package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.Users;


public interface UserRepository extends JpaRepository<Users, Integer>{

	boolean existsByEmail(String email);
	
	Users getByEmail(String email);
	
	//boolean checkByEmailAndPassword(String email,String password);
	
}
