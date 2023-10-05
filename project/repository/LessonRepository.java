package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.Lessons;

public interface LessonRepository extends JpaRepository<Lessons, Integer>{

}
