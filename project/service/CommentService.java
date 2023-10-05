package com.example.project.service;

import java.util.List;

import com.example.project.entity.Comments;

public interface CommentService {
	
	List<Comments> commentList();

	String addComment(Comments comment);

}
