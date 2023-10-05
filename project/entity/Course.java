package com.example.project.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Course {
	@Id
	int courseId;
	
	String courseName;
	
	int coursePrice;
	
	@OneToMany
	List<Lessons> Lessons;
	
	public Course() {
		// TODO Auto-generated constructor stub
	}

	public Course(int courseId, String courseName, int coursePrice, List<com.example.project.entity.Lessons> lessons) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.coursePrice = coursePrice;
		Lessons = lessons;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(int coursePrice) {
		this.coursePrice = coursePrice;
	}

	public List<Lessons> getLessons() {
		return Lessons;
	}

	public void setLessons(List<Lessons> lessons) {
		Lessons = lessons;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", coursePrice=" + coursePrice
				+ ", Lessons=" + Lessons + "]";
	}
	
	@ManyToMany
	List<Users> userList;
	public List<Users> getUserList() {
		
		return userList;
	}
	
	

}
