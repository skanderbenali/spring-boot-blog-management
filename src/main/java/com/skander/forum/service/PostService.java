package com.skander.forum.service;

import java.util.List;

import com.skander.forum.model.Post;
import com.skander.forum.model.User;

public interface PostService {

	Post savePost(Post p);
	Post updatePost(Post p);
	void deletePost(Post p);
	void deletePostById(Long id);
	Post getPost(Long id);
	List<Post> getAllPosts();
	String testStars(float stars);
	List<Post> getPostsByUser(User u);
	List<Post> search(String text);
	Post findById(Long id);
	List<Post> getPostsOrderByRate();
}
