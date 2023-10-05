package com.example.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Lessons {
	@Id
	int lessonId;
	
	String lessonName;
	
	String topics;
	
	String link;
	
	@ManyToOne
	Course course;
	
	public Lessons() {
		// TODO Auto-generated constructor stub
	}

	public Lessons(int lessonId, String lessonName, String topics, String link, Course course) {
		super();
		this.lessonId = lessonId;
		this.lessonName = lessonName;
		this.topics = topics;
		this.link = link;
		this.course = course;
	}

	public int getLessonId() {
		return lessonId;
	}

	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getTopics() {
		return topics;
	}

	public void setTopics(String topics) {
		this.topics = topics;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Lessons [lessonId=" + lessonId + ", lessonName=" + lessonName + ", topics=" + topics + ", link=" + link
				+ ", course=" + course + "]";
	}
	
	

}
