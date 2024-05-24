package com.skander.forum.service;

import java.util.List;

import com.skander.forum.model.Comment;
import com.skander.forum.model.Post;





public interface CommentService {

	Comment saveComment(Comment c);
	Comment updateComment(Comment c);
	void deleteComment(Comment c);
	void deleteCommentById(Long id);
	Comment getComment(Long id);
	List<Comment> getAllComments();
	List<Comment> getByPost(Post p);
}
