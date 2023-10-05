package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.Lessons;
import com.example.project.repository.LessonRepository;
import com.example.project.repository.UserRepository;

@Service

public class StudentServicesImplementation implements StudentService{

@Autowired
UserRepository ur;

@Autowired
LessonRepository lessonRepo;

@Override

public Lessons getLesson(int lessonId) {

return lessonRepo.findById(lessonId).get();

}

}
