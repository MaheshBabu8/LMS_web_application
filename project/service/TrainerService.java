package com.example.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.entity.Course;
import com.example.project.entity.Lessons;
import com.example.project.entity.Users;

public interface TrainerService {
	
	Course addCourse(Course course);
	
	public String saveCourse(Course course);
	
	public String addLesson(Lessons lesson);

	public Course getCourse(int courseId);

	public List<Course> courseList();
	
	
	


}
