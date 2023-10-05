package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.Course;
import com.example.project.entity.Lessons;
import com.example.project.entity.Users;
import com.example.project.repository.CourseRepository;
import com.example.project.repository.LessonRepository;

@Service
public class TrainerServiceImplementation implements TrainerService {
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	LessonRepository lessonRepository;
	

	@Override
	public Course addCourse(Course course) {
		return courseRepository.save(course);
		
	}


	@Override
	public String addLesson(Lessons lesson) {
		lessonRepository.save(lesson);
		return "Lesson Added Successfully into lesson table";
	}


	@Override
	public Course getCourse(int courseId) {
		return courseRepository.findById(courseId).get();
		
	}


	@Override
	public List<Course> courseList() {
		return courseRepository.findAll();
	}


	@Override
	public String saveCourse(Course course) {
		courseRepository.save(course);
		return "Course Updated Successfully";
	}


	


	

}
