package com.skander.forum.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skander.forum.model.Comment;
import com.skander.forum.model.Post;





public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findAllByPost(Post p);

}
