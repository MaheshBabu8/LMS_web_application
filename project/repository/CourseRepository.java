package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.Course;
import com.example.project.entity.Users;

public interface CourseRepository extends JpaRepository<Course, Integer>{
	
	

}
