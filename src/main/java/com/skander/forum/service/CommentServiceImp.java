package com.skander.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skander.forum.model.Comment;
import com.skander.forum.model.Post;
import com.skander.forum.repos.CommentRepository;






@Service
public class CommentServiceImp implements CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	@Override
	public Comment saveComment(Comment c) {
		return commentRepository.save(c);
	}

	@Override
	public Comment updateComment(Comment c) {
		return commentRepository.save(c);
	}

	@Override
	public void deleteComment(Comment c) {
		commentRepository.delete(c);
		
	}

	@Override
	public void deleteCommentById(Long id) {
		commentRepository.deleteById(id);
		
	}

	@Override
	public Comment getComment(Long id) {
		return commentRepository.findById(id).get();
	}

	@Override
	public List<Comment> getAllComments() {
		
		return commentRepository.findAll();
	}
	
	@Override
	public List<Comment> getByPost(Post p)
	{
		return commentRepository.findAllByPost(p);
	}
}
