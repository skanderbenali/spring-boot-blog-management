package com.skander.forum.repos;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.skander.forum.model.Post;
import com.skander.forum.model.User;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUser(User u);
	List<Post> findByDescriptionContaining(String text);
	
	

}
