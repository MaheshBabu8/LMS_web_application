package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.Comments;

public interface CommentRepository extends JpaRepository<Comments, Integer> {
	
	

}
