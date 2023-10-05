package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.Comments;
import com.example.project.repository.CommentRepository;

@Service

public class CommentSeviceImplementation implements CommentService {

@Autowired
CommentRepository commentRepo;



@Override
public List<Comments> commentList() {

return commentRepo.findAll();

}



@Override
public String addComment(Comments comment) {
	// TODO Auto-generated method stub
	return null;
}

}
